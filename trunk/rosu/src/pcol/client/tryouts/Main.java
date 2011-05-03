package pcol.client.tryouts;


import pcol.client.profesor.ProfesorActivity;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Main implements EntryPoint {

	@Override
	public void onModuleLoad() {
		ProfesorActivity a = new ProfesorActivity();
		SimplePanel pnl = new SimplePanel();
		RootLayoutPanel.get().add(pnl);
		
		a.start(pnl, null);

	}

}
