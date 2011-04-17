package pcol.client.security;

import java.util.logging.Level;
import java.util.logging.Logger;

import pcol.client.App;
import pcol.shared.AuthenticationException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T>{
	private static Logger log = Logger.getLogger(AppAsyncCallback.class.getName()); 
	@Override
	public void onFailure(Throwable caught) {
	  if(caught instanceof AuthenticationException) { 
		  App.getInstance().loginManager.onAuthenticationException();
	  } 
      else if(caught instanceof StatusCodeException) { 
			StatusCodeException ex = (StatusCodeException) caught;
			if(ex.getStatusCode()==401){
				App.getInstance().loginManager.onAuthenticationException();
			}else if(ex.getStatusCode()==403){
				Window.alert("nu aveti dreptul sa efectuati aceasta operatie");
			}
       } 
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
