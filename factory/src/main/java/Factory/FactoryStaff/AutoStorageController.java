package Factory.FactoryStaff;

import Factory.Details.*;
import Observers.Observer;
import Factory.Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class AutoStorageController implements Observer {
    private static final double MIN_PERCENTAGE = 0.2;
    private static final double NORMAL_PERCENTAGE = 0.5;
    private static final int COUNT_OF_ACCESSORIES = 3;

    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;
    private final WorkersService workers;

    private static final Logger logger = LoggerFactory.getLogger(AutoStorageController.class);

    private static int completed;
    private static int submitted;

    public AutoStorageController(Storage<Body> bodyStorage, Storage<Motor> motorStorage,
                                 Storage<Accessory> accessoryStorage, Storage<Auto> autoStorage, WorkersService workers) {
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.autoStorage = autoStorage;
        this.workers = workers;
        this.update();
    }

    @Override
    public void update() {
        int notCompleted = AutoStorageController.submitted - AutoStorageController.completed;
        double currentPercentage = (double) (autoStorage.getNumberOfDetails() + notCompleted) / autoStorage.getCapacity();
        if (currentPercentage  < MIN_PERCENTAGE) {
            logger.info("Create task to make " + (NORMAL_PERCENTAGE - currentPercentage) * autoStorage.getCapacity() + " autos.");
            for (int i = 0; i < (NORMAL_PERCENTAGE - currentPercentage) * autoStorage.getCapacity(); ++i) {
                workers.execute(new Task());
                ++submitted;
            }
        }
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            try {
                logger.info("Trying to create auto.");
                Body body = bodyStorage.getDetail();
                Motor motor = motorStorage.getDetail();
                ArrayList<Accessory> accessories = new ArrayList<>();
                for (int j = 0; j < COUNT_OF_ACCESSORIES; ++j) {
                    accessories.add(accessoryStorage.getDetail());
                }
                Auto auto = new Auto(body, motor, accessories);
                autoStorage.addDetail(auto);
                logger.info("Auto was successfully created.");
                ++completed;
            } catch (InterruptedException exc) {
                logger.info("Generated exception: ", exc);
            }
        }
    }
}
