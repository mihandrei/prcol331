package pcol.client.materii;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MateriiActivity extends AbstractActivity implements
		MateriiView.Presenter {
	private MateriiView materiiView;

	public MateriiActivity(MateriiView materiiView) {
		this.materiiView = materiiView;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		materiiView.setPresenter(this);
		materiiView.clear();
		materiiView.addItem("MF 002  Analiza complexa", "materie/FLCD");
		materiiView.addItem("MF 022	 Algebra", "materie/DB");
		materiiView.addItem("MF 101  Dalmatieni", "materie/FLCD");
		materiiView.addItem("MF 102  Dalmatieni", "materie/FLCD");
		materiiView.addItem("MF 103  Dalmatieni", "materie/FLCD");
		materiiView.addItem("MF 104  Dalmatieni", "materie/FLCD");
		materiiView.addItem("MF 105  Dalmatieni", "materie/FLCD");
		panel.setWidget(materiiView.asWidget());
	}

}
