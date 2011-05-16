package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditMaterialeView extends Composite {

	private static MaterialeEditViewUiBinder uiBinder = GWT
			.create(MaterialeEditViewUiBinder.class);
	@UiField FileUpload upl;
	@UiField FormPanel form;
	@UiField TextBox denumireTbox;
	@UiField Button button;
	@UiField FlexTable temeList;
	@UiField InlineLabel resName;
	private EditMaterialeActivity presenter;

	interface MaterialeEditViewUiBinder extends
			UiBinder<Widget, EditMaterialeView> {
	}

	public EditMaterialeView(String action, String fileInputName) {
		initWidget(uiBinder.createAndBindUi(this));
	
		form.setAction(action);
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		upl.setName(fileInputName);
		
		temeList.getColumnFormatter().getElement(0).getStyle().setWidth(15, Unit.EM);
		temeList.setText(0, 0, "numele temei");
		temeList.setText(0, 1, "cerinta");
	}

	@UiHandler("upl")
	void onUplChange(ChangeEvent event) {
		presenter.onFileChange();
	}
	
	@UiHandler("form")
	void onFormSubmitComplete(SubmitCompleteEvent event) {
		String res = event.getResults();
		presenter.onUploaded(res);
	}

	void addRow(String numeTema, String cerinteUrl){
		int lastrow = temeList.getRowCount();
		temeList.setText(lastrow, 0, numeTema);
		Anchor cerintaLink = new Anchor(resName.getText(),cerinteUrl,"blank");
		temeList.setWidget(lastrow, 1, cerintaLink);
		Anchor removeLink = new Anchor("sterge");
		temeList.setWidget(lastrow, 2, removeLink);
		
		removeLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int rowidx = temeList.getCellForEvent(event).getRowIndex();
				presenter.onRemove(rowidx);
			}
		});
	}
	
	void removerow(int rowidx){
		temeList.removeRow(rowidx);
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		presenter.onAdd();
	}

	public void setPresenter(EditMaterialeActivity presenter) {
		this.presenter=presenter;
	}

	public String getFilePth() {
		return upl.getFilename().trim();
	}

	public void submit() {
		form.submit();
	}
	
	void resetForm(){
		resName.setVisible(false);
		denumireTbox.setText("");
		form.setVisible(true);
	}
	
	void hideForm(String r){
		resName.setText(r);
		resName.setVisible(true);
		form.setVisible(false);
	}

	public void clear() {
		temeList.removeAllRows();
	}

	public String getDescription() {
		return denumireTbox.getText();
	}
}
