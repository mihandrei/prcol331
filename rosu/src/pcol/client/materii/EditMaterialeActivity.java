package pcol.client.materii;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditMaterialeActivity extends AbstractActivity{
	private MaterieShell shell;
	private EditMaterialeView view;
	private EditMaterialePlace place;

	public EditMaterialeActivity(EditMaterialePlace place) {
		this.place=place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if (view == null) {
			view = new EditMaterialeView();
			shell = new MaterieShell();
			shell.panel.setWidget(view);
			shell.backlink.setText("<- innapoi la sumar");
			shell.backlink.setTargetHistoryToken("materie/"+place.getMaterieid());
		}
		view.setPresenter(this);
		panel.setWidget(shell.asWidget());
		
	}

}
