package pcol.client.teme;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TemaNotareView extends Composite {

	private static TemaNotareViewUiBinder uiBinder = GWT
			.create(TemaNotareViewUiBinder.class);

	interface TemaNotareViewUiBinder extends UiBinder<Widget, TemaNotareView> {
	}

	public TemaNotareView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
