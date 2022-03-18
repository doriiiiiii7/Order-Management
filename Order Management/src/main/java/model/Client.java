package model;

/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    /**
     * Constructorul gol
     */
    public Client(){}

    /**
     * Constructorul prin intermediul caruia se realizeaza inserarea
     * Id-ul este autoincrementat la nivelul bazei de date
     * @param name      - nume
     * @param address   - adresa
     * @param email     - email
     */
    public Client(String name, String address, String email){
        super();
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Constructorul ce primeste ca parametrii toate atributele clasei
     * @param id        - id
     * @param name      - nume
     * @param address   - adresa
     * @param email     - email
     */
    public Client(int id, String name, String address, String email){
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda returneaza date despre un client, prin specificarea valorilor tuturor atributelor sale
     */
    public String toString(){
        return "Id: " + id + "\nName: " + name + "\nAddress: " + address + "\nEmail: " + email;
    }
}
