package pcol.client.security;

import java.util.logging.Level;
import java.util.logging.Logger;

import pcol.client.App;
import pcol.shared.AuthenticationException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T>{
	private static Logger log = Logger.getLogger(AppAsyncCallback.class.getName()); 
	@Override
	public void onFailure(Throwable caught) {
	  if(caught instanceof AuthenticationException) { 
		  App.getInstance().loginManager.onAuthenticationException();
	  } 
//    else if(caught instanceof AuthorizationException) { 
//    	  you cannot access this resource
//    } 
      try { 
           handleFailure(caught); 
      } 
      catch(Throwable e) { 
    	  log.log(Level.SEVERE,"rpc unhandled error",caught);
    	  App.getInstance().showInfo("eroare. incercati un pic" +
    	  		" mai tarziu, verificati conexiunea la internet sau reincarcati pagina");
      } 
		
	}

	protected void handleFailure(Throwable caught) throws Throwable{
		throw caught;
	}

}
