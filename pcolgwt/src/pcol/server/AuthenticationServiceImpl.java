package pcol.server;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.client.AuthenticationService;
import pcol.server.domain.Users;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {

	/**
	 * interogez db daca exista userul si daca parola ii ok
	 * updatez lastlogin
	 * TODO: fa-l pe lastlogin de tip date
	 */
	private boolean doAuthenticate(String usrname,String pwd){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Users user = (Users) session.get(Users.class, usrname); //da-mi userul cu numele asta
			session.getTransaction().commit();
			if( user!=null && pwd.equals(user.getPasswordHash())){
				user.setLastlogin(new Date().toString());
				session.update(user);
				return true;
			}
			return false;
		} finally {
			session.close();
		}
	}
	
	//FIXME: verifica securitatea
	//clientul deja stie tokenul asta , 
	//il pune filtrul de sesiune intr-un cookie
	//Are sens sa trimit tokenul doar pt a spori securitatea.
	//ar avea sens intr-o situatie REST da aici oricum clientul e intodeauna gwt
	@Override
	public String autenticate(String usrname,String pwd){
		
		if(doAuthenticate(usrname, pwd)){
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute("usr", usrname);
			return session.getId(); 
		}else{
			return "";
		}
	}

	@Override
	public boolean isSessionValid(String sessionid) {
		HttpSession session = getThreadLocalRequest().getSession();
		return session.getId().equals(sessionid); 
	}
}
