package pcol.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginScreen extends Composite implements LoginView{
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
	@UiField SimplePanel progp;

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
		initWidget(uiBinder.createAndBindUi(this));
		usr.addKeyDownHandler(keydownh);
		pwd.addKeyDownHandler(keydownh);
		badauth.setVisible(false);
		usr.setFocus(true);
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
}
