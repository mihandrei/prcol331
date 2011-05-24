package pcol.server;

import pcol.client.useradmin.UsrService;
import pcol.server.security.AuthRemoteServiceServlet;

@SuppressWarnings("serial")
public class UsrAdminServiceImpl extends AuthRemoteServiceServlet implements UsrService {

	@Override
	public void saveUser(String nume, int nrmatr, int an, int sectie,
			String grupa, String cont) {
	System.out.println("req save usr");
		
	}

}
