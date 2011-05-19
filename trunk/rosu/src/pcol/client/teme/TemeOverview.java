package pcol.client.teme;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TemeOverview extends Composite {

	private static TemeOverviewUiBinder uiBinder = GWT
			.create(TemeOverviewUiBinder.class);

	interface TemeOverviewUiBinder extends UiBinder<Widget, TemeOverview> {
	}

	public TemeOverview() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
