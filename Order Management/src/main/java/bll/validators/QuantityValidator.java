package bll.validators;

import model.Product;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class QuantityValidator implements Validator<Product>{

    /**
     * Se verifica daca cantitatea produsului este strict pozitiva
     * @param p - produsul caruia se doreate a i se verifica cantitatea
     * @return daca cantitatea este strict pozitiva
     */
    public boolean validate(Product p) {
        return p.getQuantity() > 0;
    }
}
