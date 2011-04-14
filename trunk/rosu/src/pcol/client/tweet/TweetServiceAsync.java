package pcol.client.tweet;

import java.util.List;

import pcol.shared.Tweet;
import pcol.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface TweetServiceAsync {

	void getTweets(User usr, int limit, AsyncCallback<List<Tweet>> callback);

}
