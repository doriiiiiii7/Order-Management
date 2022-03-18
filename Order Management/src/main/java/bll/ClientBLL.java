package bll;

import bll.validators.EmailValidator;
import dao.ClientDAO;
import model.Client;

import java.util.List;
import java.util.NoSuchElementException;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class ClientBLL {
    private final static ClientDAO clientDAO = new ClientDAO();
    private final static EmailValidator validator = new EmailValidator();

    /**
     * Realizeaza inserarea clientului in baza de date
     * @param newClient - clientul ce trebuie inserat
     */
    public static void insertClient(Client newClient){
        if(validator.validate(newClient))
            clientDAO.insert(newClient);
        else
            throw new NoSuchElementException("Email is not valid!");
    }

    /**
     * Realizeaza updatarea clientului in baza de date
     * @param id        - id-ul clientului ce trebuie updatat
     * @param client    - noile date ale clientului
     */
    public static void updateClient(int id, Client client){
        if(clientDAO.findById(id) != null){
            if(validator.validate(client))
                clientDAO.update(id, client);
            else
                throw new IllegalArgumentException("Email is not valid!");
        }
        else
            throw new NoSuchElementException("Client with id " + id + " was not found");
    }

    /**
     * Realizeaza stergerea clientului din baza de date
     * @param id - id-ul clientului ce trebuie sters
     */
    public static void deleteClient(int id){
        if(clientDAO.findById(id) != null)
            clientDAO.delete(id);
        else
            throw new NoSuchElementException("Client with id " + id + " was not found");
    }

    /**
     * Cauta un client dupa id
     * @param id - id-ul dupa care se face cautarea
     * @return clientul gasit
     */
    public static Client findById(int id){
        return clientDAO.findById(id);
    }

    /**
     * Obtine o lista cu numele coloanelor tabelului clienti
     * @return lista cu numele coloanelor
     */
    public static List<Object> getHeader(){
        return clientDAO.getHeader();
    }

    /**
     * Obtine o matrice cu toate datele din tabelul clienti
     * @return matrice cu datele tuturor clientilor
     */
    public static List<List<Object>> getData(){
        return clientDAO.getTable();
    }
}
