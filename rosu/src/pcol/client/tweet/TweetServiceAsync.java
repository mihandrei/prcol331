package pcol.client.tweet;

import pcol.shared.Tweet;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface TweetServiceAsync {

	void getTweets(int limit, AsyncCallback<Tweet[]> callback);

}
