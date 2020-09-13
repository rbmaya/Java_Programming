package operation;

import calculatorException.CalculatorException;
import org.junit.Assert;
import org.junit.Test;


public class PopTest extends Assert {
    private static Operation operation = new Pop();
    private static Context context = new Context();

    @Test
    public void doOperation() {
        context.pushStackElement(3.5);
        try{
            operation.doOperation(context, null);
        } catch (CalculatorException ignore){}
        assertEquals(context.getStackSize(), 0);
    }
}