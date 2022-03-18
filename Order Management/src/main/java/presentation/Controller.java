package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class Controller {
    private final View view;

    /**
     * in interiorul constructorului sunt suprascrise metodele de actionPerformed
     * in functie de butonul care a fost apasat
     * @param view - interfata principala
     */
    public Controller(View view){
        this.view = view;

        view.addClientsButtonListener(new ActionListener() {
            /**
             * Se deschide o noua fereasta cu titlul Clients la apasarea butonului Clients
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AuxiliarView auxView = new AuxiliarView("Clients", "name:", "address:", "email:");
                AuxiliarController auxController = new AuxiliarController(auxView);
                auxView.setVisible(true);
            }
        });

        view.addProductsButtonListener(new ActionListener() {
            /**
             * Se deschide o noua fereasta cu titlul Products la apasarea butonului Products
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AuxiliarView auxView = new AuxiliarView("Products", "name:", "quantity:", "price");
                AuxiliarController auxController = new AuxiliarController(auxView);
                auxView.setVisible(true);
            }
        });

        view.addOrdersButtonListener(new ActionListener() {
            /**
             * Se deschide o noua fereasta cu titlul Orders la apasarea butonului Orders
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AuxiliarView auxView = new AuxiliarView("Orders", "idClient:", "idProduct:", "quantity:");
                AuxiliarController auxController = new AuxiliarController(auxView);
                auxView.setVisible(true);
            }
        });
    }
}
