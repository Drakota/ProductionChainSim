package simulation.models;

import simulation.composants.Aile;

import java.util.ArrayList;

public class UsineAile extends Usine {
    int metalRequired;

    public UsineAile(int id, int x, int y, String[] imgPaths, Integer productionInterval, Integer metalRequired) {
        super(id, x, y, imgPaths, productionInterval);
        this.metalRequired = metalRequired;
    }

    @Override
    public void nextTurn() {
        if (composants.size() >= metalRequired) {
            ++productionTimer;
            if (productionTimer.equals(productionInterval)) {
                composants = composants.subList(metalRequired, composants.size());
                productionTimer = 0;
                this.tradeRoute.addComposant(new Aile(coordinates.x, coordinates.y));
            }
        }
    }
}
