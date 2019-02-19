package simulation.models;

import simulation.composants.Composant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Batiment {
    protected Integer id;
    protected String type;
    protected List<BufferedImage> images;
    protected Point coordinates;
    protected List<Composant> composants;

    public Batiment(Integer id, Point coordinates, String[] imgPaths) {
        this.id = id;
        this.coordinates = coordinates;
        this.images = new ArrayList<>();
        this.composants = new ArrayList<>();
        try {
            for (String imgPath: imgPaths) {
                this.images.add(ImageIO.read(new File(imgPath)));
            }
        }
        catch (Exception e) {}
    }

    public Point getCoordinates() {
        return this.coordinates;
    }

    public Integer getId() {
        return this.id;
    }

    public abstract void nextTurn();

    public abstract void connect(Chemin chemin);

    public abstract void addComponent(Composant composant);

    public abstract BufferedImage getCurrentImage();
}
