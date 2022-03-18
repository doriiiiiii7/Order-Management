package start;

import presentation.Controller;
import presentation.View;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class Start {
    /**
     * Main-ul in interiorul caruia se instantiaza view-ul principal si controller-ul acestuia
     * @param args argumentele functiei main
     */
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);
    }
}
