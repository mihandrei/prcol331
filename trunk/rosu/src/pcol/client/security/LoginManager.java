package pcol.client.security;

import java.util.Date;
import java.util.logging.Logger;

import pcol.shared.AuthenticationException;
import pcol.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * toate metodele de verifica credentiale sunt asincrone si
 *  pe cand se termina posteaza pe eventbus un loginevent
 * @author miha
 */
public class LoginManager{
	private static final Logger log = Logger.getLogger(LoginManager.class.getName());
	private User user;
	private String authToken = null;
	private AuthenticationServiceAsync authService = GWT.create(AuthenticationService.class);
	private EventBus eventBus;
	
	public LoginManager(EventBus eventBus){
		this.eventBus=eventBus;
	}
	
	/**
	 * incearca login automat daca sesiunea de lucru e inca activa
	 * lanseaza un AutoLoginEvent pe bus. parametrul user e null daca 
	 * autologinul a esuat
	 */
	public void requestInitialLogin(){
		final String sessionID = Cookies.getCookie("sid");
		if (sessionID != null) {
			log.fine("request cookie login");
			authService.getUserBySid(sessionID, new AsyncCallback<User>() {
				@Override
				public void onSuccess(User usr) {
					authToken = sessionID;
					setcookie(authToken);
					LoginManager.this.user=usr;
					eventBus.fireEvent(new AutoLoginEvent(usr));
				}

				@Override
				public void onFailure(Throwable ex) {
					if(ex instanceof AuthenticationException) { 
						eventBus.fireEvent(new AutoLoginEvent(null));
					}else{
						Window.alert(ex.getLocalizedMessage());
						ex.printStackTrace();
					}
				}
			});
		}else{
			eventBus.fireEvent(new AutoLoginEvent(null));
		}
	}
	
	public void logout() {
		authService.logout(new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		Cookies.removeCookie("sid");
	}

	public void login(String usr, String pwd) {
		authService.authenticate(usr,pwd,new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if(user!=null){
					authToken = user.getSid();
					setcookie(authToken);
					LoginManager.this.user = user;
					eventBus.fireEvent(new LoginEvent(user));
				}else{
					eventBus.fireEvent(new LoginEvent(null));
				}
			}

			@Override
			public void onFailure(Throwable ex) {
				Window.alert(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
		});
	}
	
	public void relogin(String pwd){
		login(user.getLoginName(),pwd);
	}
	
	private void setcookie(String sessionid){
		Date expires = new Date(System.currentTimeMillis() + 1000 * 60
				* 60 * 1);
		Cookies.setCookie("sid", sessionid, expires, null, "/", false);
	}
	
	public User getUser() {
		return user;
	}
}
//public invokeCommand
//on auth failure show login
// #teme/bd   -> rpc -> login fail -> hichjack history -> show login ui
//on login ok : set auth token && cookie && refire history 