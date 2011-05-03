package pcol.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import pcol.client.config.AdminActivityConfig;
import pcol.client.config.AdminPlaceConfig;
import pcol.client.config.AdminTabConfig;
import pcol.client.config.ProfActivityConfig;
import pcol.client.config.ProfPlaceConfig;
import pcol.client.config.ProfTabConfig;
import pcol.client.config.StudentActivityConfig;
import pcol.client.config.StudentPlaceConfig;
import pcol.client.config.StudentTabConfig;
import pcol.client.config.TabPlaceMapper;
import pcol.client.security.AppDoneEvent;
import pcol.client.security.AutoLoginEvent;
import pcol.client.security.LoginEvent;
import pcol.client.security.LoginManager;
import pcol.client.tweet.TweetPlace;
import pcol.client.ui.LoginShell;
import pcol.client.useradmin.UsrAdminPlace;
import pcol.shared.User;
import pcol.shared.User.Role;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class AppLoader implements EntryPoint, LoginShell.Presenter{
	private static final Logger log = Logger.getLogger(AppLoader.class.getName());

	private LoginShell shell;
	private final EventBus eventBus = new SimpleEventBus();
	private  LoginManager loginManager = new LoginManager(eventBus);
	private HandlerRegistration autoLoginReg,loginReg;
	
	private static App app;
	
	public static App getApp(){
		return app;
	}
	
	public AppLoader(){
		
	}
				
	@Override
	public void onModuleLoad() {
		autoLoginReg = eventBus.addHandler(AutoLoginEvent.TYPE, new AutoLoginEvent.Handler() {
			@Override
			public void onUserAuthenticated(AutoLoginEvent event) {
				if(event.getUser()!=null){
					loadAppAsync(event.getUser());
				}else{
					shell=new LoginShell();
					shell.setPresenter(AppLoader.this);
					RootLayoutPanel.get().add(shell);
				}
				autoLoginReg.removeHandler();
			}
		});
		
		listenForLoginOnce();
	    
	    eventBus.addHandler(AppDoneEvent.TYPE, new AppDoneEvent.Handler() {
			
			@Override
			public void onAppTerminated(AppDoneEvent event) {
				RootLayoutPanel.get().clear();
				shell.reset();
				RootLayoutPanel.get().add(shell);
				listenForLoginOnce();
			}
		});
	    
		loginManager.requestInitialLogin();
	}

	private void listenForLoginOnce() {
	    loginReg = eventBus.addHandler(LoginEvent.TYPE, new LoginEvent.Handler() {
			@Override
			public void onUserAuthenticated(LoginEvent event) {
				if(event.getUser()!=null){
					loadAppAsync(event.getUser());
					loginReg.removeHandler();
				}else{
					shell.showLoginError();
				}
			}
		 });		
	}

	@Override
	public void login(String usr, String pwd) {
		loginManager.login(usr, pwd);
	}

	public void loadAppAsync(final User usr){
		GWT.runAsync(new RunAsyncCallback() {
			@Override
			public void onSuccess() {
				PlaceHistoryMapper placeConf;
				Place defPlace;
				TabPlaceMapper tabConf;
				ActivityMapper activityConf;
				
				//FIXME: obtine rolurile de la server!
				if(usr.getRole() == Role.STUDENT ){
					placeConf = new StudentPlaceConfig();
					defPlace = new TweetPlace();
					tabConf = new StudentTabConfig();
					activityConf = new StudentActivityConfig();
				}else if(usr.getRole() == Role.ADMIN){
					placeConf = new AdminPlaceConfig();
					defPlace = new UsrAdminPlace();
					tabConf = new AdminTabConfig();
					activityConf = new AdminActivityConfig();
				}else if(usr.getRole() == Role.PROFESOR){
					placeConf = new ProfPlaceConfig();
					defPlace = new UsrAdminPlace();
					tabConf = new ProfTabConfig();
					activityConf = new ProfActivityConfig();
				}else{
					Window.alert("tip de utilizator neasteptat :" + usr.getRole());
					return;
				}
				app = new App(eventBus,loginManager,placeConf,defPlace,tabConf,activityConf);
				log.fine("new App starting for user "+usr.getLoginName());
				app.start();
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert("fatal " + reason.getLocalizedMessage());
				log.log(Level.SEVERE, "app load failed", reason);
			}
		});
	}


}