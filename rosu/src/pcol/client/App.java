package pcol.client;

import java.util.logging.Logger;

import pcol.client.contract.ContractView;
import pcol.client.contract.ContractViewImpl;
import pcol.client.login.LoginScreen;
import pcol.client.login.LoginView;
import pcol.client.materii.MaterieView;
import pcol.client.materii.MaterieViewImpl;
import pcol.client.materii.MateriiView;
import pcol.client.materii.MateriiViewImpl;
import pcol.client.teme.TemeView;
import pcol.client.teme.TemeViewImpl;
import pcol.client.tweet.TweetPlace;
import pcol.client.tweet.TweetView;
import pcol.client.tweet.TweetViewImpl;
import pcol.client.ui.Nav;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;


public class App implements EntryPoint, Nav.Presenter{
	private static final Logger log = Logger.getLogger(App.class.getName());
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	//performance : multe initializari pot fi facute mai tarziu
	//si eventual asincron cu code split
	private final TweetView tweetView = new TweetViewImpl();
	private final ContractView contractView = new ContractViewImpl();
	private final LoginView loginView = new LoginScreen();
	private final MaterieView materieView = new MaterieViewImpl();
	private final MateriiView materiiView = new MateriiViewImpl();
	private final TemeView temeView = new TemeViewImpl();

	private Place defaultPlace = new TweetPlace.Tokenizer().getPlace("");

	@Override
	public void onModuleLoad() {
		final AppPlaceHistoryMapper historyMapper = new AppPlaceHistoryMapper();
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		//pot sa am mai multi acctivitymanageri si mappere coresp
		//fiecare manager se ocupa de display-ul lui
		//Dar inn cazuri generice gen breadcrumb cred ca-i mai bine sa aculte 
		//placechangeeventuri ( ca activvitattiile ar fi boring ar selecta
		//un tab hardcodat in activitate
		final ActivityMapper activityMapper = new AppActivityMapper(this);
		final ActivityManager activityManager = new ActivityManager(
				activityMapper, eventBus);

		final Nav shell = new Nav();
		shell.setPresenter(this);
		shell.addSelectionHandler(new SelectionHandler<String>() {
			@Override
			public void onSelection(SelectionEvent<String> event) {
				Place p = historyMapper.getPlace(event.getSelectedItem());
				if(p!=null){
					placeController.goTo(p);
				}
			}
		});
		shell.setUserName("Andrei Mihai Daniel");
		shell.setNrMatr(1040);

		eventBus.addHandler(PlaceChangeEvent.TYPE,
				new PlaceChangeEvent.Handler() {
					@Override
					public void onPlaceChange(PlaceChangeEvent event) {
						Place pl = event.getNewPlace();
						String token = historyMapper.getToken(pl);
						//TODO: is hack, tre un mecanism mai robust de asociat 
						//place-uri cu taburi
						log.fine(token);
						Document.get().setTitle(token);
						if(token!=null && token.startsWith("materie")){
							token = "materii";
						}
						shell.selectTabByToken(token,false);
					}
				});

		shell.addTab("noutati");
		shell.addTab("materii");
		shell.addTab("teme");
		shell.addSmallTab("contract");
		shell.addSmallTab("plati");
		shell.addSmallTab("etc");

		activityManager.setDisplay(shell.getContainer());

		RootLayoutPanel.get().add(shell);

		historyHandler.handleCurrentHistory();
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	public TweetView getTweetView() {
		return tweetView;
	}

	public ContractView getContractView() {
		return contractView;
	}

	@Override
	public void onLogout() {
		// TODO Auto-generated method stub
		
	}

	public MaterieView getMaterieView() {
		return materieView;
	}

	public MateriiView getMateriiView() {
		return materiiView;
	}

	public TemeView getTemeView() {
		return temeView;
	}

}
