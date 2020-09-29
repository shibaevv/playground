package au.com.totemsoft.shoppingcart.exception;

public class ShoppingCartException extends Exception {

    private static final long serialVersionUID = -6575489960873969252L;

    public ShoppingCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShoppingCartException(String message) {
        super(message);
    }

    public ShoppingCartException(Throwable cause) {
        super(cause);
    }

}
