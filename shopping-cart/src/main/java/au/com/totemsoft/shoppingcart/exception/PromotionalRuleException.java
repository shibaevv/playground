package au.com.totemsoft.shoppingcart.exception;

public class PromotionalRuleException extends ShoppingCartException {

    private static final long serialVersionUID = -3730161399662447181L;

    public PromotionalRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public PromotionalRuleException(String message) {
        super(message);
    }

    public PromotionalRuleException(Throwable cause) {
        super(cause);
    }

}
