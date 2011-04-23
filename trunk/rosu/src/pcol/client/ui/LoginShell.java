package pcol.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginShell extends Composite {
	static {
		AppResources.INSTANCE.style().ensureInjected();
	}

	private static LoginShellUiBinder uiBinder = GWT
			.create(LoginShellUiBinder.class);

	interface LoginShellUiBinder extends UiBinder<Widget, LoginShell> {
	}

	public interface Presenter {
		void login(String usr, String pwd);
	}

	private Presenter p;

	@UiField
	TextBox usr;
	@UiField
	TextBox pwd;
	@UiField
	Label badauth;
	@UiField 
	Button button;

	public LoginShell() {
		initWidget(uiBinder.createAndBindUi(this));
		badauth.setVisible(false);
	}

	public void setPresenter(Presenter p) {
		this.p = p;
	}

	public void showLoginError() {
		badauth.setVisible(true);
		enableControls();
	}

	private void onLogin(){
		usr.setEnabled(false);
		pwd.setEnabled(false);
		button.setEnabled(false);
		p.login(usr.getValue(), pwd.getValue());
	}
	
	private void enableControls(){
		usr.setEnabled(true);
		pwd.setEnabled(true);
		button.setEnabled(true);
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		onLogin();
	}

	@UiHandler("usr")
	void onUsrKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			pwd.setFocus(true);
			pwd.selectAll();
		}
	}

	@UiHandler("pwd")
	void onPwdKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			onLogin();
		}
	}
	
	public void reset(){
		usr.setText("");
		pwd.setText("");
		enableControls();
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		usr.setFocus(true);
	}
}
