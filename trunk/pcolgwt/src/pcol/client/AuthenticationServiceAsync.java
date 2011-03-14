package pcol.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {

	void autenticate(String text, String text2,
			AsyncCallback<String> asyncCallback);

	void isSessionValid(String sessionid, AsyncCallback<Boolean> callback);

}
