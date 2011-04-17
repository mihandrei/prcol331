package pcol.client.security;

import java.util.Date;
import java.util.logging.Logger;

import pcol.client.App;
import pcol.client.security.LoginView.Presenter;
import pcol.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * toate metodele de verifica credentiale sunt asincrone si
 *  pe cand se termina posteaza pe eventbus un loginevent
 * @author miha
 */
public class LoginManager implements Presenter {
	private static final Logger log = Logger.getLogger(LoginManager.class.getName());
	private User user;
	private LoginView loginView  =  new LoginScreen();
	private String authToken = null;
	private AuthenticationServiceAsync authService = GWT.create(AuthenticationService.class);
	
	public LoginManager(){
		loginView.setPresenter(this);
	}
	
	/**
	 * incearca login automat daca sesiunea de lucru e inca activa
	 * Altfel arata formul de login
	 */
	public void requestInitialLogin(){
		final String sessionID = Cookies.getCookie("sid");
		if (sessionID != null) {
			log.fine("request cookie login");
			authService.getUserBySid(sessionID, new AppAsyncCallback<User>() {
				@Override
				public void onSuccess(User usr) {
						authToken = sessionID;
						setcookie(authToken);
						LoginManager.this.user=usr;
						App.getInstance().getEventBus().fireEvent(new LoginEvent(usr));
				}

			});
		}else{
			loginView.showscreen();
		}
	}
	
	public void onAuthenticationException(){
		//presupunem ca o expirat sesiunea, cerem relog
		loginView.showscreen();
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
		loginView.reset();
	}

	@Override
	public void login(String usr, String pwd) {
		authService.authenticate(usr,pwd,new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				// TODO Auto-generated method stub
				if(user!=null){
					authToken = user.getSid();
					setcookie(authToken);
					LoginManager.this.user = user;
					loginView.hide();
					App.getInstance().getEventBus().fireEventFromSource(new LoginEvent(user), this);
				}else{
					loginView.showLoginError();
				}
			}

			@Override
			public void onFailure(Throwable ex) {
				Window.alert(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
		});
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