package bll.validators;

import model.Product;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class PriceValidator implements Validator<Product>{
    /**
     * Se verifica daca pretul produsului este strict pozitiv
     * @param p - produsul caruia se doreste a i se verifica pretul
     * @return daca pretul este strict pozitiv
     */
    public boolean validate(Product p) {
        return p.getPrice() > 0;
    }
}
