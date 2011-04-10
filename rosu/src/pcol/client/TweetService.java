package pcol.client;

import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("tweets")
public interface TweetService extends RemoteService {
	
	Tweet[] getTweets(int limit) throws AuthenticationException;
}
