package pcol.client.tweet;

import java.util.List;

import pcol.client.AppLoader;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.Tweet;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class SendTweetActivity extends AbstractActivity implements
		SendTweetView.Presenter {
	private static SendTweetView view = null;
	private static TweetServiceAsync rpc = null;
	private SendTweetPlace place;
	
	public SendTweetActivity(SendTweetPlace place) {
		this.place = place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if(rpc == null){
			rpc = GWT.create(TweetService.class);
		}
		
		if(view == null){
			view = new SendTweetViewImpl();
		}
		
		view.setPresenter(SendTweetActivity.this);
		view.setChannel(place.getDest());
		panel.setWidget(view.asWidget());
				
	}

	@Override
	public void send() {
		rpc.sendTweet(AppLoader.getApp().getSid(), view.getMsg(), view.getChan(), new AppAsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void arg0) {
				AppLoader.getApp().showInfo("mesaj trimis");
			}
			
		});
	}

}
