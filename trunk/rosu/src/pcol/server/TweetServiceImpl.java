package pcol.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.client.tweet.TweetService;
import pcol.server.domain.Mesaje;
import pcol.server.domain.Users;
import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;
import pcol.shared.Tweet.Level;
import pcol.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TweetServiceImpl extends RemoteServiceServlet implements
		TweetService {
	static Logger log = Logger.getLogger(TweetServiceImpl.class.getName());
	
	@Override
	public List<Tweet> getTweets(User usr, int limit) throws AuthenticationException{
		List<Tweet> ret = new ArrayList<Tweet>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Users user = (Users) session.get(Users.class, usr.getUsr());
			if( user==null){
				throw new AuthenticationException();
			}
			
			//TODO: conceptual vreu un user.getInomingMessages 
			//care sa fie un fel de many2many peste substriptii-canale-msgchan
			//oare este vreo anotare?
			//daca nu rescris in HQL
			Query res = session
			    .createSQLQuery("select m.* from mesaje m , msg_chan mc, subscriptii s "+
			 	    "where m.id=mc.msgid  and s.id_canal=mc.chanid and login=:login")
				.addEntity(Mesaje.class)
			    .setParameter("login", usr.getUsr());

			List<Mesaje> msgs = res.list();
			
			for(Mesaje m :msgs){
				ret.add(new Tweet(m.getMsg(), m.getUsers().getLoginName(),
						m.getDate(), Level.valueOf(m.getLevel()) ));
			}
			session.getTransaction().commit();
			return ret;
		} finally {
			session.close();
		}
	}

}
