package simulation.composants;

import simulation.utils.Observateur;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Composant implements Observateur {
    private Integer[] coordinates;
    private BufferedImage image;

    public int getX() {
        return coordinates[0];
    }

    public int getY() {
        return coordinates[1];
    }

    public void setX(int x) {
        coordinates[0] = x;
    }

    public void setY(int y) {
        coordinates[1] = y;
    }

    public BufferedImage getImage() { return image; };

    public Composant(int x, int y, String imgPath) {
        this.coordinates = new Integer[] {x, y};
        try {
            this.image = ImageIO.read(new File(imgPath));
        }
        catch (Exception e) {}
    }

    @Override
    public void update() {
    }
}
