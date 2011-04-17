package pcol.client.security;

import pcol.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {

	void getUserBySid(String sessionID, AsyncCallback<User> asyncCallback);

	void authenticate(String usr, String pwd, AsyncCallback<User> asyncCallback);

	void logout(AsyncCallback<Void> callback);

}
