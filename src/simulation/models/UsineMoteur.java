package simulation.models;

import simulation.composants.Moteur;

public class UsineMoteur extends Usine {
    int metalRequired;

    public UsineMoteur(int id, int x, int y, String[] imgPaths, Integer productionInterval, Integer metalRequired) {
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
                this.tradeRoute.addComposant(new Moteur(getX(), getY()));
            }
        }
    }
}
