package operation;

import calculatorException.*;
import org.junit.Assert;
import org.junit.Test;

public class SqrtTest extends Assert {
    private static Operation operation = new Sqrt();
    private static Context context = new Context();

    @Test
    public void doOperation() {
        context.pushStackElement(9.0);
        try {
            operation.doOperation(context, null);
        } catch (CalculatorException ignored) {
        }
        assertEquals(3.0, context.popStackElement(), 1e-10);
        context.pushStackElement(18.5);
        try {
            operation.doOperation(context, null);
        } catch (CalculatorException ignored) {
        }
        assertEquals(Math.sqrt(18.5), context.popStackElement(), 1e-10);
    }

}