package simulation.models;

import simulation.utils.Observateur;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Usine extends Batiment implements Observateur {
    protected Integer productionInterval;
    protected Integer productionTimer;
    protected Chemin tradeRoute;

    public Usine(int id, int x, int y, String[] imgPaths, Integer productionInterval) {
        super(id, new Integer[] {x, y}, imgPaths);
        this.productionInterval = productionInterval;
        this.productionTimer = 0;
    }

    @Override
    public void update() {
    }

    @Override
    public void connect(Chemin chemin) {
        super.connect(chemin);
        tradeRoute = chemin;
    }

    public abstract void nextTurn();

    @Override
    public BufferedImage getCurrentImage() {
        double percentage = (productionTimer * 1.0 / productionInterval) * 100.0;
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
