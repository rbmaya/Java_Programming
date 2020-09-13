package operation;

import calculatorException.CalculatorException;
import org.junit.Assert;
import org.junit.Test;

public class DefineTest extends Assert {
    private static Operation operation = new Define();
    private static Context context = new Context();

    @Test
    public void doOperation() {
        try {
            operation.doOperation(context, new String[]{"abc", "-5.6"});
        } catch (CalculatorException ignore){}
        assertTrue(context.containsVariable("abc"));
        assertEquals(context.getVariableValue("abc"), -5.6, 1e-10);
    }

    @Test
    public void doOperationwithNull(){
        try {
            operation.doOperation(context, null);
            //TODO null array
        } catch (CalculatorException ignore){}
        assertFalse(context.containsVariable("abc"));
    }
}