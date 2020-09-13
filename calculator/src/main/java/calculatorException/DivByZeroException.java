package calculatorException;

public class DivByZeroException extends CalculatorException {
    public DivByZeroException() {
        super();
    }

    public DivByZeroException(String message) {
        super(message);
    }

    public DivByZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public DivByZeroException(Throwable cause) {
        super(cause);
    }
}
