package au.com.totemsoft.crm.exception;

public class NotAllowedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotAllowedException(String message) {
        super(message);
    }

}
