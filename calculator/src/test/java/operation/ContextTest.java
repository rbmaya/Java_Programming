package operation;

import org.junit.Assert;
import org.junit.Test;


public class ContextTest extends Assert {

    @Test
    public void containsAndSetVariable() {
        Context context = new Context();
        context.setVariableAndValue("abc", 10.5);
        assertTrue(context.containsVariable("abc"));
        assertFalse(context.containsVariable("cba"));
    }

    @Test
    public void getStackSize() {
        Context context = new Context();
        context.pushStackElement(10.2);
        context.pushStackElement(32.0);
        assertEquals(context.getStackSize(), 2);
    }

    @Test
    public void pushAndPopStackElements() {
        Context context = new Context();
        context.pushStackElement(10.4);
        context.pushStackElement(4.78);
        context.pushStackElement(-45.6);
        double num3 = context.popStackElement();
        assertEquals(num3, -45.6, 1e-10);
        double num2 = context.popStackElement();
        assertEquals(num2, 4.78, 1e-10);
        double num1 = context.popStackElement();
        assertEquals(num1, 10.4, 1e-10);
    }
}