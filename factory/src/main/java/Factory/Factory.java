package Factory;

import Factory.Details.*;
import Factory.FactoryStaff.AutoStorageController;
import Factory.FactoryStaff.Dealer;
import Factory.FactoryStaff.Supplier;
import Factory.FactoryStaff.WorkersService;
import Observers.Observable;
import Observers.Observer;
import Factory.Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Thread.State.WAITING;

public class Factory extends Observable implements Observer {

    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;

    private final WorkersService workers;

    private final ArrayList<Supplier<? extends Detail>> suppliers = new ArrayList<>();
    private final ArrayList<Dealer> dealers = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    public Factory() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/factoryProperties.properties"));

        int bodyCapacity = Integer.parseInt(properties.getProperty("StorageBodyCapacity"));
        bodyStorage = new Storage<Body>(bodyCapacity, Body.class);
        bodyStorage.addObserver(this);

        int motorCapacity = Integer.parseInt(properties.getProperty("StorageMotorCapacity"));
        motorStorage = new Storage<Motor>(motorCapacity, Motor.class);
        motorStorage.addObserver(this);

        int accessoryCapacity = Integer.parseInt(properties.getProperty("StorageAccessoryCapacity"));
        accessoryStorage = new Storage<Accessory>(accessoryCapacity, Accessory.class);
        accessoryStorage.addObserver(this);

        int autoCapacity = Integer.parseInt(properties.getProperty("StorageAutoCapacity"));
        autoStorage = new Storage<Auto>(autoCapacity, Auto.class);

        int countOfWorkers = Integer.parseInt(properties.getProperty("Workers"));
        workers = new WorkersService(countOfWorkers);

        autoStorage.addObserver(this);
        autoStorage.addObserver(new AutoStorageController(bodyStorage, motorStorage, accessoryStorage, autoStorage, workers));

        suppliers.add(new Supplier<Motor>(motorStorage, Motor.class));
        suppliers.add(new Supplier<Body>(bodyStorage, Body.class));

        int countAccessorySuppliers = Integer.parseInt(properties.getProperty("AccessorySuppliers"));
        for (int i = 0; i < countAccessorySuppliers; ++i){
            suppliers.add(new Supplier<Accessory>(accessoryStorage, Accessory.class));
        }

        int countOfDealers = Integer.parseInt(properties.getProperty("Dealers"));
        for (int i = 0; i < countOfDealers; ++i){
            dealers.add(new Dealer(autoStorage));
        }
    }

    public void start(){
        logger.info("Start of working factory.");
        for (Supplier<? extends Detail> supplier: suppliers){
            supplier.start();
        }

        for (Dealer dealer: dealers){
            dealer.start();
        }

    }

    public int getDelayMotorSupplier(){
        return suppliers.get(0).getDelay();
    }

    public int getCountWorkingMotorSuppliers(){
        return suppliers.get(0).getState() == WAITING ? 0 : 1;
    }

    public void changeDelayMotorSupplier(int newDelay){
        suppliers.get(0).changeDelay(newDelay);
    }

    public void changeDelayBodySupplier(int newDelay){
        suppliers.get(1).changeDelay(newDelay);
    }

    public int getDelayBodySupplier(){
        return suppliers.get(1).getDelay();
    }

    public int getCountWorkingBodySuppliers(){
        return suppliers.get(1).getState() == WAITING ? 0 : 1;
    }

    public void changeDelayAccessoriesSuppliers(int newDelay){
        for (int i = 2; i < suppliers.size(); ++i) {
            suppliers.get(i).changeDelay(newDelay);
        }
    }

    public int getDelayAccessoriesSuppliers(){
        return suppliers.get(3).getDelay();
    }

    public int getCountWorkingAccessoriesSuppliers(){
        int count = 0;
        for (int i = 2; i < suppliers.size(); ++i){
            if (suppliers.get(i).getState() != WAITING){
                ++count;
            }
        }
        return count;
    }

    public void changeDelayDealers(int newDelay){
        for (Dealer dealer: dealers){
            dealer.changeDelay(newDelay);
        }
    }

    public int getDelayDealers(){
        return dealers.get(0).getDelay();
    }

    public String getStateBodyStorage(){
        return (bodyStorage.getNumberOfDetails() + ". Max: " + bodyStorage.getCapacity());
    }

    public String getStateMotorStorage(){
        return (motorStorage.getNumberOfDetails() + ". Max: " + motorStorage.getCapacity());
    }

    public String getStateAccessoriesStorage(){
        return (accessoryStorage.getNumberOfDetails() + ". Max: " + accessoryStorage.getCapacity());
    }

    public String getStateAutoStorage(){
        return (autoStorage.getNumberOfDetails() + ". Max: " + autoStorage.getCapacity());
    }

    public void stop(){
        for (Supplier<? extends Detail> supplier: suppliers){
            supplier.interrupt();
        }

        for (Dealer dealer: dealers){
            dealer.interrupt();
        }
        workers.shutdown();
        logger.info("End of working factory.");
    }

    @Override
    public void update() {
        notifyObservers();
    }
}
