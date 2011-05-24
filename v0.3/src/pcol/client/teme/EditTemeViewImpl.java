package pcol.client.teme;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.google.gwt.user.datepicker.client.DateBox;

public class EditTemeViewImpl extends Composite implements EditTemeView{

	private static EditTemeViewUiBinder uiBinder = GWT
			.create(EditTemeViewUiBinder.class);
	FileUpload upl;
	@UiField FormPanel form;
	@UiField TextBox denumireTbox;
	@UiField Button addBtn;
	@UiField Button cancelBtn;
	@UiField FlexTable temeList;
	@UiField InlineLabel resName;
	@UiField DateBox dateBox ;
	private final String fileInputName;
	private EditTemeView.Presenter presenter;

	interface EditTemeViewUiBinder extends
			UiBinder<Widget, EditTemeViewImpl> {
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
	
	public EditTemeViewImpl(String action, String fileInputName) {
		initWidget(uiBinder.createAndBindUi(this));
		dateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MMM")));
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
	
	@Override
	public void addRow(String numeTema, String cerinteUrl, Date deadline){
		int lastrow = temeList.getRowCount();
		temeList.setText(lastrow, 0, numeTema);
		temeList.setText(lastrow, 1, DateTimeFormat.getFormat("dd/MMM").format(deadline));
		Anchor cerintaLink = new Anchor("descarca",cerinteUrl,"blank");
		temeList.setWidget(lastrow, 2, cerintaLink);
		Anchor removeLink = new Anchor("sterge");
		temeList.setWidget(lastrow, 3, removeLink);
		
		removeLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int rowidx = temeList.getCellForEvent(event).getRowIndex();
				presenter.onRemove(rowidx);
			}
		});
	}
	
	@Override
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
	
	@Override
	public void setPresenter(EditTemeView.Presenter presenter) {
		this.presenter=presenter;
	}
	@Override
	public String getFilePth() {
		return upl.getFilename().trim();
	}
	
	@Override
	public void submit() {
		form.submit();
	}
	
	@Override
	public void clear() {
		temeList.removeAllRows();
	}
	
	@Override
	public String getDescription() {
		return denumireTbox.getText();
	}
	
	@Override
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
	
	@Override
	public void uploadingMode() {
		resName.setText("se uploadeaza ... ");
		resName.setVisible(true);
		form.setVisible(false); 
	}
	
	@Override
	public void readyToSaveMode(String r) {
		resName.setText(r);
		resName.setVisible(true);
		form.setVisible(false); 
		
		addBtn.setVisible(true);
		cancelBtn.setVisible(true);
	}
	
	@Override
	public void descInvalid() {
		denumireTbox.getElement().getStyle().setBorderColor("#FF3322");
	}

	@Override
	public Date getDeadLine() {
		return dateBox.getValue();
	}
	
}
