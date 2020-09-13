import calculatorException.FactoryException;
import calculatorException.LackOfCommandException;
import calculatorException.LoadingResourceException;
import operation.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class Factory {
    public volatile static Factory INSTANCE = null;
    private static Properties props = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(Factory.class);


    private Factory() {}

    public static Factory getInstance(){
        if (INSTANCE == null){
            synchronized (Factory.class){
                if (INSTANCE == null){
                    INSTANCE = new Factory();
                }
            }
        }
        return INSTANCE;
    }


    public static void initFactory() throws FactoryException, IOException {
        logger.info("Loading resource of configuration.");
        InputStream inputStream = Factory.class.getClassLoader().getResourceAsStream("conf.properties");
        if (inputStream == null) {
            throw new LoadingResourceException("Can not load resource of configuration.");
        }
        props.load(inputStream);
    }


    Operation createOperation(String information) throws Exception {
        logger.info("Finding command \"{}\" in property-file.", information);
        String commandName = Factory.props.getProperty(information);
        if (commandName == null) {
            throw new LackOfCommandException("Can not find command.");
        }
        Operation operation = (Operation) Class.forName(commandName).getDeclaredConstructor().newInstance();
        logger.info("Creating operation {}", operation.getClass());
        return operation;
    }
}
