package pcol.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

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

	interface LoginScreenUiBinder extends UiBinder<Widget, LoginScreen> {
	}

	// GWT creeaza un proxy pentu serviciul de autentificare
	AuthenticationServiceAsync contractService = GWT
			.create(AuthenticationService.class);

	public LoginScreen() {
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		final String username = usr.getText();
        
		//metoda se apleleaza ca si cum ar fi locala , ii dau parametri si un command object
		//pe care sa-l apeleza dupe ce s-o gatat apelul de metoda
		//In fundal GWT o sa vorbeasca cu serverul aici
		contractService.autenticate(username, pwd.getText(),
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) { //perfect no crapat netu sau serveru
						if (result.equals("")) {	//nu am primit un sessionid
							Window.alert("failed login");
						} else {	//suntem logati
//							Window.alert(result);
							Auth.sessionid = result; //tinem minte detalii de login
							Auth.usr = username;

							RootPanel.get().clear();//golim fereastra browserului 
							Main mainpanel = new Main(); //instantiem widgetul care afiseaza contractele
							mainpanel.setWidth("100%");
							mainpanel.setHeight("40em");
							RootPanel.get().add(mainpanel); //sil adaugam in panou

							// TODO : invoca un serviciu care determina nr
							// matricol pt userul curent
							// daca nu are nr matricol (ex prof) nu afisa
							// widgetul asta
							mainpanel.userchanged(1040); //ii zicem widgetului sa randeze contractul studentului 1040
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());

					}
				});
	}

	/**
	 * mainul aplicatiei ataseaza widgetul ferestrii browserului
	 */
	@Override
	public void onModuleLoad() {
		Widget htmlp = uiBinder.createAndBindUi(this);
		RootPanel.get().add(htmlp);
	}
}
