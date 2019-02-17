package simulation.display;

import simulation.state.SimulationEtat;
import simulation.utils.XMLParser;

import java.awt.Graphics;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	///////////////////////////////// TO REMOVE //////////////////////////////////
	private SimulationEtat simState = new SimulationEtat(XMLParser.getInstance().readConfig("/home/drakota/Documents/Projects/Java/Lab1/src/ressources/configuration.xml"));

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("SELECTED_FILE")) {
			simState = new SimulationEtat(XMLParser.getInstance().readConfig(evt.getNewValue().toString()));
		}
		else if (evt.getPropertyName().equals("NEXT_TURN")) {
			simState.nextTurn();
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		simState.draw(g);
	}
}