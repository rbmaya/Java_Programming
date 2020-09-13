package operation;

import calculatorException.CalculatorException;
import calculatorException.WrongArgumentsException;

public class Define extends Operation {

    @Override
    public void doOperation(Context context, String[] args) throws CalculatorException {
        this.validateArgs(context, args);
        context.setVariableAndValue(args[0], Double.parseDouble(args[1]));
    }

    @Override
    public void validateArgs(Context context, String[] args) throws CalculatorException {
        if (args != null && args.length == 2) {
            try {
                Double.parseDouble(args[0]);
                throw new WrongArgumentsException("Wrong format of arguments for DEFINE-command.");
            } catch (NumberFormatException exc1) {
                try {
                    Double.parseDouble(args[1]);
                }
                catch(NumberFormatException exc2){
                    throw new WrongArgumentsException("Wrong format of arguments for DEFINE-command.");
                }
            }
        } else {
            throw new WrongArgumentsException("Wrong amount of arguments for DEFINE-command.");
        }
    }
}
