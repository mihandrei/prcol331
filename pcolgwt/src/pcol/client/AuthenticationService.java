package pcol.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("auth")
public interface AuthenticationService extends RemoteService{
	
	boolean isSessionValid(String sessionid);

	String autenticate(String usr, String pwd);
}
