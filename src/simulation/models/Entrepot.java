package simulation.models;

import simulation.utils.Observateur;
import java.awt.image.BufferedImage;

public class Entrepot extends Batiment implements Observateur {
    int capacity;

    public Entrepot(int id, int x, int y, String[] imgPaths, Integer capacity) {
        super(id, new Integer[] {x, y}, imgPaths);
        this.capacity = capacity;
    }

    @Override
    public void update() {
    }

    @Override
    public void nextTurn() {
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
