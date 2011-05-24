package pcol.client.tweet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;


public class SendTweetViewImpl extends Composite implements SendTweetView{
	private Presenter presenter;
    
	private static SendTweetUiBinder uiBinder = GWT.create(SendTweetUiBinder.class);
	@UiField TextArea text;
	@UiField TextBox channel;
	@UiField Button send;

	interface SendTweetUiBinder extends UiBinder<Widget, SendTweetViewImpl> {
	}

	public SendTweetViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter p) {
		this.presenter=p;
	}

	@UiHandler("send")
	void onSendClick(ClickEvent event) {
		presenter.send();
	}

	@Override
	public void setChannel(String ch) {
		channel.setText(ch);
	}

	@Override
	public void setMsg(String msg) {
		text.setText(msg);
	}

	@Override
	public String getMsg() {
		return text.getText().trim();
	}

	@Override
	public String getChan() {
		return channel.getText().trim();
	}
}
