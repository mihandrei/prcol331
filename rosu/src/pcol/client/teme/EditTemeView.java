package pcol.client.teme;

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;

import pcol.client.materii.UploadView;
import pcol.client.materii.UploadView.Presenter;

public interface EditTemeView extends IsWidget {
	public interface Presenter extends UploadView.Presenter{
		
	}
	void clear();

	void waitForInputMode();

	void setPresenter(Presenter presenter);

	String getFilePth();

	void submit();

	void uploadingMode();

	void readyToSaveMode(String r);

	void removerow(int rowidx);

	String getDescription();

	void descInvalid();
	Date getDeadLine();

	void addRow(String numeTema, String cerinteUrl, Date deadline);
}
