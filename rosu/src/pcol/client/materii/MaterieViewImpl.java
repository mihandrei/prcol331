package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MaterieViewImpl extends Composite implements MaterieView {

	private static MaterieViewImplUiBinder uiBinder = GWT
			.create(MaterieViewImplUiBinder.class);
	@UiField
	HTML materialeHTML;
	@UiField FlexTable ftbl;
	
	private Presenter presenter;

	interface MaterieViewImplUiBinder extends UiBinder<Widget, MaterieViewImpl> {
	}

	public MaterieViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(MaterieView.Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setDescription(String desc) {
		materialeHTML.setHTML(desc);
	}

	@Override
	public void addMaterial(String description, String url) {
		int lastrow = ftbl.getRowCount();
		ftbl.setText(lastrow, 0,description);
		Anchor cerintaLink = new Anchor("descarca",url,"blank");
		ftbl.setWidget(lastrow, 1, cerintaLink);
	}

	@Override
	public void clearMateriale(){
		ftbl.removeAllRows();
	}
}
