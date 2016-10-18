package observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.10.16.
 */
public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers(String action);
}
