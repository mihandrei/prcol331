package pcol.server;

import java.util.Date;
//import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

//import javax.persistence.EntityManager;
//import javax.persistence.Query;

import pcol.client.TweetService;
import pcol.shared.AuthenticationException;
import pcol.shared.Tweet;
import pcol.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class TweetServiceImpl extends RemoteServiceServlet implements
		TweetService {
	static Logger log = Logger.getLogger(TweetServiceImpl.class.getName());
	static Tweet[] mocktweets = new Tweet[] {
			new Tweet("<p>Yesterday,<br>"
					+ "All those backups seemed a waste of pay.<br>"
					+ "Now my database has gone away.<br>"
					+ "Oh I believe in yesterday.</p>", "The Beatles",
					new Date(1983, 1, 1), Tweet.Level.CRITICAL),
			new Tweet(
					"You can never find general mechanical means <br/>"
							+ "for predicting the acts of computing machines <br/>"
							+ "It’s something that cannot be done. So we users <br/>"
							+ "must find our own bugs. Our computers are losers! <br/>"
							+ "<a href='http://www.lel.ed.ac.uk/~gpullum/loopsnoop.pdf' target='_blank'> source </a>",
					"Pullum", new Date(2000, 10, 2), Tweet.Level.INFO),
			new Tweet(
					"I have no money, no resources, no hopes. I am the happiest man alive.",
					"Henri Miller", new Date(), Tweet.Level.INFO),
			new Tweet(
					"Debugging is twice as hard as writing the code in the first place.  Therefore, if you write the code as cleverly as possible, you are–by definition–not smart enough to debug it.",
					"Ana Lugojana", new Date(), Tweet.Level.TASK),
			new Tweet(
					"Atunci când sunt supărat, mă retrag între oile mele şi mă liniştesc",
					"Gigi becali", new Date(), Tweet.Level.CRITICAL) };

	@Override
	public Tweet[] getTweets(int limit) throws AuthenticationException {
		//astea tre intr-un filtru
		HttpSession session = getThreadLocalRequest().getSession();
		if (session.getAttribute("user")==null){
			throw new AuthenticationException();
		}
		//end filtru
		
//		EntityManager em = EMF.get().createEntityManager();
//
//		try {
//			em.persist(new pcol.server.domain.Tweet(
//					"the streets have become green"));
//
//			Query q = em.createQuery("SELECT x FROM Tweet x");
//
//			List<pcol.server.domain.Tweet> results = (List<pcol.server.domain.Tweet>) q
//					.getResultList();
//			log.warning(results.size() + "");
//		} finally {
//			em.close();
//		}
		return mocktweets;
	}

}
