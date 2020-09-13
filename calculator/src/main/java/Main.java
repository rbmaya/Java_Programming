import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String input = null;
        if (args.length == 1) {
            input = args[0];
        }

        Calculator calculator = new Calculator();

        try{
            calculator.calculate(input);
            logger.info("Calculating is successful.");
        }
        catch (Exception exc){
            logger.error("Generated exception:", exc);
            exc.printStackTrace();
            logger.error("Calculating is failed.");
        }
    }
}
