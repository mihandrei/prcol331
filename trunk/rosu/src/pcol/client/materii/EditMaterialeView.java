package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.InlineLabel;

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

	public EditMaterialeView() {
		initWidget(uiBinder.createAndBindUi(this));
	
		form.setAction(GWT.getModuleBaseURL()+"upload");
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		upl.setName("upl");
		
		temeList.getColumnFormatter().getElement(0).getStyle().setWidth(15, Unit.EM);
		temeList.setText(0, 0, "numele temei");
		temeList.setText(0, 1, "cerinta");
	}
	
	@UiHandler("upl")
	void onUplChange(ChangeEvent event) {
		String fname = upl.getFilename().trim();
		if(!fname.isEmpty()){
			form.submit();
		}
	}
	
	@UiHandler("form")
	void onFormSubmitComplete(SubmitCompleteEvent event) {
		String res = event.getResults();
		if(res.startsWith("ok:")){
			String r = res.substring(3);
			resName.setText(r);
			resName.setVisible(true);
			form.setVisible(false);
		}else{
			Window.alert(res);
		}
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		//validate
		//rpc.addTema(cursId,denumireTbox.getValue(),cerinta)
		int lastrow = temeList.getRowCount();
		String numeTema = denumireTbox.getValue();
		temeList.setText(lastrow, 0, numeTema);
		Anchor cerintaLink = new Anchor(resName.getText(),"uploads/"+upl.getFilename().trim());
		cerintaLink.setTarget("blank");
		temeList.setWidget(lastrow, 1, cerintaLink);
		Anchor removeLink = new Anchor("sterge");
		temeList.setWidget(lastrow, 2, removeLink);
		
		removeLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int rowidx = temeList.getCellForEvent(event).getRowIndex();
				//todo: rpc
				temeList.removeRow(rowidx);
			}
		});
		
		resName.setVisible(false);
		denumireTbox.setText("");
		form.setVisible(true);
	}

	public void setPresenter(EditMaterialeActivity presenter) {
		this.presenter=presenter;
	}
}
