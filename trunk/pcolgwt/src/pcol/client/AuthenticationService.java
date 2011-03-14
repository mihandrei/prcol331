package pcol.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Servicul ofera autentificare
 * 
 * Serverul tre sa implementeze interfata
 * GWT creaza pe client o clasa cu interfata asincrona 
 * corespunzatoare (AuthenticationServiceAsync)
 * Clasa creata pe client trimite request la server cand 
 * ii sunt apelate metodele 
 *
 * annotarea zice pe ce url asculta serviciul pe server
 * corespunde maparii de servlet din web.xml
 */
@RemoteServiceRelativePath("auth")
public interface AuthenticationService extends RemoteService{
	
	boolean isSessionValid(String sessionid);

	String autenticate(String usr, String pwd);
}
