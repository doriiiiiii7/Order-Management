package model;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */

public class ClientOrder {

    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    /**
     * Constructorul gol
     */
    public ClientOrder(){}

    /**
     * Constructorul prin intermediul caruia se realizeaza inserarea
     * Id-ul este autoincrementat la nivelul bazei de date
     * @param idClient      - id client
     * @param idProduct     - id produs
     * @param quantity      - cantitate
     */
    public ClientOrder(int idClient, int idProduct, int quantity){
        super();
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * Constructorul ce primeste ca parametrii toate atributele clasei
     * @param id            - id
     * @param idClient      - id client
     * @param idProduct     - id produs
     * @param quantity      - cantitate
     */
    public ClientOrder(int id, int idClient, int idProduct, int quantity){
        super();
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
