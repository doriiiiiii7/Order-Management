package bll.validators;

/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public interface Validator<T> {

    /**
     * Metoda de validare ce trebuie suprascrisa de toate clasele ce implementeaza interfata
     * @param t - parametrul generic, ce poate fi orice clasa
     * @return true, daca conditia de validare este indeplinita
     */
    public boolean validate(T t);
}
