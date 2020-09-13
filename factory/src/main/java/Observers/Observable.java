package Observers;

import java.util.ArrayList;

public class Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer observer: observers){
            observer.update();
        }
    };
}
