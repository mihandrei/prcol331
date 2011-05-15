package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class MaterieShell extends Composite  {

	private static MaterieViewImplUiBinder uiBinder = GWT
			.create(MaterieViewImplUiBinder.class);
	@UiField
	Label title;
	@UiField InlineHyperlink backlink;
	@UiField SimpleLayoutPanel panel;
	
	interface MaterieViewImplUiBinder extends UiBinder<Widget, MaterieShell> {
	}

	public MaterieShell() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
