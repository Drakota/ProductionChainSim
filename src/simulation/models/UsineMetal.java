package simulation.models;

import simulation.composants.Metal;

public class UsineMetal extends Usine {
    public UsineMetal(int id, int x, int y, String[] imgPaths, Integer productionInterval) {
        super(id, x, y, imgPaths, productionInterval);
    }

    @Override
    public void nextTurn() {
        ++productionTimer;
        if (productionTimer.equals(productionInterval)) {
            productionTimer = 0;
            this.tradeRoute.addComposant(new Metal(coordinates.x, coordinates.y));
        }
    }
}
