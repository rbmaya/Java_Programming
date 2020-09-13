package Factory.Storage;

import Observers.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Storage<T> extends Observable {
    private final ArrayList<T> details = new ArrayList<>();
    private final int CAPACITY;
    private final Class<T> typeDetails;

    private static final Logger logger = LoggerFactory.getLogger(Storage.class);

    public Storage(int capacity, Class<T> typeDetails ) {
        this.CAPACITY = capacity;
        this.typeDetails = typeDetails;
    }

    public int getNumberOfDetails() {
        return details.size();
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public synchronized T getDetail() throws InterruptedException {
        this.notifyObservers();
        while (details.size() == 0) {
            logger.info("Storage of {} is empty. Waiting...", typeDetails.getSimpleName());
            this.wait();
        }
        T detail = details.get(details.size() - 1);
        details.remove(detail);
        logger.info("Detail {} was got. Number of details: {}", typeDetails.getSimpleName(), getNumberOfDetails());
        this.notify();
        return detail;
    }

    public synchronized void addDetail(T detail) throws InterruptedException {
        while (details.size() == CAPACITY) {
            logger.info("Storage of {} is full. Waiting...", typeDetails.getSimpleName());
            this.wait();
        }
        details.add(detail);
        logger.info("New detail {} was added. Number of details {}. ", typeDetails.getSimpleName(), details.size());
        this.notify();
        this.notifyObservers();
    }
}
