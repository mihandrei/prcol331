package pcol.client.contract;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ContractViewImpl extends Composite implements ContractView {
	// standard uibind
	@UiField
	TabLayoutPanel tabpanel;
	@UiField
	Label total;
	@UiField
	Label plata;
	@UiField
	Button button;
	@UiField
	InlineLabel nrzile;

	private Presenter presenter;

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<DockLayoutPanel, ContractViewImpl> {
	}

	public ContractViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		presenter.onSave();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void addToCategory(String category, List<? extends IsWidget> widgets) {
		VerticalPanel vpanel = new VerticalPanel();
		vpanel.setWidth("100%");
		tabpanel.add(vpanel, category);
		
		for(IsWidget w:widgets){
			vpanel.add(w);
		}
	}
	
	@Override
	public void clearAllCategories(){
		tabpanel.clear();
	}

	@Override
	public void setCreditTotal(int ncred) {
		total.setText(Integer.toString(ncred));
	}

	@Override
	public void setPrice(float price) {
		plata.setText(NumberFormat.getDecimalFormat().format(price));
	}

}
