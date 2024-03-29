package pcol.client.useradmin;

import pcol.client.AppLoader;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
/**
 * Aplicatia instantiaza clasa asta cand userul navigheaza la #usradmin
 * si invoca startul de mai jos.
 */
public class UsrAdminActivity extends AbstractActivity implements UsrAdminView.Presenter{
	static UsrAdminView view = new UsrAdminBinder();
	static UsrServiceAsync rpc = GWT.create(UsrService.class);
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		panel.setWidget(view.asWidget());
		AppLoader.getApp().showTipFor("inmatriculare");
	}

	@Override
	public void saveUser(String nume, int nrmatr, int an, int sectie,String grupa, String cont) {
		rpc.saveUser( nume,  nrmatr,  an,  sectie, grupa,  cont, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				AppLoader.getApp().showInfo("saved");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		
	}

}
