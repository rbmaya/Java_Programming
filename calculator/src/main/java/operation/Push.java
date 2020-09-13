package operation;

import calculatorException.CalculatorException;
import calculatorException.WrongArgumentsException;

public class Push extends Operation {

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.validateArgs(context, args);
        try{
            Double.parseDouble(args[0]);
            context.pushStackElement(Double.parseDouble(args[0]));
        }
        catch (NumberFormatException exc){
            Double num = context.getVariableValue(args[0]);
            context.pushStackElement(num);
        }
    }

    @Override
    public void validateArgs(Context context, String[] args) throws CalculatorException {
        if (args != null && args.length == 1){
            try{
                Double.parseDouble(args[0]);
            }
            catch (NumberFormatException | NullPointerException exc){
                if (!context.containsVariable(args[0])){
                    throw new WrongArgumentsException("Variable is \"" + args[0] + "\" for PUSH-command not defined.");
                }
            }
        } else {
            throw new WrongArgumentsException("Wrong amount of arguments PUSH-command.");
        }
    }
}
