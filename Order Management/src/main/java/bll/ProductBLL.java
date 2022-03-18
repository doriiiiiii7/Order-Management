package bll;

import bll.validators.PriceValidator;
import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class ProductBLL {
    private static ProductDAO productDAO = new ProductDAO();
    private static ArrayList<Validator<Product>> validators = new ArrayList<>();;

    /**
     * Adauga validatorii pentru produs
     */
    private static void addValidators(){
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());
    }

    /**
     * Realizeaza inserarea produsului in baza de date
     * @param newProduct - produsul ce trebuie inserat
     */
    public static void insertProduct(Product newProduct){
        addValidators();
        if(!validators.get(0).validate(newProduct))
            throw new NumberFormatException("Price must be a positive float!");
        if(!validators.get(1).validate(newProduct))
            throw new NumberFormatException("Quantity must be a positive integer");
        productDAO.insert(newProduct);
    }

    /**
     * Realizeaza updatarea produsului in baza de date
     * @param id        - id-ul produsului ce trebuie editat
     * @param product   - noile date ale produsului
     */
    public static void updateProduct(int id, Product product){
        addValidators();
        if(productDAO.findById(id) != null) {
            if(!validators.get(0).validate(product))
                throw new NumberFormatException("Price must be a positive float!");
            if(!validators.get(1).validate(product))
                throw new NumberFormatException("Quantity must be a positive integer");
            productDAO.update(id, product);
        }
        else
            throw new NoSuchElementException("Product with id " + id + " was not found");
    }

    /**
     * Realizeaza stergerea unui produs din baza de date
     * @param id - id-ul produsului ce trebuie sters
     */
    public static void deleteProduct(int id){
        if(productDAO.findById(id) != null)
            productDAO.delete(id);
        else
            throw new NoSuchElementException("Product with id " + id + " was not found");
    }

    /**
     * Realizeaza cautarea unui produs dupa id
     * @param id - id-ul dupa care se face cautarea
     * @return produsul gasit
     */
    public static Product findById(int id){
        return productDAO.findById(id);
    }

    /**
     * Obtine o lista cu numele coloanelor tabelului produs
     * @return lista cu numele coloanelor
     */
    public static List<Object> getHeader(){
        return productDAO.getHeader();
    }

    /**
     * Obtine o matrice cu toate datele din tabelul produs
     * @return matrice cu datele tuturor produselor
     */
    public static List<List<Object>> getData(){
        return productDAO.getTable();
    }

}
