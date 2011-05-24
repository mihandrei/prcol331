package pcol.server.blogic;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pcol.server.domain.Logins;
import pcol.server.domain.MsgChannel;
import pcol.server.domain.MsgMessage;

public class Mesaje {
	@SuppressWarnings("unchecked")
	public static List<MsgMessage> getTweets(String loginname, int limit, Session session) {
		Query res = session.createQuery(
				"select distinct m from Logins as u "
						+ "join u.msgChannels as c "
						+ "join c.msgMessages as m "
						+ "where u.loginName=:login " 
						+ "order by m.date desc")
				.setParameter("login", loginname);

		return res.list();
	}

	public static void saveTweet(Logins login, String mesaj, String destinatie, Session session) {
		MsgMessage msg = new MsgMessage(login, mesaj, "INFO", new Date());
		MsgChannel chan = (MsgChannel) session.get(MsgChannel.class, destinatie);
		msg.getMsgChannels().add(chan);
		session.persist(msg);
	}

	public static void subscribe(Logins login, MsgChannel msgChannel, Session session) {
		login.getMsgChannels().add(msgChannel);
		session.persist(login);
	}

	public static void unSubscribeAll(Logins login, Session session) {
		login.getMsgChannels().clear();
		session.persist(login);
		
	}
}
