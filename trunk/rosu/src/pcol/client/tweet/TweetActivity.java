package pcol.client.tweet;

import java.util.Arrays;

import pcol.client.TweetService;
import pcol.client.TweetServiceAsync;
import pcol.shared.Tweet;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class TweetActivity extends AbstractActivity implements
		TweetView.Presenter {
	
	private TweetView tweetView;
	private TweetServiceAsync rpc = GWT.create(TweetService.class);
	
	private AsyncCallback<Tweet[]> tweetResponseHandler = new AsyncCallback<Tweet[]>() {
		@Override
		public void onSuccess(Tweet[] result) {
			tweetView.appendTweets(Arrays.asList(result));
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
		}
	};
	
	public TweetActivity(TweetView tweetView) {
		this.tweetView=tweetView;
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		tweetView.setPresenter(this);
		tweetView.clear();
		rpc.getTweets(5, tweetResponseHandler);

		panel.setWidget(tweetView.asWidget());
	}

	@Override
	public void getMoreTweets() {
		rpc.getTweets(5, tweetResponseHandler);
	}

}
