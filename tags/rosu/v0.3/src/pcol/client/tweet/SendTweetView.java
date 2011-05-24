package pcol.client.tweet;

import com.google.gwt.user.client.ui.IsWidget;

public interface SendTweetView extends IsWidget{
	public interface Presenter {
		void send();
	}

	public void setChannel(String ch);
	public void setMsg(String msg);
	public void setPresenter(Presenter p);
	public String getMsg();
	public String getChan();
}
