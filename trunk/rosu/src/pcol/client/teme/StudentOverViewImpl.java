package pcol.client.teme;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

//TODO : ii aproape identic viewul cu cel de la materii, reuse
public class StudentOverViewImpl  extends Composite implements StudentOverView {

	private static TemeViewImplUiBinder uiBinder = GWT
			.create(TemeViewImplUiBinder.class);

	@UiField HTMLPanel panel;
	@UiField Style style;

	interface Style extends CssResource{
		String link();
	}

	interface TemeViewImplUiBinder extends UiBinder<Widget, StudentOverViewImpl> {
	}
	private Presenter presenter;

	public StudentOverViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}

	@Override
	public void addItem(String status, String name, String historyToken,
			Date deadline) {
		//todo : compozite this
		FlowPanel fp = new FlowPanel();
		fp.setStylePrimaryName(style.link());
		InlineHyperlink hyperlink = new InlineHyperlink(name, historyToken);
		fp.add(hyperlink);
		DateLabel dlbl = new DateLabel(DateTimeFormat.getFormat(" EEE, MMM d"));
		dlbl.setValue(deadline);
		fp.add(dlbl);
		fp.add(new InlineLabel(status));
		panel.add(fp);		
	}

	@Override
	public void clear() {
		panel.clear();
	}


}
