package pcol.client.useradmin;

import com.google.gwt.user.client.ui.IsWidget;

public interface UsrAdminView extends IsWidget{
    public interface Presenter{
    	void saveUser(String nume,int nrmatr, int an,int sectie, String grupa, String cont);
    }
    
	void setPresenter(Presenter p);
}
