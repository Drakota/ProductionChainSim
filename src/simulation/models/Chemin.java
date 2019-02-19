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
        Point sendCoords = sender.getCoordinates();
        Point recCoords = receiver.getCoordinates();
        if (sendCoords.x - recCoords.x == 0)
            speedX = 0;
        else if (sendCoords.x - recCoords.x > 0)
            speedX = -1;
        else speedX = 1;
        if (sendCoords.y - recCoords.y == 0)
            speedY = 0;
        else if (sendCoords.y - recCoords.y > 0)
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

            composant.getCoordinates().translate(speedVec[0], speedVec[1]);

            if (composant.getCoordinates().x == receiver.getCoordinates().x && composant.getCoordinates().y == receiver.getCoordinates().y)
            {
                i.remove();
                receiver.addComponent(composant);
            }
        }
    }

    public void draw(Graphics g) {
        Point sendCoords = sender.getCoordinates();
        Point recCoords = receiver.getCoordinates();
        g.drawLine(sendCoords.x + sender.getCurrentImage().getWidth() / 2, sendCoords.y + sender.getCurrentImage().getHeight() / 2,
                recCoords.x + receiver.getCurrentImage().getWidth() / 2, recCoords.y + receiver.getCurrentImage().getHeight() / 2);
        for (Composant composant: composants) {
            g.drawImage(composant.getImage(), composant.getCoordinates().x, composant.getCoordinates().y, null);
        }
    }
}
