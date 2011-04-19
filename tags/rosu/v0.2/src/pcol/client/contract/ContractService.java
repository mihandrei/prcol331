package pcol.client.contract;

import java.util.List;
import java.util.Set;

import pcol.shared.AuthenticationException;
import pcol.shared.Contract;
import pcol.shared.Curicul;
import pcol.shared.OrarDto;
import pcol.shared.Tuple;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/contract")
public interface ContractService extends RemoteService{
	Curicul getCuricula(String sid) throws AuthenticationException;
	Contract getContract(String sid) throws AuthenticationException;
	Tuple<Curicul, Contract> getContractAndCuricul(String sid) throws AuthenticationException;
	void submitContract(String sid, Set<Integer> selectedCourseIds) throws AuthenticationException;
	List<OrarDto> getSchedule(String sid) throws AuthenticationException;
}
