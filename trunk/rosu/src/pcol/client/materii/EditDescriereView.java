package pcol.client.materii;

import pcol.client.ui.AppResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.HtmlSanitizer;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class EditDescriereView extends Composite {

	private static MaterieDescriereEditViewUiBinder uiBinder = GWT
			.create(MaterieDescriereEditViewUiBinder.class);
	@UiField TextArea txtInput;
	@UiField Button button;
	@UiField HTML previewPane;
	@UiField Label taghint;
	@UiField Button btnEdit;
	@UiField Button btnsave;
	@UiField FlowPanel previewPanel;
	@UiField HTMLPanel editPanel;
	private EditDescriereActivity presenter;
	
	static {
		AppResources.INSTANCE.style().ensureInjected();
	}
	
	interface MaterieDescriereEditViewUiBinder extends
			UiBinder<Widget, EditDescriereView> {
	}

	public EditDescriereView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	void genPreview(){
		SafeHtml html = SimpleHtmlSanitizer.sanitizeHtml(txtInput.getText());
		String str = html.asString().replace("\n", "<br/>");
		previewPane.setHTML(str);
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		previewMode();
	}
	
	@UiHandler("btnEdit")
	void onBtnEditClick(ClickEvent event) {
		editMode();
	}

	@UiHandler("btnsave")
	void onBtnsaveClick(ClickEvent event) {
		presenter.saveDescription(txtInput.getText());
	}

	public void setPresenter(EditDescriereActivity presenter) {
		this.presenter=presenter;		
	}

	public void setDescription(String desc) {
		txtInput.setText(desc);
		genPreview();
	}
	
	public void editMode(){
		editPanel.setVisible(true);
		previewPanel.setVisible(false);
		txtInput.setFocus(true);
	}
	
	public void previewMode(){
		genPreview();
		editPanel.setVisible(false);
		previewPanel.setVisible(true);
	}
}
