package pcol.client.materii;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MaterieActivity extends AbstractActivity implements
		MaterieView.Presenter {
	private static Logger log = Logger.getLogger(MaterieActivity.class
			.getName());

	private static MaterieView materieView;
	private MateriePlace place;

	public MaterieActivity(MateriePlace place) {
		this.place = place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {

			@Override
			public void onSuccess() {
				if (materieView == null) {
					materieView = new MaterieViewImpl();
				}
				materieView.setPresenter(MaterieActivity.this);
				materieView.setMateriale("Miller, Bradley, and David Ranum. Problem Solving "
						+ "with Algorithms and Data Structures Using Python. Wilsonville, OR: "
						+ "Franklin, Beedle and Associates,");
				materieView.setName(place.getMaterieid());
				panel.setWidget(materieView.asWidget());
			}

			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}
