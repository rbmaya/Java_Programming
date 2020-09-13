package operation;

import calculatorException.CalculatorException;

public abstract class Operation implements Validator{
    public void doOperation(Context context, String[] args) throws CalculatorException {};
}

