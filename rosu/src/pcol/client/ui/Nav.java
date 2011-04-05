package pcol.client.ui;

import java.util.ArrayList;
import java.util.List;

import pcol.client.widgets.SimpleLayoutPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;


/**
 * notes: pastres hasselectionhandler, desi in cazul de fata avem un singur
 * listerer : App si mai simplu ar fi sa apelez direct app Daca refolosesc
 * taburile va trebui selectionevent
 * 
 * @author miha
 * 
 */
public class Nav extends Composite implements HasSelectionHandlers<String> {
	public interface Presenter {
		void onLogout();
	}

	private static class Pallette {
		public static Pallette VIBRANT = new Pallette(new String[] { "#3FBA20",
				"#0f7fc0", "#ff2a2a", "#fed700" });
		public static Pallette PASTEL = new Pallette(new String[] { "#C3DBBD",
				"#C2CFD6", "#D6C2C2", "#D6D3C2" });
		public static Pallette NICE = new Pallette( 
				new String[] { "#FFD800", "#0095FF", "#F20D0D", "#A1E619" });

		private String[] scolor;

		public Pallette(String[] colors) {
			scolor = colors;
		}

		private int clamp(int idx) {
			if (idx < scolor.length && idx >= 0)
				return idx;
			else
				return scolor.length - 1;
		}

		public String getSColor(int i) {
			return scolor[clamp(i)];
		}
	}

	private ClickHandler clickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			int index = -1;

			for (int i = 0; i < widgets.size(); i++) {
				if (event.getSource() == widgets.get(i)) {
					index = i;
					break;
				}
			}
			selectTab(index,true);

		}
	};

	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField
	SimpleLayoutPanel container;
	@UiField
	FlowPanel tabc;
	@UiField
	InlineLabel usernamelbl;
	@UiField
	InlineLabel nrmatrlbl;
	@UiField
	InlineLabel logoutlbl;
	@UiField
	NavStyle style;

	interface NavStyle extends CssResource {
		String navItem();

		String navItemSel();
	}

	private List<String> links = new ArrayList<String>();
	private List<Label> widgets = new ArrayList<Label>();

	private int selectedidx = -1;
	private Pallette pallete = Pallette.NICE;

	private Presenter presenter;

	interface Binder extends UiBinder<Widget, Nav> {
	}

	public Nav() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(Presenter p) {
		presenter = p;
	}

	public void setUserName(String usr) {
		usernamelbl.setText(usr);
	}

	public void setNrMatr(int nrmatr) {
		// nrmatr.setText(Integer.toString(nrmatr));
	}

	//permitem reselcarea tabului curent
	private void selectTab(int index, boolean fire) {
		if (index < 0 || index > links.size()) {
			return;
		}
		
		if(index == selectedidx && !fire){
			return;
		}
		
		if(index != selectedidx){//change style
			if (selectedidx != -1) {
				widgets.get(selectedidx).removeStyleName(style.navItemSel());
				widgets.get(selectedidx).getElement().getStyle().clearBackgroundColor();
			}
	
			Style containerstyle = container.getElement().getStyle();
			containerstyle.setBorderColor(pallete.getSColor(index));
			containerstyle.setBackgroundColor(pallete.getSColor(index));
			
			widgets.get(index).addStyleName(style.navItemSel());
			widgets.get(index).getElement().getStyle()
					.setBackgroundColor(pallete.getSColor(index));
			selectedidx = index;
		}
		
		if(fire){
			SelectionEvent.fire(this, links.get(selectedidx));
		}
	}

	public void addTab(String tab) {
		//TODO: considera sa inlocuiesti label cu hyperlink
		Label lbl = new Label(tab);
		lbl.setStylePrimaryName(style.navItem());
		lbl.addClickHandler(clickHandler);
		links.add(tab);
		widgets.add(lbl);
		tabc.add(lbl);
	}

	public void selectTabByToken(String token) {
		selectTab(links.indexOf(token),true);
	}
	
	public void selectTabByToken(String token,boolean fire) {
		selectTab(links.indexOf(token),fire);
	}

	@Override
	public HandlerRegistration addSelectionHandler(
			SelectionHandler<String> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	public SimpleLayoutPanel getContainer() {
		return container;
	}

	@UiHandler("logoutlbl")
	void onLogoutlblClick(ClickEvent event) {
		presenter.onLogout();
	}
}
