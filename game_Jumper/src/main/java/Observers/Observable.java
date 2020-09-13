package Observers;

import java.util.ArrayList;

public interface Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    abstract class Event <T> {
        protected T o;

        public Event(T o) {
            this.o = o;
        }

        public Object getContext() {
            return o;
        }
    }

    class TicEvent extends Event<Object> {
        public TicEvent(Object o) {
            super(o);
        }
    }

    class RecordEvent  extends Event<Integer> {
        public RecordEvent(Integer record) {
            super(record);
        }
    }


    default void notifyObservers(Event<?> event){
        for (Observer observer: observers){
            observer.update(event);
        }
    };
}
