package by.it.academy.exceptions.product;

/**
 * The {@code NotEnoughProductException} exception throws when happen decreased products in the store or user bucket
 * and product amount not enough for decreasing.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
public class NotEnoughProductException extends RuntimeException {

    public NotEnoughProductException(String message) {
        super(message);
    }

}
