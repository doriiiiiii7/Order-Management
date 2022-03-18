package model;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    /**
     * Constructorul gol
     */
    public Product(){}

    /**
     * Constructorul prin intermediul caruia se realizeaza inserarea
     * Id-ul este autoincrementat la nivelul bazei de date
     * @param name      - nume
     * @param quantity  - cantitate
     * @param price     - pret
     */
    public Product(String name, int quantity, double price){
        super();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constructorul ce primeste ca parametrii toate atributele clasei
     * @param id        - id
     * @param name      - nume
     * @param quantity  - cantitate
     * @param price     - pret
     */
    public Product(int id, String name, int quantity, double price){
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return "id: " + id + "\nname: " + name + "\nquantity: " + quantity + "\nprice: " + price;
    }
}
