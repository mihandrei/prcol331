package pcol.client.contract;

import pcol.shared.Curicul;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContractServiceAsync {

	void getCuricula(int nrmatricol, AsyncCallback<Curicul> callback);

	void submitContract(Curicul c, AsyncCallback<Void> callback);

}
