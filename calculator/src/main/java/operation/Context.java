package operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private Stack<Double> numbers = new Stack<>();
    private Map<String, Double> variables = new HashMap<>();

    public boolean containsVariable(String variable){
        return variables.containsKey(variable);
    }

    public int getStackSize(){
        return numbers.size();
    }

    public Double popStackElement(){
        return numbers.pop();
    }

    public Double getStackElement(){
        return numbers.peek();
    }

    public Double getVariableValue(String variable){
        return variables.get(variable);
    }

    public void pushStackElement(Double num){
        numbers.push(num);
    }

    public void setVariableAndValue(String variable, Double value) {
        if (variables.containsKey(variable)) {
            variables.replace(variable, value);
        } else {
            variables.put(variable, value);
        }
    }

}
