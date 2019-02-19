package simulation.composants;

import simulation.utils.Observateur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Composant {
    private Point coordinates;
    private BufferedImage image;

    public Point getCoordinates() {
        return coordinates;
    }

    public BufferedImage getImage() { return image; };

    public Composant(int x, int y, String imgPath) {
        this.coordinates = new Point(x, y);
        try {
            this.image = ImageIO.read(new File(imgPath));
        }
        catch (Exception e) {}
    }
}
