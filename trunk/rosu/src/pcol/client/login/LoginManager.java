package pcol.client.login;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoginManager {
	String authToken = null;
	
	public boolean isAuthenticated(){
		final boolean isauth = false;
		
		String sessionID = Cookies.getCookie("sid");
		if (sessionID != null) {
//			authService.isSessionValid(sessionID, new AsyncCallback<Boolean>() {
//				@Override
//				public void onSuccess(Boolean sessionvalid) {
//					isauth =  sessionvalid;
//				}
//
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert(caught.getLocalizedMessage());
//				}
//			});
		}
		return isauth;
	}
//public invokeCommand
//on auth failure show login
// #teme/bd   -> rpc -> login fail -> hichjack history -> show login ui
//on login ok : set auth token && cookie && refire history 
}
