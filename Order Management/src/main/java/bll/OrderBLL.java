package bll;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.ClientOrder;
import model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class OrderBLL {
    private static OrderDAO orderDAO = new OrderDAO();
    private static ProductDAO productDAO = new ProductDAO();
    private static ClientDAO clientDAO = new ClientDAO();
    private static int noOfOrders = 0;

    /**
     * Realizeaza inserarea comenzii in baza de date
     * @param newOrder - comanda ce trebuie inserata
     */
    public static void insertOrder(ClientOrder newOrder){
        int idClient = newOrder.getIdClient();
        int idProduct = newOrder.getIdProduct();
        if(ClientBLL.findById(idClient) != null && ProductBLL.findById(idProduct) != null) {
            Product desiredProduct = ProductBLL.findById(idProduct);
            if(desiredProduct.getQuantity() >= newOrder.getQuantity()){
                orderDAO.insert(newOrder);
                desiredProduct.setQuantity(desiredProduct.getQuantity()-newOrder.getQuantity());
                productDAO.update(desiredProduct.getId(), desiredProduct);
            }else
               throw new IllegalArgumentException("The desired quantity cannot be greater that the number of products in stock!");
        }else
            throw new NoSuchElementException("Id for client or product does not exist!");
        writeBill(newOrder);
    }

    /**
     * Creaza un fisier cu detalii despre comanda inserata in baza de date
     * @param newOrder - comanda ce a fost inserata
     */
    private static void writeBill(ClientOrder newOrder){
        noOfOrders++;
        Client client = clientDAO.findById(newOrder.getIdClient());
        Product product = productDAO.findById(newOrder.getIdProduct());
        try {
            FileWriter myWriter = new FileWriter("Bill_no" + noOfOrders + ".txt");
            myWriter.write("Bill no." + noOfOrders + ":\n");
            myWriter.write("Client: " + client.getName() + "\n");
            myWriter.write("Product: " + product.getName() + "\n");
            myWriter.write("Quantity: " + newOrder.getQuantity() + "\n");
            myWriter.write("Total: " + newOrder.getQuantity() * product.getPrice() + "$\n");
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtine o lista cu numele coloanelor tabelului comenzi
     * @return lista cu numele coloanelor
     */
    public static List<Object> getHeader(){
        return orderDAO.getHeader();
    }

    /**
     * Obtine o matrice cu toate datele din tabelul comenzi
     * @return matrice cu datele tuturor comenzilor
     */
    public static List<List<Object>> getData(){
        return orderDAO.getTable();
    }
}
