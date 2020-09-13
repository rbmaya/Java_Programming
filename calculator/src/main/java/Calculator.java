import calculatorException.CalculatorException;
import calculatorException.FactoryException;
import calculatorException.LackOfCommandException;
import operation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.Scanner;

public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public void calculate(String input) throws Exception{
        Scanner scanner;
        if (input == null){
            logger.info("Creating scanner from {}", System.in);
            scanner = new Scanner(System.in);
        } else {
            logger.info("Creating scanner from {}", input);
            scanner = new Scanner(Paths.get(input));
        }

        logger.info("Creating new context.");
        Context context = new Context();

        logger.info("Initialization Factory.");
        Factory.initFactory();

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if (line.equals("END")) break;
            if (!line.startsWith("#") && !line.isEmpty()){
                String [] information = line.split("\\s+");
                Operation operation;
                try{
                    operation = Factory.getInstance().createOperation(information[0]);
                    //TODO information[0]
                }
                catch(Exception exc){
                    if (exc.getClass() != LackOfCommandException.class) {
                        throw exc;
                    } else {
                        logger.warn("Generated exception:", exc);
                        continue;
                    }
                }
                String [] args = new String[information.length - 1];
                System.arraycopy(information, 1, args, 0, information.length - 1);
                try{
                    logger.info("Trying to do operation {}", operation);
                    operation.doOperation(context, args);
                    logger.info("Operation is successfully {}", operation);
                } catch (CalculatorException exc){
                    logger.warn("Generated exception: ", exc);
                }
            }
        }
    }
}
