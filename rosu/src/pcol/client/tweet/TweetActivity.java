package pcol.client.tweet;

import java.util.Arrays;

import pcol.client.TweetService;
import pcol.client.TweetServiceAsync;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.Tweet;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class TweetActivity extends AbstractActivity implements
		TweetView.Presenter {
	//viewurile si serviciu sunt singletoane
	private static TweetView cached_tweetView = null;
	private static TweetServiceAsync rpc = null;
	
	private AppAsyncCallback<Tweet[]> tweetResponseHandler = new AppAsyncCallback<Tweet[]>() {
		@Override
		public void onSuccess(Tweet[] result) {
			cached_tweetView.appendTweets(Arrays.asList(result));
		}

	};
	
	public TweetActivity() {
		
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		//experimental
		GWT.runAsync(new RunAsyncCallback() {
				
				@Override
				public void onSuccess() {
					if(rpc == null){
						rpc = GWT.create(TweetService.class);
					}
					
					if(cached_tweetView == null){
						cached_tweetView = new TweetViewImpl();
					}
					
					cached_tweetView.setPresenter(TweetActivity.this);
					cached_tweetView.clear();
					rpc.getTweets(5, tweetResponseHandler);
					
					panel.setWidget(cached_tweetView.asWidget());
				}
				
				@Override
				public void onFailure(Throwable reason) {
					Window.alert(reason.getLocalizedMessage());
				}
			});
	}

	@Override
	public void getMoreTweets() {
		rpc.getTweets(5, tweetResponseHandler);
	}

}
