package simulation.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    protected List<Observateur> observateurs = new ArrayList<>();

    public void addObserver(Observateur obs) {
        this.observateurs.add(obs);
    }

    public void removeObserver(Observateur obs) {
        this.observateurs.remove(obs);
    }

    public abstract void notifyObservers();
}
