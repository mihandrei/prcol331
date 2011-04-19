package pcol.client.useradmin;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/useradmin")
public interface UsrService  extends RemoteService {
	void saveUser(String nume, int nrmatr, int an, int sectie,String grupa, String cont);
}
