package pcol.client.teme;

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;

public interface StudentOverView extends IsWidget{
	public interface Presenter {
		
	}

	void setPresenter(Presenter presenter);
	
	void addItem(String status,String name,String historyToken,Date deadline);
	void clear();
}
