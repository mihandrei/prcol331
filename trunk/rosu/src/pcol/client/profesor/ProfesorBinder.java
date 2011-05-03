package pcol.client.profesor;

import pcol.client.AppLoader;
import pcol.client.tweet.TweetService;
import pcol.client.tweet.TweetServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;

public class ProfesorBinder extends Composite {

	private static ProfesorBinderUiBinder uiBinder = GWT
			.create(ProfesorBinderUiBinder.class);
	@UiField TextArea anunt;
	@UiField Button trimite;
	
	@UiField FileUpload upl;
	@UiField FormPanel form;
	@UiField Button button;
	@UiField TextBox denumireTbox;
	@UiField FlexTable uploadtbl;

	private ProfesorActivity presenter;
	
	interface ProfesorBinderUiBinder extends UiBinder<Widget, ProfesorBinder> {
	}

	public ProfesorBinder() {
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
		
	}
	
	@UiHandler("form")
	void onFormSubmit(SubmitEvent event) {
		if(upl.getFilename().trim().isEmpty()){
			event.cancel();
		}
	}
	@UiHandler("form")
	void onFormSubmitComplete(SubmitCompleteEvent event) {
		uploadtbl.setWidget(uploadtbl.getRowCount(),0,new Label("aaa"));
	}
	@UiHandler("trimite")
	void onTrimiteClick(ClickEvent event) {
		presenter.send(anunt.getText());
	}
	public void setPresenter(ProfesorActivity profesorActivity) {
		this.presenter = profesorActivity;
		
	}
}
