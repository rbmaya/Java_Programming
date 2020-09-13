package Observers;

public interface Observer {
    void update(Observable.Event<?> event);
}
