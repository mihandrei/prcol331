package pcol.client.useradmin;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UsrServiceAsync {

	void saveUser(String nume, int nrmatr, int an, int sectie, String grupa,
			String cont, AsyncCallback<Void> callback);

}
