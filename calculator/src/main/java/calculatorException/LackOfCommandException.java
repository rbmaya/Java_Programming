package calculatorException;

public class LackOfCommandException extends LoadingResourceException {
    public LackOfCommandException() {
        super();
    }

    public LackOfCommandException(String message) {
        super(message);
    }

    public LackOfCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public LackOfCommandException(Throwable cause) {
        super(cause);
    }
}
