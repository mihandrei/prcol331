package pcol.client.contract;

import java.util.List;
import java.util.Set;

import pcol.shared.ContractItem;
import pcol.shared.Curicul;
import pcol.shared.Tuple;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContractServiceAsync {

	void getCuricula(String sid, AsyncCallback<Curicul> callback);

	void submitContract(String sid, Set<Integer> selectedCourseIds,
			AsyncCallback<Void> callback);

	void getContract(String sid, AsyncCallback<List<ContractItem>> callback);

	void getContractAndCuricul(String sid,
			AsyncCallback<Tuple<Curicul, List<ContractItem>>> callback);

}
