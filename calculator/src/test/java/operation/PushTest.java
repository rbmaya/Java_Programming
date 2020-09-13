package operation;

import calculatorException.CalculatorException;
import org.junit.Assert;
import org.junit.Test;

public class PushTest extends Assert {
    private static Operation operation = new Push();
    private static Context context = new Context();

    @Test
    public void doOperation() {
        try {
            operation.doOperation(context, new String[]{"1.4"});
        } catch (CalculatorException ignore){}
        try {
            operation.doOperation(context, new String[]{"-5.788"});
        } catch (CalculatorException ignore){}
        try{
            operation.doOperation(context, null);
        } catch (CalculatorException ignore){}
        assertEquals(context.popStackElement(), -5.788, 1e-10);
        assertEquals(context.popStackElement(), 1.4, 1e-10);
    }
}