package simulation.models;

import simulation.composants.Aile;
import simulation.composants.Avion;
import simulation.composants.Composant;
import simulation.composants.Moteur;

import java.util.Iterator;

public class UsineAssemblage extends Usine {
    int moteursRequired;
    int ailesRequired;

    public UsineAssemblage(int id, int x, int y, String[] imgPaths, Integer productionInterval, Integer ailesRequired, Integer moteursRequired) {
        super(id, x, y, imgPaths, productionInterval);
        this.ailesRequired = ailesRequired;
        this.moteursRequired = moteursRequired;
    }

    public Integer checkStock(Class className) {
        int i = 0;
        for (Composant composant: composants) {
            if (composant.getClass() == className)
                i++;
        }
        return  i;
    }

    public void removeComponent(Class className, int counterLimit) {
        int counter = 0;
        Iterator<Composant> i = composants.iterator();
        while (counter != counterLimit) {
            Composant composant = i.next();
            if (composant.getClass() == className)
            {
                i.remove();
                counter++;
            }
        }
    }

    @Override
    public void nextTurn() {
        if (checkStock(Moteur.class) >= moteursRequired && checkStock(Aile.class) >= ailesRequired) {
            ++productionTimer;
            if (productionTimer.equals(productionInterval)) {
                removeComponent(Moteur.class, moteursRequired);
                removeComponent(Aile.class, ailesRequired);
                productionTimer = 0;
                this.tradeRoute.addComposant(new Avion(coordinates.x, coordinates.y));
            }
        }
    }
}
