package pcol.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.KeyDownEvent;

/**
 * punctul de intrare in app afiseaza login formu
 * 
 * @author miha
 */
public class LoginScreen implements EntryPoint {
	// standard uibinder
	private static LoginScreenUiBinder uiBinder = GWT
			.create(LoginScreenUiBinder.class);

	@UiField
	Button button;
	@UiField
	TextBox usr;
	@UiField
	PasswordTextBox pwd;
	@UiField
	Label badauth;

	interface LoginScreenUiBinder extends UiBinder<Widget, LoginScreen> {
	}

	// GWT creeaza un proxy pentu serviciul de autentificare
	AuthenticationServiceAsync authService = GWT
			.create(AuthenticationService.class);

	private KeyDownHandler keydownh = new KeyDownHandler() {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				login(usr.getText(), pwd.getText());
			}
		}
	};

	private AsyncCallback<String> loginresponsehandler = new AsyncCallback<String>() {

		@Override
		// perfect no crapat netu sau serveru
		public void onSuccess(String sessionid) {
			if (sessionid.equals("")) { // nu am primit un sessionid
				badauth.setVisible(true);
			} else { // suntem logati
				Auth.sessionid = sessionid;
				Date expires = new Date(System.currentTimeMillis() + 1000 * 60
						* 60 * 1);
				Cookies.setCookie("sid", sessionid, expires, null, "/", false);
				showMain();
			}
		}

		@Override
		public void onFailure(Throwable caught) {
			new ErrorDialog(caught.toString()).show();
		}
	};

	public LoginScreen() {

	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		login(usr.getText(), pwd.getText());
	}

	private void showMain() {
		RootLayoutPanel.get().clear();// golim fereastra browserului
		Main mainpanel = new Main(); // instantiem widgetul care afiseaza
										// contractele
		// mainpanel.setWidth("100%");
		RootLayoutPanel.get().add(mainpanel);// sil adaugam in panou

		// TODO : invoca un serviciu care determina nr
		// matricol pt userul curent
		// daca nu are nr matricol (ex prof) nu afisa
		// widgetul asta
		mainpanel.userchanged(1040); // ii zicem widgetului sa randeze
										// contractul studentului 1040
	}
	
	private void showLogin(){
		Widget htmlp = uiBinder.createAndBindUi(this);
		usr.addKeyDownHandler(keydownh);
		pwd.addKeyDownHandler(keydownh);
		badauth.setVisible(false);
		
		RootLayoutPanel.get().add(htmlp);
		usr.setFocus(true);
	}
	
	/**
	 * mainul aplicatiei ataseaza widgetul ferestrii browserului
	 */
	@Override
	public void onModuleLoad() {
		// mai jos implementare autentificare
		String sessionID = Cookies.getCookie("sid");
		if (sessionID != null) {
			authService.isSessionValid(sessionID, new AsyncCallback<Boolean>() {
				@Override
				public void onSuccess(Boolean sessionvalid) {
					if (sessionvalid) {
						showMain();
					}else{
						showLogin();
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getLocalizedMessage());
				}
			});
		}else{
			showLogin();
		}
	}

	private void login(final String username, String password) {
		// metoda se apleleaza ca si cum ar fi locala , ii dau parametri si un
		// command object
		// pe care sa-l apeleza dupe ce s-o gatat apelul de metoda
		// In fundal GWT o sa vorbeasca cu serverul aici
		authService.autenticate(username, password, loginresponsehandler);
	}

}
