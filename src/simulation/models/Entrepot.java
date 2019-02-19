package simulation.models;

import simulation.composants.Composant;
import simulation.utils.Observable;
import simulation.utils.Observateur;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Entrepot extends Batiment implements Observable {
    int capacity;

    public Entrepot(int id, int x, int y, String[] imgPaths, Integer capacity) {
        super(id, new Point(x, y), imgPaths);
        this.capacity = capacity;
    }

    @Override
    public void addObserver(Observateur obs) {
        this.observateurs.add(obs);
    }

    @Override
    public void removeObserver(Observateur obs) {
        this.observateurs.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observateur observateur: observateurs) {
            double percentage = (composants.size() * 1.0 / capacity) * 100.0;
            observateur.getNotified(percentage);
        }
    }

    @Override
    public void nextTurn() {
    }

    @Override
    public void connect(Chemin chemin) {
    }

    @Override
    public void addComponent(Composant composant) {
        this.composants.add(composant);
        notifyObservers();
    }

    @Override
    public BufferedImage getCurrentImage() {
        double percentage = (composants.size() * 1.0 / capacity) * 100.0;
        if  (percentage > 90) {
            return this.images.get(3);
        }
        else if (percentage > 66) {
            return this.images.get(2);
        }
        else if (percentage > 33) {
            return this.images.get(1);
        }
        else return this.images.get(0);
    }
}
