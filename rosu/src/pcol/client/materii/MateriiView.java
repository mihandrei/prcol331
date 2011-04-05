package pcol.client.materii;

import com.google.gwt.user.client.ui.IsWidget;

public interface MateriiView extends IsWidget{
	public interface Presenter {
	}

	void setPresenter(Presenter presenter);

	void addItem(String string, String targetHistoryToken);

	void clear();
}
