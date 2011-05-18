package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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

public class EditMaterialeView extends Composite implements UploadView{

	private static MaterialeEditViewUiBinder uiBinder = GWT
			.create(MaterialeEditViewUiBinder.class);
	FileUpload upl;
	@UiField FormPanel form;
	@UiField TextBox denumireTbox;
	@UiField Button addBtn;
	@UiField Button cancelBtn;
	@UiField FlexTable temeList;
	@UiField InlineLabel resName;
	private final String fileInputName;
	private UploadView.Presenter presenter;

	interface MaterialeEditViewUiBinder extends
			UiBinder<Widget, EditMaterialeView> {
	}
	private void createNewFileUpload(){
		upl= new FileUpload();
		upl.setName(fileInputName);
		
		upl.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent arg0) {
				presenter.onFileChange();
			}
		});
	}
	
	public EditMaterialeView(String action, String fileInputName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.fileInputName = fileInputName;
	
		form.setAction(action);
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		createNewFileUpload();
		
		temeList.getColumnFormatter().getElement(0).getStyle().setWidth(16, Unit.EM);
	}

	@UiHandler("form")
	void onFormSubmitComplete(SubmitCompleteEvent event) {
		String res = event.getResults();
		presenter.onUploaded(res);
	}

	public void addRow(String numeTema, String cerinteUrl){
		int lastrow = temeList.getRowCount();
		temeList.setText(lastrow, 0, numeTema);
		Anchor cerintaLink = new Anchor("descarca",cerinteUrl,"blank");
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
	
	public void removerow(int rowidx){
		temeList.removeRow(rowidx);
	}
	
	@UiHandler("addBtn")
	void onButtonClick(ClickEvent event) {
		presenter.onAdd();
	}
	@UiHandler("cancelBtn")
	void onCancelBtnClick(ClickEvent event) {
		waitForInputMode();
	}
	public void setPresenter(UploadView.Presenter presenter) {
		this.presenter=presenter;
	}

	public String getFilePth() {
		return upl.getFilename().trim();
	}

	public void submit() {
		form.submit();
	}
	
	public void clear() {
		temeList.removeAllRows();
	}

	public String getDescription() {
		return denumireTbox.getText();
	}

	public void waitForInputMode(){
		resName.setVisible(false);
		addBtn.setVisible(false);
		cancelBtn.setVisible(false);
		
		denumireTbox.setText("");
		
		createNewFileUpload();
		form.clear();
		form.add(upl);
		form.setVisible(true);
		
		denumireTbox.getElement().getStyle().clearBorderColor();
	}
	
	public void uploadingMode() {
		resName.setText("se uploadeaza ... ");
		resName.setVisible(true);
		form.setVisible(false); 
	}

	public void readyToSaveMode(String r) {
		resName.setText(r);
		resName.setVisible(true);
		form.setVisible(false); 
		
		addBtn.setVisible(true);
		cancelBtn.setVisible(true);
	}

	public void descInvalid() {
		denumireTbox.getElement().getStyle().setBorderColor("#FF3322");
	}
	
}
