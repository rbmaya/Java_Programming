package Factory.FactoryStaff;

import Factory.Storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Supplier<T> extends Thread {
    private int DELAY = 1000 * 60;
    private final Storage<T> storage;
    private final Class<T> detailType;
    private boolean isChangedDelay = false;

    private static final Logger logger = LoggerFactory.getLogger(Supplier.class);

    public Supplier(Storage<T> storage, Class<T> detail_t) {
        this.storage = storage;
        this.detailType = detail_t;
    }

    public int getDelay() {
        return DELAY;
    }

    public void changeDelay(int newDelay) {
        isChangedDelay = true;
        this.DELAY = newDelay;
        this.interrupt();
    }

    public void createDetail() throws InterruptedException {
        try {
            T detail = detailType.getDeclaredConstructor().newInstance();
            this.storage.addDetail(detail);
            logger.info("Successfully created detail {}.", detailType.getSimpleName());
        } catch (InterruptedException exc) {
            throw exc;
        } catch (Exception exc) {
            logger.warn("Can not create detail {}.", detailType.getSimpleName());
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (!interrupted()) {
                    Thread.sleep(DELAY);
                    logger.info("Trying to create detail {}.", detailType.getSimpleName());
                    this.createDetail();
                }
            } catch (InterruptedException exc) {
                if (isChangedDelay) {
                    isChangedDelay = false;
                } else {
                    break;
                }
            }
        }
        System.out.println("SUPPLIER DIED");
    }
}
