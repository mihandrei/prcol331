package pcol.client;

import java.util.logging.Logger;

import pcol.client.config.AdminActivityConfig;
import pcol.client.config.AdminPlaceConfig;
import pcol.client.config.TabPlaceMapper;
import pcol.client.config.TabPlaceMapper.Tab;
import pcol.client.security.AppDoneEvent;
import pcol.client.security.AuthenticationService;
import pcol.client.security.AuthenticationServiceAsync;
import pcol.client.security.LoginEvent;
import pcol.client.security.LoginManager;
import pcol.client.tweet.TweetPlace;
import pcol.client.ui.ReLoginDialog;
import pcol.client.ui.ReLoginView;
import pcol.client.ui.Shell;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeRequestEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public final class App implements Shell.Presenter{
	private static final Logger log = Logger.getLogger(App.class.getName());

	public final EventBus eventBus;
	public final LoginManager loginManager;
	public final PlaceController placeController; 
	private final Shell shell;
	private final ReLoginView reloginview;
	
	private PlaceHistoryHandler historyHandler;
	private Tips tips;
	
	private AuthenticationServiceAsync authService = GWT.create(AuthenticationService.class);

	private HandlerRegistration historyRegistration;

	private ActivityManager activityManager;
	
	protected App(EventBus eventBus, final LoginManager loginManager,
			PlaceHistoryMapper placeConfig,
			Place defaultPlace, 
			final TabPlaceMapper tabMapper,
			ActivityMapper activityConfig){
		this.eventBus = eventBus;
		this.tips = new Tips(loginManager.getUser());
		placeController = new PlaceController(eventBus);
		this.loginManager = loginManager;

		this.shell = new Shell();
		shell.setPresenter(App.this);
		shell.setUserName(loginManager.getUser().getName());
		shell.setNrMatr(loginManager.getUser().getNrMatr());
		
		for(int i=0;i<tabMapper.getTabs().size();i++){
			Tab tab = tabMapper.getTabs().get(i);
			if(i<3){// <- hardcode: daca in loc de string intorc UserNavPref {name, relevance}
				shell.addTab(tab.name,tab.historyToken,false);
			}else{
				shell.addTab(tab.name,tab.historyToken,true);
			}
		}
		
		this.reloginview = new ReLoginDialog();
		reloginview.setPresenter(new ReLoginView.Presenter() {
			@Override
			public void relogin(String pwd) {
				loginManager.relogin(pwd);
			}
		});
		
		eventBus.addHandler(LoginEvent.TYPE, new LoginEvent.Handler() {
			@Override
			public void onUserAuthenticated(LoginEvent event) {
				if(event.getUser()!=null){
					reloginview.hide();
					//TODO: tre cumva notificat userul/activitatea sa reincerce
					showInfo("incercati acum actiunea care a esuat anterior");
				}else{
					reloginview.showLoginError();
				}
			}
		 });
		
		initAppFlow(placeConfig,defaultPlace,tabMapper,activityConfig);
	}

	protected void start(){
		startHeartBeat();
		
		//WARN: here be matze (every abstraction is leaky, tuluai cat de relevant ii win32 event loop)
		//eventbus inregistreaza handlerele asyncronish 
		//Mai concret pe cand se termina de executat eventu
		//deci un handlecurrenthistory aici va fire placechangedevents 
		//pe un eventbus fara handleri inregistrati
		//Deaia execut handlecurenthistory pe cand o gatat eventu 
		//gwteventu are radacina de stack pe ceva browser event loop
		//schedule executa jucaria pe urmatorul loop
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RootLayoutPanel.get().add(shell);
				historyHandler.handleCurrentHistory();
			}
		});	
	}
	
	/** 
	 *  init activity/places framework and wires navigation ui to history changes
	 *  registeres handlers
	 */
	private void initAppFlow(PlaceHistoryMapper placeConfig,
			Place defaultPlace, 
			final TabPlaceMapper tabMapper,
			ActivityMapper activityConfig){
		
		historyHandler = new PlaceHistoryHandler(placeConfig);
		historyRegistration = historyHandler.register(placeController, eventBus, defaultPlace);
		
		//pot sa am mai multi acctivitymanageri si mappere coresp
		//fiecare manager se ocupa de display-ul lui
		//Dar inn cazuri generice gen breadcrumb cred ca-i mai bine sa aculte 
		//placechangeeventuri ( ca activvitattiile ar fi boring ar selecta
		//un tab hardcodat in activitate
		activityManager = new ActivityManager(
				activityConfig, eventBus);
		activityManager.setDisplay(shell.getContainer());

		shell.addSelectionHandler(new SelectionHandler<String>() {
			@Override
			public void onSelection(SelectionEvent<String> event) {
				History.newItem(event.getSelectedItem());
			}
		});
		
		eventBus.addHandler(PlaceChangeEvent.TYPE,
				new PlaceChangeEvent.Handler() {
			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				Place pl = event.getNewPlace();
				String tabname = tabMapper.getTab(pl);
				Document.get().setTitle("pcol - " + tabname);
				shell.selectTabByToken(tabname);
			}
		});
	}
	
	private void startHeartBeat(){
		new Timer(){
			@Override
			public void run() {
				authService.ping(new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						shell.heartBeat();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						shell.heartBeatMissed();
					}
				});
				
			}
		}.scheduleRepeating(1000*10);
	}

	@Override
	public void onLogout() {
		//intrebat activitatea curenta daca se poate opri
		PlaceChangeRequestEvent willChange = new PlaceChangeRequestEvent(Place.NOWHERE);
	    eventBus.fireEvent(willChange);
		String warning = willChange.getWarning();
		 if (warning == null || Window.confirm(warning)) {
			 //loginmanagerul sa invalideze credentiale (sid & cookie)
			 loginManager.logout();
			 //daca nu-l deregistrez o sa aculte pe bus placechangeeventuri, si o sa vrea sa le mapeze			 
			 historyRegistration.removeHandler();
			 //daca nu inca mai asculta place change-uri si o sa intrebe activitatea curenta daca maystop
			 //repara bugul : cand dau relogin contractactivity intreaba daca maystop (pt ca e o navigare 
			 //de la PLace.NOWHERE la contractplace, se pare ca acctivitatile sunt intrebate mereu daca 
			 //maystop si daca nu se pleaca de la place-ul lor
			 activityManager.setDisplay(null);
			 //notifica apploaderul si alte ca am incheiat
			 eventBus.fireEvent(new AppDoneEvent());
		 }
	}
	
	public void showError(String msg){
		if(shell!=null){
			shell.showerror(msg);
		}
	}
	
	public void showInfo(String msg){
		if(shell!=null){
			shell.showinfo(msg);
		}
	}

	public void showTipFor(String category) {
		String tip = tips.getTip(category);
		if(tip!=null){
			showInfo(tip);
		}
	}
	
	/**
	 * @return session id -ul  asociat cu loginul curent
	 */
	public String getSid() {
		return loginManager.getUser().getSid();
	}

	public void onAuthenticationException() {
		reloginview.showscreen();
	}

}
