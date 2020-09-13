package operation;

import calculatorException.CalculatorException;
import calculatorException.StackSizeException;
import calculatorException.WrongArgumentsException;

public class Pop extends Operation {
    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.validateArgs(context, args);
        context.popStackElement();
    }

    @Override
    public void validateArgs(Context context, String[] args) throws CalculatorException{
        if (args != null && args.length != 0){
            throw new WrongArgumentsException("Wrong amount of arguments for POP-command.");
        }
        if (context.getStackSize() < 1){
            throw new StackSizeException("Too few elements on stack for POP-command.");
        }
    }
}
