package pcol.client.contract;


import pcol.shared.Curicul;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("contract")
public interface ContractService extends RemoteService{
	public Curicul getCuricula(int nrmatricol);
	public void submitContract(Curicul c);
}
