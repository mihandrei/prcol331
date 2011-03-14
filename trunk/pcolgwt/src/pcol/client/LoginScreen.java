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

public class LoginScreen implements EntryPoint {

	private static LoginScreenUiBinder uiBinder = GWT
			.create(LoginScreenUiBinder.class);
	
	@UiField Button button;
	@UiField TextBox usr;
	@UiField PasswordTextBox pwd;
	
	interface LoginScreenUiBinder extends UiBinder<Widget, LoginScreen> {
	}

	AuthenticationServiceAsync contractService = GWT.create(AuthenticationService.class);

	public LoginScreen() {
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		final String username = usr.getText();
			contractService.autenticate(username, pwd.getText(), new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
					if(result.equals("")){
						Window.alert("failed login");
					}else{
						Window.alert(result);
						Auth.sessionid = result;
						Auth.usr = username;
						
						RootPanel.get().clear();
						Main mainpanel = new Main();
						mainpanel.setWidth("100%");
						mainpanel.setHeight("40em");
						RootPanel.get().add(mainpanel );
						
						//TODO : invoca un serviciu care determina nr matricol pt userul curent
						//daca nu are nr matricol (ex prof) nu afisa widgetul asta
						mainpanel.userchanged(1040);
					}
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getLocalizedMessage());
					
				}
			});
	}

	@Override
	public void onModuleLoad() {
		Widget htmlp = uiBinder.createAndBindUi(this);
		RootPanel.get().add(htmlp);
	}
}
