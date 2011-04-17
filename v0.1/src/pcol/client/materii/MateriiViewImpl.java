package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class MateriiViewImpl extends Composite implements MateriiView {

	private static MateriiViewImplUiBinder uiBinder = GWT
			.create(MateriiViewImplUiBinder.class);
	@UiField HTMLPanel panel;
	@UiField Style style;
	interface MateriiViewImplUiBinder extends UiBinder<Widget, MateriiViewImpl> {
	}

	interface Style extends CssResource{
		String link();
	}


	private Presenter presenter;
	
	public MateriiViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public void addItem(String string, String targetHistoryToken) {
		Hyperlink hyperlink = new Hyperlink(string, targetHistoryToken);
		hyperlink.setStylePrimaryName(style.link());
		panel.add(hyperlink);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}


	@Override
	public void clear() {
		panel.clear();
	}

}
