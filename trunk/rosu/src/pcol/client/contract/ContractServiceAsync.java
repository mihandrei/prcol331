package pcol.client.contract;

import java.util.List;
import java.util.Set;

import pcol.shared.Contract;
import pcol.shared.Curicul;
import pcol.shared.OrarDto;
import pcol.shared.Tuple;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ContractServiceAsync {

	void getContract(String sid, AsyncCallback<Contract> callback);

	void getContractAndCuricul(String sid,
			AsyncCallback<Tuple<Curicul, Contract>> callback);

	void submitContract(String sid, Set<Integer> selectedCourseIds,
			AsyncCallback<Void> callback);

	void getCuricula(String sid, AsyncCallback<Curicul> callback);

	void getSchedule(String sid, AsyncCallback<List<OrarDto>> callback);


}
