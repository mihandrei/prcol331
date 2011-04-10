package pcol.client.materii;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MateriiActivity extends AbstractActivity implements
		MateriiView.Presenter {
	
	private static MateriiView materiiView;

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				if(materiiView==null){
					//creerea poate fi deferita la un factory 
					materiiView = new MateriiViewImpl();
				}
				materiiView.setPresenter(MateriiActivity.this);
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
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}
