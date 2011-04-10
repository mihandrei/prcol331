package pcol.client.security;

import pcol.client.App;
import pcol.shared.AuthenticationException;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T>{

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
        //default application-wide error handling 
      } 
		
	}

	protected void handleFailure(Throwable caught){
		
	}

}
