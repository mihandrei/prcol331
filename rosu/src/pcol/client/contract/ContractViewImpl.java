package pcol.client.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

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
	private Map<String,Panel> catlist = new HashMap<String, Panel>();

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
		Panel panel = catlist.get(category);
		
		if(panel==null){
		     panel = new FlowPanel();
		     panel.setWidth("100%");
		     catlist.put(category,panel);
		     ScrollPanel scrollp = new ScrollPanel();
		     scrollp.add(panel);		     
		     tabpanel.add(scrollp, category);
		}
		
		for(IsWidget w:widgets){
			//lene: de trecut in css 
			w.asWidget().getElement().getStyle().setFloat(com.google.gwt.dom.client.Style.Float.LEFT);
			w.asWidget().getElement().getStyle().setMargin(1, Unit.EM);
			panel.add(w);
		}
	}
	
	@Override
	public void clearAllCategories(){
		catlist.clear();
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
	
	@Override
	public void setNrzile(int zile){
		nrzile.setText(Integer.toString(zile));
	}

	@Override
	public void setSaveEnabled(boolean enabled) {
		button.setEnabled(enabled);
		
	}

}
