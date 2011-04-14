package pcol.server;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.client.security.AuthenticationService;
import pcol.server.domain.Users;
import pcol.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {	
	@Override
	public User getUserBySid(String sessionID) {		
		HttpSession session = getThreadLocalRequest().getSession();
//TODO: verifica daca checkul de mai jos nu e necesar		
//		if(session.getId().equals(sessionID)){ 
			return (User)session.getAttribute("user");
//		}else{
//			return null;
//		}
	}

	//FIXME: verifica securitatea
	//clientul deja stie tokenul asta , 
	//il pune filtrul de sesiune intr-un cookie
	//Are sens sa trimit tokenul doar pt a spori securitatea.
	//ar avea sens intr-o situatie REST da aici oricum clientul e intodeauna gwt
	@Override
	public User authenticate(String usr, String pwd) {
		User user = doAuthenticate(usr, pwd);
		if(user!=null){
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute("user", user);
			user.setSid(session.getId()); 
		}
		return user;
	}
	@Override
	public void logout(){
		HttpSession session = getThreadLocalRequest().getSession();
		session.invalidate();
	}
	
	private User doAuthenticate(String login,String pwd){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Users user = (Users) session.get(Users.class, login); //da-mi userul cu numele asta
			session.getTransaction().commit();
			if( user!=null && pwd.equals(user.getPasswordHash())){
				user.setLastlogin(new Date().toString());
				session.update(user);
				
				return new User(login, 
					user.getPersoane().getNume() + " " + user.getPersoane().getPrenume(),
					user.getStudentis().iterator().next().getNrMatr(),new String[]{"noutati","orar","teme","contract"},"");
			
			}
			return null;
		} finally {
			session.close();
		}
	}

}
