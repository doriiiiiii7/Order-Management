package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    /**
     * Constructorul fara parametrii
     */
    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creaza interogarea pentru afisarea tuturor datelor din tabel
     * @return interogarea
     */
    private String createSelectAllQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM " + type.getSimpleName());
        return sb.toString();
    }

    /**
     * Creaza interogarea pentru afisarea datelor al caror field este specificat
     * @param field - campul in functie de care se face cautarea
     * @return interogarea
     */
    private String createSelectQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Creaza interogarea pentru inserarea in tabel
     * @param c - conexiunea
     * @return interogarea
     */
    private String createInsertQuery(Connection c){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName() + "(");
        ArrayList<String> fields = getNonPrimaryKeysOfTable(c);
        for(String fieldName: fields)
            sb.append(fieldName + ", ");
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") VALUES (");
        for(String field: fields){
            sb.append("?" + ", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(")");
        return sb.toString();
    }

    /**
     * Creaza interogarea pentru update pe un tabel
     * @param c - conexiunea
     * @return interogarea
     */
    private String createUpdateQuery(Connection c){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        ArrayList<String> fields = getNonPrimaryKeysOfTable(c);
        for(String fieldName: fields){
            sb.append(fieldName + " = ?, ");
        }
        sb.deleteCharAt(sb.length() - 2);
        String primaryKey = getPrimaryKeyOfTable(c);
        sb.append("WHERE " + primaryKey + " = ?");
        return sb.toString();
    }

    /**
     * Creaza interogarea pentru stergerea din tabel dupa primary-key
     * @param c - conexiunea
     * @return interogarea
     */
    private String createDeleteQuery(Connection c){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        String primaryKey = getPrimaryKeyOfTable(c);
        sb.append(" WHERE " + primaryKey + " = ?");
        return sb.toString();
    }

    /**
     * Returneaza o lista cu toate obiectele din tabel
     * @return lista de obiecte
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Obtine primary-key-ul unui tabel
     * @param c - conexiunea
     * @return numele primary-key-ului
     */
    public String getPrimaryKeyOfTable(Connection c){
        String tableName = type.getSimpleName();
        ResultSet rs = null;
        try {
            rs = c.getMetaData().getPrimaryKeys(null, null, tableName);
            rs.next();
            return rs.getString("COLUMN_NAME");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Obtine coloanele din tabel care nu sunt primary-key-uri
     * @param c - conexiunea
     * @return lista numelor coloanelor
     */
    public ArrayList<String> getNonPrimaryKeysOfTable(Connection c){
        String primaryKey = getPrimaryKeyOfTable(c);
        ArrayList<String> nonPrimaryKeys = new ArrayList<>();
        for(Field field: type.getDeclaredFields()){
            if(!field.getName().equals(primaryKey))
                nonPrimaryKeys.add(field.getName());
        }
        return nonPrimaryKeys;
    }

    /**
     * Gasetste un obiect dupa un id specificat
     * @param id - id-ul dupa care se face cautarea
     * @return obiectul gasit
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> list = createObjects(resultSet);
            if(list.isEmpty())
                return null;
            else
                return list.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creaza lista de obiecte din rezultatul returnat de interogare
     * @param resultSet - rezultatul returnat de interogare
     * @return lista de obiecte
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Insereaza un obiect in tabel
     * @param t - obiectul ce trebuie inserat
     * @return obiectul inserat
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createInsertQuery(connection);
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            String primaryKey = getPrimaryKeyOfTable(connection);
            for(Field field: type.getDeclaredFields()) {
                field.setAccessible(true);
                if(!field.getName().equals(primaryKey))
                    statement.setObject(parameterIndex++, field.get(t));
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Updateaza obiectul din tabel cu id-ul id cu datele lui t
     * @param id - id-ul obiectului ce trebuie updatat
     * @param t - datele cu care se face inlocuirea
     * @return obiectul updatat
     */
    public T update(int id, T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(connection);
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            String primaryKey = getPrimaryKeyOfTable(connection);
            for(Field field: type.getDeclaredFields()) {
                field.setAccessible(true);
                if(!field.getName().equals(primaryKey))
                    statement.setObject(parameterIndex++, field.get(t));
                else
                    statement.setObject(type.getDeclaredFields().length, id);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * stergea unui obiect din tabel
     * @param id - id-ul obiectului ce trebuie sters
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createDeleteQuery(connection);
            System.out.println(query);
            statement = connection.prepareStatement(query);
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Obtine numele coloanelor unui tabel
     * @return lista numelor coloanelor
     */
    public List<Object> getHeader(){
        List<Object> fields = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            fields.add(field.getName());
        }
        return fields;
    }

    /**
     * Obtine toate datele dintr-un anumit tabel
     * @return matrice cu datele din tabel
     */
    public List<List<Object>> getTable(){
        List<T> allT = findAll();
        List<List<Object>> allData = new ArrayList<>();
        for(T singleT: allT){
            List<Object> row = new ArrayList<>();
            for(Field fieldOfT: singleT.getClass().getDeclaredFields()){
                fieldOfT.setAccessible(true);
                try {
                    row.add(fieldOfT.get(singleT));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            allData.add(row);
        }
        return allData;
    }

}
