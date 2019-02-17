package simulation.models;

import simulation.composants.Composant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Batiment {
    protected Integer id;
    protected String type;
    protected List<BufferedImage> images;
    protected Integer[] coordinates;
    protected List<Composant> composants;

    public Batiment(Integer id, Integer[] coordinates, String[] imgPaths) {
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

    public Integer getX() {
        return this.coordinates[0];
    }

    public Integer getY() {
        return this.coordinates[1];
    }

    public Integer getId() {
        return this.id;
    }

    public abstract void nextTurn();

    public void connect(Chemin chemin){}

    public void addComponent(Composant composant) {
        composants.add(composant);
    }

    public abstract BufferedImage getCurrentImage();
}
