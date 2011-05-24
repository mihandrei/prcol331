package pcol.client.schedule;

import java.util.List;
import java.util.logging.Logger;

import pcol.client.AppLoader;
import pcol.client.contract.ContractService;
import pcol.client.contract.ContractServiceAsync;
import pcol.client.security.AppAsyncCallback;
import pcol.client.security.AppDoneEvent;
import pcol.shared.OrarDto;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class ScheduleActivity extends AbstractActivity implements
		WeekView.Presenter {
	static final Logger log = Logger.getLogger(ScheduleActivity.class.getName());
	private static WeekView view;
	private static boolean firstrun = true;
	private static ContractServiceAsync rpc = null;
	/**
	 * hack; invalideaza cache-ul , cand reporneste activitatea 
	 * va lua datele de pe server
	 * Ideal ar trebui o politica de cache mai globala si uniforma
	 */
	public static void invalidateCache(){
		firstrun=true;
		log.fine("cache invalidated");
	}
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {

			@Override
			public void onSuccess() {
				if (view == null) {
					// creerea poate fi deferita la un factory
					view = new WeekViewImpl();
				}
				if(rpc == null){
					rpc = GWT.create(ContractService.class);
				}
				// caching the widget; o umplem o data;
				// ar trebui sa pun un timer care sa faca refresh
				if (firstrun) {
					firstrun = false;
					view.setPresenter(ScheduleActivity.this);
					view.clear();
					rpc.getSchedule(AppLoader.getApp().getSid(), new AppAsyncCallback<List<OrarDto>>() {
						@Override
						public void onSuccess(List<OrarDto> result) {
							for(OrarDto o:result){
								view.addEvent(o);
							}
						}
						
					});
				}
				
				//inca un hack datorat lipsei de cache global
				//pe logout app se termina dar campurile statice raman
				eventBus.addHandler(AppDoneEvent.TYPE, new AppDoneEvent.Handler() {
					@Override
					public void onAppTerminated(AppDoneEvent event) {
						invalidateCache();
					}
				});
				
				
				panel.setWidget(view.asWidget());

				AppLoader.getApp().showTipFor("orar");
			}

			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}
