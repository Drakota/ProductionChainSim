package simulation.display;

import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.List;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 10;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			firePropertyChange("NEXT_TURN", null, null);
		}
		return null;
	}

}