package pcol.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ErrorDialog extends DialogBox {

	private static ErrorDialogUiBinder binder = GWT
			.create(ErrorDialogUiBinder.class);

	interface ErrorDialogUiBinder extends UiBinder<Widget, ErrorDialog> {
	}

	@UiField
	Label errLbl;
	@UiField
	Button okButton;

	public ErrorDialog() {
		setWidget(binder.createAndBindUi(this));
		setTitle("eroare");
		setGlassEnabled(true);
		center();
	}

	public ErrorDialog(String err) {
		this();
		errLbl.setText(err);
		center();
	}

	@UiHandler("okButton")
	void onOkClicked(ClickEvent event) {
		hide();
	}

}
