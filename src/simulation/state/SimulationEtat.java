package simulation.state;

import simulation.display.Simulation;
import simulation.stategies.Strategie;
import simulation.models.*;
import simulation.utils.Observable;
import simulation.utils.Observateur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationEtat {
    private List<Batiment> batiments = new ArrayList<>();
    private List<Chemin> chemins = new ArrayList<>();
    private Strategie strategie;

    public List<Batiment> createBatiments(List<Map<String, String>> usineTypes, List<Map<String, String>> usines) {
        List<Batiment> batimentsList = new ArrayList<>();
        for (Map<String, String> usine: usines) {
            for (Map<String, String> usineType: usineTypes) {
                if (usine.get("type").equals(usineType.get("type"))) {
                    switch (usine.get("type")) {
                        case "usine-matiere":
                            batimentsList.add(
                                new UsineMetal(
                                        Integer.parseInt(usine.get("id")),
                                        Integer.parseInt(usine.get("x")),
                                        Integer.parseInt(usine.get("y")),
                                        new String[] { usineType.get("vide"), usineType.get("un-tiers"), usineType.get("deux-tiers"), usineType.get("plein") },
                                        Integer.parseInt(usineType.get("interval"))
                                ));
                            break;
                        case "usine-aile":
                            batimentsList.add(
                                    new UsineAile(
                                            Integer.parseInt(usine.get("id")),
                                            Integer.parseInt(usine.get("x")),
                                            Integer.parseInt(usine.get("y")),
                                            new String[] { usineType.get("vide"), usineType.get("un-tiers"), usineType.get("deux-tiers"), usineType.get("plein") },
                                            Integer.parseInt(usineType.get("interval")),
                                            Integer.parseInt(usineType.get("metal"))
                                    ));
                            break;
                        case "usine-moteur":
                            batimentsList.add(
                                    new UsineMoteur(
                                            Integer.parseInt(usine.get("id")),
                                            Integer.parseInt(usine.get("x")),
                                            Integer.parseInt(usine.get("y")),
                                            new String[] { usineType.get("vide"), usineType.get("un-tiers"), usineType.get("deux-tiers"), usineType.get("plein") },
                                            Integer.parseInt(usineType.get("interval")),
                                            Integer.parseInt(usineType.get("metal"))
                                    ));
                            break;
                        case "usine-assemblage":
                            batimentsList.add(
                                    new UsineAssemblage(
                                            Integer.parseInt(usine.get("id")),
                                            Integer.parseInt(usine.get("x")),
                                            Integer.parseInt(usine.get("y")),
                                            new String[] { usineType.get("vide"), usineType.get("un-tiers"), usineType.get("deux-tiers"), usineType.get("plein") },
                                            Integer.parseInt(usineType.get("interval")),
                                            Integer.parseInt(usineType.get("aile")),
                                            Integer.parseInt(usineType.get("moteur"))
                                    ));
                            break;
                        case "entrepot":
                            batimentsList.add(
                                    new Entrepot(
                                            Integer.parseInt(usine.get("id")),
                                            Integer.parseInt(usine.get("x")),
                                            Integer.parseInt(usine.get("y")),
                                            new String[] { usineType.get("vide"), usineType.get("un-tiers"), usineType.get("deux-tiers"), usineType.get("plein") },
                                            Integer.parseInt(usineType.get("avion"))
                                    ));
                            break;
                        default:
                            break;

                    }
                }
            }
        }
        return batimentsList;
    }

    public void subscribeToWarehouse() {
        List<Batiment> tempBatiments = new ArrayList(this.batiments);
        Batiment entrepot = null;
        for (Batiment batiment: tempBatiments) {
            if (batiment.getClass() == Entrepot.class) {
                entrepot = batiment;
                tempBatiments.remove(batiment);
                break;
            }
        }
        for (Batiment batiment: tempBatiments) {
            ((Entrepot)entrepot).addObserver((Usine)batiment);
        }
    }

    public List<Chemin> createRoutes(List<Map<String, String>> routes) {
        List<Chemin> chemins = new ArrayList<>();
        for (Map<String, String> route: routes) {
            Batiment destinateur = findBatimentByID(Integer.parseInt(route.get("de")));
            Batiment destinataire = findBatimentByID(Integer.parseInt(route.get("vers")));
            Chemin chemin = new Chemin(destinateur, destinataire);
            destinateur.connect(chemin);
            chemins.add(chemin);
        }
        return chemins;
    }

    public Batiment findBatimentByID(Integer id) {
        for (Batiment batiment: this.batiments) {
            if (batiment.getId().equals(id)) {
                return batiment;
            }
        }
        return null;
    }

    public SimulationEtat(Object[] config) {
        this.batiments = createBatiments((List<Map<String, String>>)config[0],(List<Map<String, String>>)config[1]);
        subscribeToWarehouse();
        this.chemins = createRoutes((List<Map<String, String>>)config[2]);
    }

    public void draw(Graphics g) {
        for (Chemin chemin: chemins) {
            chemin.draw(g);
        }
        for (Batiment batiment: batiments) {
            g.drawImage(batiment.getCurrentImage(), batiment.getCoordinates().x, batiment.getCoordinates().y, null);
        }
    }

    public void nextTurn() {
        for (Batiment batiment: batiments) {
            batiment.nextTurn();
        }
        for (Chemin chemin: chemins) {
            chemin.nextTurn();
        }
    }
}
