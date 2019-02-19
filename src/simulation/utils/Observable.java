package simulation.utils;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    List<Observateur> observateurs = new ArrayList<>();

    void addObserver(Observateur obs);

    void removeObserver(Observateur obs);

    void notifyObservers();
}
