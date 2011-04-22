package pcol.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import pcol.client.security.AppDoneEvent;
import pcol.client.security.AutoLoginEvent;
import pcol.client.security.LoginEvent;
import pcol.client.security.LoginManager;
import pcol.client.ui.LoginShell;
import pcol.client.ui.Shell;
import pcol.shared.User;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
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
	    
	    eventBus.addHandler(AppDoneEvent.TYPE, new AppDoneEvent.Handler() {
			
			@Override
			public void onAppTerminated(AppDoneEvent event) {
				RootLayoutPanel.get().clear();
				shell.reset();
				RootLayoutPanel.get().add(shell);
			}
		});
	    
		loginManager.requestInitialLogin();
	}

	@Override
	public void login(String usr, String pwd) {
		loginManager.login(usr, pwd);
	}

	public void loadAppAsync(final User usr){
		GWT.runAsync(new RunAsyncCallback() {
			@Override
			public void onSuccess() {
				app = new App(eventBus,loginManager,usr.getAuthorizedActivities());
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