package pcol.client;


import pcol.shared.Curicul;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("contractService")
public interface ContractService extends RemoteService{
	public Curicul getCuricula(String nrmatricol);
	public void submitContract(Curicul c);
}
