package pcol.server;

import javax.servlet.http.HttpSession;

import pcol.client.security.AuthenticationService;
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
	private User doAuthenticate(String usrname,String pwd){
		if(usrname.equals("mihai") && pwd.equals("mihai")){
			return new User("mihai", "andrei mihai daniel", 1040,
					new String[]{"noutati","orar","materii","teme","contract"}, "");
		}else{
			return null;
		}
	}

}
