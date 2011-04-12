package pcol.client;

import java.util.logging.Logger;

import pcol.client.security.LoginEvent;
import pcol.client.security.LoginManager;
import pcol.client.tweet.TweetPlace;
import pcol.client.ui.Shell;
import pcol.shared.User;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class App implements EntryPoint, Shell.Presenter{
	private static final Logger log = Logger.getLogger(App.class.getName());
	private static App instance;
	
	public static App getInstance() {
		return instance;
	}

	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);

	private Shell shell;
	public LoginManager loginManager = new LoginManager();
	
	private PlaceHistoryHandler historyHandler;

	/** 
	 *  init activity/places framework and wires navigation ui to history changes
	 */
	private void initAppFlow(){
		//si daca-i in functie de user?
		Place defaultPlace = new TweetPlace.Tokenizer().getPlace("");
		final AppPlaceHistoryMapper historyMapper = new AppPlaceHistoryMapper();
		historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		
		//pot sa am mai multi acctivitymanageri si mappere coresp
		//fiecare manager se ocupa de display-ul lui
		//Dar inn cazuri generice gen breadcrumb cred ca-i mai bine sa aculte 
		//placechangeeventuri ( ca activvitattiile ar fi boring ar selecta
		//un tab hardcodat in activitate
		final ActivityMapper activityMapper = new AppActivityMapper();
		final ActivityManager activityManager = new ActivityManager(
				activityMapper, eventBus);
		activityManager.setDisplay(shell.getContainer());

		shell.addSelectionHandler(new SelectionHandler<String>() {
			@Override
			public void onSelection(SelectionEvent<String> event) {
				Place p = historyMapper.getPlace(event.getSelectedItem());
				if(p!=null){
					placeController.goTo(p);
				}
			}
		});
		
		eventBus.addHandler(PlaceChangeEvent.TYPE,
				new PlaceChangeEvent.Handler() {
			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				Place pl = event.getNewPlace();
				String token = historyMapper.getToken(pl);
				//TODO: HACK, tre un mecanism mai robust de asociat 
				//place-uri cu taburi
				log.fine(token);
				Document.get().setTitle(token);
				if(token!=null && token.startsWith("materie")){
					token = "materii";
				}

				shell.selectTabByToken(token);
			}
		});
	}

	private void loadAppAsync(final User usr){
		GWT.runAsync(new RunAsyncCallback() {
			@Override
			public void onSuccess() {
				shell = new Shell();
				shell.setPresenter(App.this);
				shell.setUserName(usr.getName());
				shell.setNrMatr(usr.getNrMatr());
				shell.setNrMatr(usr.getNrMatr());
				for(String s: usr.getAuthorizedActivities()){
					shell.addTab(s);
					//shell.addsmalltab(s); <- daca in loc de string intorc UserNavPref {name, relevance}
				}
				
				initAppFlow();
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
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("fatal " + reason.getLocalizedMessage());
			}
		});
	}
	
	@Override
	public void onModuleLoad() {
		instance = this;
		
		//loginmanager pune pe eventbus un userchangedevent
		eventBus.addHandler(LoginEvent.TYPE, new LoginEvent.Handler() {
			@Override
			public void onUserAuthenticated(LoginEvent event) {
				User usr = event.getUser();
				loadAppAsync(usr);
			}
		});
		
		loginManager.requestInitialLogin();		
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public void onLogout() {
		//BUG: tre intrebat activitatea curenta daca se poate opri
		loginManager.logout();
		RootLayoutPanel.get().clear();
		loginManager.onAuthenticationException();
	}
	
	public void showInfo(String msg){
		if(shell!=null){
			shell.showinfo(msg);
		}
	}

}
