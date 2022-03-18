package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.ClientOrder;
import model.Product;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class AuxiliarController {
    private final AuxiliarView view;

    /**
     * in interiorul constructorului sunt suprascrise metodele de actionPerformed
     * in functie de butonul care a fost apasat
     * @param view - fereastra secundara
     */
    public AuxiliarController(AuxiliarView view){
        this.view = view;
        if(view.getTitle().equals("Orders")){
            view.getUpdateButton().setEnabled(false);
            view.getDeleteButton().setEnabled(false);
        }
        view.addInsertButtonListener(e -> initFields(false, true, true, true));

        view.addUpdateButtonListener(e -> initFields(true, true, true, true));

        view.addDeleteButtonListener(e -> initFields(true, false, false, false));

        view.addViewAllButtonListener(e -> initFields(false, false, false, false));

        view.addProceedButtonListener(e -> {
            if(view.getInsertButton().isSelected())
                insertIntoTable(view.getTitle());
            if(view.getUpdateButton().isSelected())
                updateOnTable(view.getTitle());
            if(view.getDeleteButton().isSelected())
                deleteFromTable(view.getTitle());
            if(view.getViewAllButton().isSelected())
                showTable(view.getTitle());
        });
    }

    /**
     * In functie de fereasta din care a fost apasat butonul, se va realiza inserarea in tabelul
     * aferent
     * @param title - numele tabelului in care se insereaza
     */
    public void insertIntoTable(String title){
        switch (title) {
            case "Clients" -> {
                Client newClient = getClientData();
                try {
                    ClientBLL.insertClient(newClient);
                    view.printMessage("Insert was successful!");
                } catch (Exception ex) {
                    view.printError(ex.getMessage());
                }
            }
            case "Products" -> {
                Product newProduct = getProductData();
                try {
                    ProductBLL.insertProduct(newProduct);
                    view.printMessage("Insert was successful!");
                } catch (Exception ex) {
                    view.printError(ex.getMessage());
                }
            }
            case "Orders" -> {
                ClientOrder newOrder = getOrderData();
                try {
                    OrderBLL.insertOrder(newOrder);
                    view.printMessage("Insert was successful!");
                } catch (Exception ex) {
                    view.printError(ex.getMessage());
                }
            }
        }
    }

    /**
     * In functie de fereasta din care a fost apasat butonul, se va realiza updatarea in tabelul
     * aferent
     * @param title - numele tabelului in care se editeaza
     */
    public void updateOnTable(String title){
        switch (title) {
            case "Clients" -> {
                Client client = getClientData();
                try {
                    ClientBLL.updateClient(client.getId(), client);
                    view.printMessage("Update was successful!");
                } catch (Exception ex) {
                    view.printError(ex.getMessage());
                }
            }
            case "Products" -> {
                Product product = getProductData();
                try {
                    ProductBLL.updateProduct(product.getId(), product);
                    view.printMessage("Update was successful!");
                } catch (Exception ex) {
                    view.printError(ex.getMessage());
                }
            }
        }
    }

    /**
     * in functie de fereasta din care a fost apasat butonul, se va realiza stergerea in tabelul
     * aferent
     * @param title - numele tabelului in care se sterge
     */
    public void deleteFromTable(String title){
        int id = getId(view.getIdField().getText());
        switch(title){
            case "Clients":
                try{
                    ClientBLL.deleteClient(id);
                    view.printMessage("Delete was successful!");
                }catch (Exception ex){
                    view.printError(ex.getMessage());
                }
                break;
            case "Products":
                try{
                    ProductBLL.deleteProduct(id);
                    view.printMessage("Delete was successful!");
                }catch (Exception ex){
                    view.printError(ex.getMessage());
                }
                break;
        }
    }

    /**
     * in functie de fereasta din care a fost apasat butonul, se va afisa tabelul aferent
     * @param title - numele tabelului ce va fi afisat
     */
    public void showTable(String title){
        switch (title){
            case "Clients":
                List<Object> header = ClientBLL.getHeader();
                List<List<Object>> data = ClientBLL.getData();
                Object[][] dataAsMatrix = new Object[data.size()][];
                for(int i = 0; i < data.size(); i++)
                    dataAsMatrix[i] = data.get(i).toArray();
                TableView table = new TableView(dataAsMatrix, header.toArray(), "Clients");
                table.setVisible(true);
                break;
            case "Products":
                header = ProductBLL.getHeader();
                data = ProductBLL.getData();
                dataAsMatrix = new Object[data.size()][];
                for(int i = 0; i < data.size(); i++)
                    dataAsMatrix[i] = data.get(i).toArray();
                table = new TableView(dataAsMatrix, header.toArray(), "Products");
                table.setVisible(true);
                break;
            case "Orders":
                header = OrderBLL.getHeader();
                data = OrderBLL.getData();
                dataAsMatrix = new Object[data.size()][];
                for(int i = 0; i < data.size(); i++)
                    dataAsMatrix[i] = data.get(i).toArray();
                table = new TableView(dataAsMatrix, header.toArray(), "Orders");
                table.setVisible(true);
                break;
        }
    }

    /**
     * Preia input-urile introduse de utilizator
     * @return vector de String-uri reprezentand input-urile
     */
    public ArrayList<String> input(){
        ArrayList<String> input = new ArrayList<>();
        input.add(view.getIdField().getText());
        input.add(view.getNameField().getText());
        input.add(view.getAddressField().getText());
        input.add(view.getEmailField().getText());
        return input;
    }

    /**
     * Gestioneaza separat id-ul, intrucat este comun pentru toate tabelele
     * @param input - id-ul sub forma de String
     * @return id-ul sub forma de int
     */
    public int getId(String input){
        int id = -1;
        if(input.equals("")){
            if(!view.getInsertButton().isSelected() && !view.getViewAllButton().isSelected())
                view.printError("You must enter the id!");
        }else{
            try{
                id = Integer.parseInt(input);
            }catch(NumberFormatException e){
                view.printError("You must enter a valid integer!");
            }
        }
        return id;
    }

    /**
     * Creaza un nou client, utilizand datele primite de la utilizator
     * @return un client ce trebuie introdus/editat in tabel
     */
    public Client getClientData(){
        Client newClient = new Client();
        ArrayList<String> input = input();

        int id = getId(input.get(0));
        if(id != -1)
            newClient.setId(id);

        if(input.get(1).equals("") && !view.getViewAllButton().isSelected())
            view.printError("You must enter the name!");
        else
            newClient.setName(input.get(1));

        if(input.get(2).equals("") && !view.getViewAllButton().isSelected())
            view.printError("You must enter the address!");
        else
            newClient.setAddress(input.get(2));

        if(input.get(3).equals("") && !view.getViewAllButton().isSelected())
            view.printError("You must enter the email!");
        else
            newClient.setEmail(input.get(3));

        return newClient;
    }

    /**
     * Creaza un nou produs, utilizand datele introduse de utilizator
     * @return un produs ce trebuie introdus/editat in tabel
     */
    public Product getProductData(){
        Product newProduct = new Product();
        ArrayList<String> input = input();

        int id = getId(input.get(0));
        if(id != -1)
            newProduct.setId(id);

        if(input.get(1).equals("")) {
            if (!view.getViewAllButton().isSelected())
                view.printError("You must enter the name!");
        }else {
            newProduct.setName(input.get(1));
        }

        if(input.get(2).equals("")) {
            if (!view.getViewAllButton().isSelected())
                view.printError("You must enter the quantity!");
        } else{
            try{
                int quantity = Integer.parseInt(input.get(2));
                newProduct.setQuantity(quantity);
            }catch(NumberFormatException e){
                view.printError("You must enter a valid integer!");
            }
        }

        if(input.get(3).equals("")) {
            if (!view.getViewAllButton().isSelected())
                view.printError("You must enter the price!");
        }else{
            try{
                double price = Double.parseDouble(input.get(3));
                newProduct.setPrice(price);
            }catch(NumberFormatException e){
                view.printError("You must enter a valid float!");
            }
        }
        return newProduct;
    }

    /**
     * Creaza o noua comanda, utilizand datele introduse de utilizator
     * @return o comanda ce trebuie introdusa in tabel
     */
    public ClientOrder getOrderData(){
        ClientOrder newOrder = new ClientOrder();
        ArrayList<String> input = input();

        int id = getId(input.get(0));
        if(id != -1)
            newOrder.setId(id);

        if(input.get(1).equals("")) {
            if (!view.getViewAllButton().isSelected())
                view.printError("You must enter the name!");
        }else {
            try {
                int idClient = Integer.parseInt(input.get(1));
                newOrder.setIdClient(idClient);
            } catch (NumberFormatException e) {
                view.printError("You must enter a valid integer!");
            }
        }

        if(input.get(2).equals("")) {
            if (!view.getViewAllButton().isSelected())
                view.printError("You must enter the quantity!");
        }else{
            try{
                int idProduct = Integer.parseInt(input.get(2));
                newOrder.setIdProduct(idProduct);
            }catch(NumberFormatException e){
                view.printError("You must enter a valid integer!");
            }
        }

        if(input.get(3).equals("")) {
            if (!view.getViewAllButton().isSelected())
                view.printError("You must enter the quantity!");
        } else{
            try{
                int quantity = Integer.parseInt(input.get(3));
                if(quantity <= 0)
                    view.printError("You must enter a positive integer!");
                else
                    newOrder.setQuantity(quantity);
            }catch(NumberFormatException e){
                view.printError("You must enter a valid integer!");
            }
        }
        return newOrder;
    }

    /**
     * Seteaza ce textField-uri sunt editabile, in functie de operatia ce se doreste a fi realizata
     * si reseteaza valorile scrise anterior
     * @param one   - primul textField editabil sau nu
     * @param two   - al doilea textField editabil sau nu
     * @param three - al treilea textField editabil sau nu
     * @param four  - al patrulea textField editabil sau nu
     */
    public void initFields(boolean one, boolean two, boolean three, boolean four){
        view.getIdField().setEditable(one);
        view.getNameField().setEditable(two);
        view.getAddressField().setEditable(three);
        view.getEmailField().setEditable(four);
        view.getIdField().setText("");
        view.getNameField().setText("");
        view.getAddressField().setText("");
        view.getEmailField().setText("");
    }
}
