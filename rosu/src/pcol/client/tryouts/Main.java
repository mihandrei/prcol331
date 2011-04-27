package pcol.client.tryouts;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Main implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(new UploadView());

	}

}
