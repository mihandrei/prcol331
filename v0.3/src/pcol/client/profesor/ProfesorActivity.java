package pcol.client.profesor;

import pcol.client.tweet.TweetService;
import pcol.client.tweet.TweetServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ProfesorActivity extends AbstractActivity {
	// viewurile si serviciu sunt singletoane
	private static ProfesorBinder cached_ProfesorBinder = null;
	private static TweetServiceAsync rpc = null;

	public ProfesorActivity() {

	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		// experimental

		if (rpc == null) {
			rpc = GWT.create(TweetService.class);
		}

		if (cached_ProfesorBinder == null) {
			cached_ProfesorBinder = new ProfesorBinder();
		}

		cached_ProfesorBinder.setPresenter(this);

		panel.setWidget(cached_ProfesorBinder.asWidget());

	}

	public void send(String text) {
		// TODO Auto-generated method stub
		
	}

}
