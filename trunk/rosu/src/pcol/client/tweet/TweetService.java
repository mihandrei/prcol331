package pcol.client.tweet;

import java.util.List;

import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;
import pcol.shared.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("rpc/tweets")
public interface TweetService extends RemoteService {
	
	List<Tweet> getTweets(User usr, int limit)throws AuthenticationException;;
}
