package pcol.client.contract;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface ContractView extends IsWidget{

	public interface Presenter {
		void onSave();
	}

	void setPresenter(Presenter presenter);
	
	void setCreditTotal(int ncred);
	void setPrice(float price);

	/**
	 * grupeaza un grup de widgeturi intr-un grup vizual 
	 */
	void addToCategory(String category, List<? extends IsWidget> widgets);

	void clearAllCategories();

	void setNrzile(int zile);

	void setSaveEnabled(boolean dirty);

}
