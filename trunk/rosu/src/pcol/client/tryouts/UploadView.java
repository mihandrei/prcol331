package pcol.client.tryouts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UploadView extends Composite {

	private static RteUiBinder uiBinder = GWT.create(RteUiBinder.class);
	@UiField FileUpload upl;
	@UiField FormPanel form;
	@UiField Button button;
	@UiField TextBox denumireTbox;

	interface RteUiBinder extends UiBinder<Widget, UploadView> {
	}

	public UploadView() {
		initWidget(uiBinder.createAndBindUi(this));
		form.setAction(GWT.getModuleBaseURL()+"upload");
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		upl.setName("upl");
		denumireTbox.setName("denumire");
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		form.submit();
	}
	@UiHandler("upl")
	void onUplChange(ChangeEvent event) {
		denumireTbox.setValue(upl.getFilename());
	}
	@UiHandler("form")
	void onFormSubmit(SubmitEvent event) {
		if(upl.getFilename().trim().isEmpty()){
			event.cancel();
		}
	}
	@UiHandler("form")
	void onFormSubmitComplete(SubmitCompleteEvent event) {
		Window.alert( event.getResults());
	}
}
