package operation;

import calculatorException.CalculatorException;
import calculatorException.NegativeNumberException;
import calculatorException.StackSizeException;
import calculatorException.WrongArgumentsException;

public class Sqrt extends Operation {

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.validateArgs(context, args);
        Double num = context.popStackElement();
        context.pushStackElement(Math.sqrt(num));
    }

    @Override
    public void validateArgs(Context context, String[] args) throws CalculatorException {
        if (args != null && args.length != 0) {
            throw new WrongArgumentsException("Wrong amount of arguments for SQRT-command.");
        }
        if (context.getStackSize() < 1){
            throw new StackSizeException("Too few elements on stack for SQRT-command.");
        }
        if (context.getStackElement() < 0){
            throw new NegativeNumberException("Negative number for SQRT-command.");
        }
    }
}
