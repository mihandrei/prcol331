package pcol.client.materii;

import com.google.gwt.user.client.ui.IsWidget;

public interface MaterieView extends IsWidget{
	public interface Presenter {
	}

	void setPresenter(Presenter presenter);
	void setMateriale(String string);
	void setName(String name);
}
