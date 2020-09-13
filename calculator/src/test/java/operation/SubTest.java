package operation;

import calculatorException.CalculatorException;
import org.junit.Assert;
import org.junit.Test;

public class SubTest extends Assert {
    private static Operation operation = new Sub();
    private static Context context = new Context();

    @Test
    public void doOperation() {
        context.pushStackElement(4.6);
        context.pushStackElement(-3.2);
        try{
            operation.doOperation(context, null);
        } catch (CalculatorException ignore){}

        assertEquals(context.popStackElement(), -3.2 - 4.6, 1e-10);

        context.pushStackElement(54.8932);
        context.pushStackElement(35.58292929);
        try{
            operation.doOperation(context, null);
        } catch (CalculatorException ignore){}

        assertEquals(context.popStackElement(), 35.58292929 - 54.8932, 1e-10);
    }
}