package pcol.client.tweet;

import java.util.List;

import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("rpc/tweets")
public interface TweetService extends RemoteService {
	
	List<Tweet> getTweets(String sid, int limit)throws AuthenticationException;
}
