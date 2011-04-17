package pcol.client.contract;


import java.util.List;
import java.util.Set;

import pcol.shared.AuthenticationException;
import pcol.shared.ContractItem;
import pcol.shared.Curicul;
import pcol.shared.Tuple;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/contract")
public interface ContractService extends RemoteService{
	Curicul getCuricula(String sid) throws AuthenticationException;
	List<ContractItem> getContract(String sid) throws AuthenticationException;
	Tuple<Curicul, List<ContractItem>> getContractAndCuricul(String sid)
			throws AuthenticationException;
	void submitContract(String sid, Set<Integer> selectedCourseIds) throws AuthenticationException;
}
