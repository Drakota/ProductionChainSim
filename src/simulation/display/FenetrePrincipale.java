package simulation.display;

import simulation.state.SimulationEtat;
import simulation.utils.XMLParser;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);

	public FenetrePrincipale() {
		PanneauPrincipal panneauPrincipal = new PanneauPrincipal();

		Environnement environnement = new Environnement();
		environnement.addPropertyChangeListener(panneauPrincipal);
		environnement.execute();

		MenuFenetre menuFenetre = new MenuFenetre();
		menuFenetre.addPropertyChangeListener(panneauPrincipal);

		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);

		// Faire en sorte que le X de la fen?tre ferme la fen?tre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);

		// Rendre la fen?tre visible
		setVisible(true);

		// Mettre la fen?tre au centre de l'?cran
		setLocationRelativeTo(null);

		// Emp?cher la redimension de la fen?tre
		setResizable(false);
	}
}
