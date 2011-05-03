package pcol.client.tweet;

import java.util.List;

import pcol.shared.Tweet;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface TweetServiceAsync {

	void getTweets(String sid, int limit, AsyncCallback<List<Tweet>> callback);

	void sendTweet(String sid, String mesaj, String destinatie,
			AsyncCallback<Void> callback);

}
