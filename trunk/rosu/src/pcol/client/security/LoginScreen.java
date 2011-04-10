package pcol.client.security;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginScreen extends DialogBox implements LoginView{
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

	private Presenter presenter ;
	interface LoginScreenUiBinder extends UiBinder<Widget, LoginScreen> {
	}

	private KeyDownHandler keydownh = new KeyDownHandler() {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				presenter.login(usr.getText(), pwd.getText());
			}
		}
	};

	public LoginScreen() {		
		setWidget(uiBinder.createAndBindUi(this));
		setGlassEnabled(true);
		setText("Authentication required");

		usr.addKeyDownHandler(keydownh);
		pwd.addKeyDownHandler(keydownh);
		badauth.setVisible(false);
	}

	@Override
	public void setPresenter(Presenter p) {
		this.presenter=p;
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		presenter.login(usr.getText(), pwd.getText());
	}
	@Override
	public void showLoginError(){
		badauth.setVisible(true);
	}

	@Override
	public void reset(){
		badauth.setVisible(false);
		usr.setValue("");
		pwd.setValue("");
	}
	
	@Override
	public void showscreen(){
		center();
		usr.setFocus(true);		
	}
}
