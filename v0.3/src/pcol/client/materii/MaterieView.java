package pcol.client.materii;

import com.google.gwt.user.client.ui.IsWidget;

public interface MaterieView extends IsWidget{
	public interface Presenter {
	}

	void setPresenter(Presenter presenter);
	void setDescription(String desc);
	void addMaterial(String description, String resourceName);
	void clearMateriale();
}
