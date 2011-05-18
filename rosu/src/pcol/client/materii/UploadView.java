package pcol.client.materii;

import com.google.gwt.user.client.ui.IsWidget;

public interface UploadView extends IsWidget{
	
	public interface Presenter{

		void onFileChange();

		void onUploaded(String res);

		void onRemove(int rowidx);

		void onAdd();
		
	}

	void clear();

	void waitForInputMode();

	void addRow(String description, String string);

	void setPresenter(Presenter presenter);

	String getFilePth();

	void submit();

	void uploadingMode();

	void readyToSaveMode(String r);

	void removerow(int rowidx);

	String getDescription();

	void descInvalid();
}
