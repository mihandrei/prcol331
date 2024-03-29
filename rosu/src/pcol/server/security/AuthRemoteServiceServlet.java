package pcol.server.security;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.server.HibernateUtil;
import pcol.server.domain.Logins;
import pcol.shared.AuthenticationException;
import pcol.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthRemoteServiceServlet extends RemoteServiceServlet{
	
	public User getUserBySid(String sessionID) throws AuthenticationException {		
		HttpSession session = getThreadLocalRequest().getSession();

		if(session.getId().equals(sessionID)){ 
			return (User)session.getAttribute(Provider.SESSION_USER);
		}else{
			//aici se ajunge in caz de cross request
			//sau in cazul unui login automat cu cookie expirata
			throw new AuthenticationException();
		}
	}
	
	protected interface UserCall<V>{
		V call(Logins usr,Session s);
	}
	
	protected <V> V withUser(String sid, UserCall<V> fn) throws AuthenticationException {
		User usr = getUserBySid(sid);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Logins user = (Logins) session.get(Logins.class, usr.getLoginName());
//fixme:	if( user==null) o sters careva userul, business fct care sterge usr tre sa expire loginurile
			session.getTransaction().commit();
			
			return fn.call(user, session);
		} finally {
			session.close();
		}
	}
}
