package simulation.models;

import simulation.composants.Composant;
import simulation.models.Batiment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chemin {
    private Batiment sender;
    private Batiment receiver;
    private Integer[] speedVec;
    private List<Composant> composants;

    public Chemin(Batiment sender, Batiment receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.composants = new ArrayList<>();
        this.speedVec = calculateSpeedVec();
    }

    public Integer[] calculateSpeedVec() {
        int speedX;
        int speedY;
        if (sender.getX() - receiver.getX() == 0)
            speedX = 0;
        else if (sender.getX() - receiver.getX() > 0)
            speedX = -1;
        else speedX = 1;
        if (sender.getY() - receiver.getY() == 0)
            speedY = 0;
        else if (sender.getY() - receiver.getY() > 0)
            speedY = -1;
        else speedY = 1;
        return new Integer[] {speedX, speedY};
    }

    public void addComposant(Composant composant) {
        this.composants.add(composant);
    }

    public void nextTurn() {
        Iterator<Composant> i = composants.iterator();
        while (i.hasNext()) {
            Composant composant = i.next(); // must be called before you can call i.remove()

            composant.setX(composant.getX() + speedVec[0]);
            composant.setY(composant.getY() + speedVec[1]);

            if (composant.getX() == receiver.getX() && composant.getY() == receiver.getY())
            {
                i.remove();
                receiver.addComponent(composant);
            }
        }
    }

    public void draw(Graphics g) {
        g.drawLine(sender.getX() + sender.getCurrentImage().getWidth() / 2, sender.getY() + sender.getCurrentImage().getHeight() / 2,
                receiver.getX() + receiver.getCurrentImage().getWidth() / 2, receiver.getY() + receiver.getCurrentImage().getHeight() / 2);
        for (Composant composant: composants) {
            g.drawImage(composant.getImage(), composant.getX(), composant.getY(), null);
        }
    }
}
