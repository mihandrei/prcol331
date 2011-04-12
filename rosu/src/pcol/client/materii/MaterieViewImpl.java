package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MaterieViewImpl extends Composite implements MaterieView {

	private static MaterieViewImplUiBinder uiBinder = GWT
			.create(MaterieViewImplUiBinder.class);
	@UiField
	HTML materialeHTML;
	
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
	public void setMateriale(String string) {
		materialeHTML.setHTML(string);
	}

}
