package pcol.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ReLoginDialog extends DialogBox implements ReLoginView{
	// standard uibinder
	private static ReLoginDialogUiBinder uiBinder = GWT
			.create(ReLoginDialogUiBinder.class);

	@UiField
	Button button;
	@UiField
	PasswordTextBox pwd;
	@UiField
	Label badauth;

	private Presenter presenter ;
	interface ReLoginDialogUiBinder extends UiBinder<Widget, ReLoginDialog> {
	}

	private KeyDownHandler keydownh = new KeyDownHandler() {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				presenter.relogin(pwd.getText());
			}
		}
	};

	public ReLoginDialog() {		
		setWidget(uiBinder.createAndBindUi(this));
		setGlassEnabled(true);
		setText("Confirmati parola");

		pwd.addKeyDownHandler(keydownh);
		badauth.setVisible(false);
	}

	@Override
	public void setPresenter(Presenter p) {
		this.presenter=p;
	}

	private void onLogin(){
		pwd.setEnabled(false);
		button.setEnabled(false);
		presenter.relogin(pwd.getValue());
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		onLogin();
	}
	
	@UiHandler("pwd")
	void onPwdKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			onLogin();
		}
	}
	
	@Override
	public void showLoginError(){
		badauth.setVisible(true);
		pwd.setEnabled(true);
		button.setEnabled(true);
	}

	@Override
	public void reset(){
		badauth.setVisible(false);
		pwd.setValue("");
		pwd.setEnabled(true);
		button.setEnabled(true);
	}
	
	@Override
	public void showscreen(){
		center();
		pwd.setFocus(true);
	}
}
