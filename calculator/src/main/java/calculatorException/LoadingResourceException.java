package calculatorException;

public class LoadingResourceException extends FactoryException {
    public LoadingResourceException() {
        super();
    }

    public LoadingResourceException(String message) {
        super(message);
    }

    public LoadingResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingResourceException(Throwable cause) {
        super(cause);
    }
}
