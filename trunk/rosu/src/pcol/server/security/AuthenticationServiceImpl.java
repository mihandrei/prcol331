package pcol.server.security;

import javax.servlet.http.HttpSession;

import pcol.client.security.AuthenticationService;
import pcol.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**TODO autorizarea cu filtru e comoda, DAR se bazeaza doar pe cookie (vulnerabil la cross request)
*    Fiecare rpc secure ar tre sa trimita ca argument 
*    sid-ul cachat pe client in camp static la server    
*    unde se verifica daca sidul ii valid
*/
public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {	
	@Override
	public User getUserBySid(String sessionID) {		
		HttpSession session = getThreadLocalRequest().getSession();
//TODO: verifica daca checkul de mai jos nu e necesar		
//		if(session.getId().equals(sessionID)){ 
			return (User)session.getAttribute(Provider.SESSION_USER);
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
		User user = Provider.getInstance().doAuthenticate(usr, pwd);
		if(user!=null){
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute(Provider.SESSION_USER, user);
			user.setSid(session.getId()); 
		}
		return user;
	}
	
	@Override
	public void logout(){
		HttpSession session = getThreadLocalRequest().getSession();
		session.invalidate();
	}
	
}
