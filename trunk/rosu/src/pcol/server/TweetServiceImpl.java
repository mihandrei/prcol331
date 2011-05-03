package pcol.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import pcol.client.tweet.TweetService;
import pcol.server.domain.Logins;
import pcol.server.domain.MsgMessage;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;
import pcol.shared.Tweet.Level;

public class TweetServiceImpl extends AuthRemoteServiceServlet implements
		TweetService {
	static Logger log = Logger.getLogger(TweetServiceImpl.class.getName());
	
	@Override
	public List<Tweet> getTweets(String sid, int limit) throws AuthenticationException{
		return withUser(sid, new UserCall<List<Tweet>>() {
			@Override
			public List<Tweet> call(Logins user, Session session) {
				session.beginTransaction();
				Query res = session
				.createQuery(
						"select distinct m from Logins as u " +
						"join u.msgChannels as c " +
						"join c.msgMessages as m " +
						"where u.loginName=:login " +
				"order by m.date desc")
				.setParameter("login", user.getLoginName());

				List<MsgMessage> msgs = res.list();
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
	public void sendTweet(String sid, String mesaj, String destinatie) {
		// TODO Auto-generated method stub
		
	}
}
