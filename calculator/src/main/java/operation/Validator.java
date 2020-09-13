package operation;

import calculatorException.CalculatorException;

public interface Validator {
    void validateArgs(Context context, String[] args) throws CalculatorException;
}