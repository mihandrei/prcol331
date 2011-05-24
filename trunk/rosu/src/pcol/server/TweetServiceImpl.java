package pcol.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;

import pcol.client.tweet.TweetService;
import pcol.server.blogic.Mesaje;
import pcol.server.domain.Logins;
import pcol.server.domain.MsgMessage;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;
import pcol.shared.Tweet.Level;

@SuppressWarnings("serial")
public class TweetServiceImpl extends AuthRemoteServiceServlet implements
		TweetService {
	static Logger log = Logger.getLogger(TweetServiceImpl.class.getName());
	
	@Override
	public List<Tweet> getTweets(String sid, final int limit) throws AuthenticationException{
		return withUser(sid, new UserCall<List<Tweet>>() {
			@Override
			public List<Tweet> call(Logins user, Session session) {
				session.beginTransaction();

				List<MsgMessage> msgs = Mesaje.getTweets(user.getLoginName(), limit, session);
				List<Tweet> ret = new ArrayList<Tweet>();
				for(MsgMessage m :msgs){
					ret.add(new Tweet(m.getMsg(), m.getLogins().getLoginName(),
							m.getDate(), Level.valueOf(m.getLevel()) ));
				}
				session.getTransaction().commit();
				return ret;
			}
		});
	}

	@Override
	public void sendTweet(String sid, final String mesaj, final String destinatie) throws AuthenticationException {
		withUser(sid, new UserCall<Void>() {
			@Override
			public Void call(Logins usr, Session s) {
				Mesaje.sendTweet(usr, mesaj, destinatie, s);
				return null;
			}
		});
	}
}
