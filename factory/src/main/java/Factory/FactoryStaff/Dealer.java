package Factory.FactoryStaff;

import Factory.Details.Auto;
import Factory.Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dealer extends Thread{
    private int DELAY = 1000 * 60;
    private final Storage<Auto> autoStorage;
    private boolean isChangedDelay = false;

    private static final Logger logger = LoggerFactory.getLogger(Dealer.class);

    public Dealer(Storage<Auto> autoStorage){
        this.autoStorage = autoStorage;
    }

    public int getDelay(){
        return DELAY;
    }

    public void changeDelay(int newDelay){
        isChangedDelay = true;
        this.DELAY = newDelay;
        this.interrupt();
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (!interrupted()) {
                    Thread.sleep(DELAY);
                    logger.info("Trying to buy auto.");
                    autoStorage.getDetail();
                    logger.info("Auto was bought.");
                }
            } catch (InterruptedException exc) {
                if (isChangedDelay) {
                    isChangedDelay = false;
                } else {
                    break;
                }
            }
        }
    }
}
