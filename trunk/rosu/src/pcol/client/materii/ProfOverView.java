package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.FlowPanel;

public class ProfOverView extends Composite {

	private static MaterieOverViewUiBinder uiBinder = GWT
			.create(MaterieOverViewUiBinder.class);
	@UiField Hyperlink descLink;
	@UiField Hyperlink materialeLink;
	@UiField Hyperlink temeLink;
	@UiField FlowPanel temelinks;
	@UiField FlowPanel notelinks;

	interface MaterieOverViewUiBinder extends UiBinder<Widget, ProfOverView> {
	}

	private ProfOverviewActivity presenter;

	public ProfOverView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(ProfOverviewActivity presenter) {
		this.presenter = presenter;
	}

}
