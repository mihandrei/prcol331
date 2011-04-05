package pcol.client.tweet;

import java.util.List;

import pcol.shared.Tweet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;


public class TweetViewImpl extends ResizeComposite implements TweetView{
    @UiField
    FlexTable tbl;
    @UiField ScrollPanel scrollPanel;
    @UiField Button button;
	private Presenter presenter;
    
    public void appendTweets(List<Tweet> tweets){
    	int nrows = tbl.getRowCount();
    	for(int i=0;i<tweets.size();i++){
    		Blip blip = new Blip();
    		blip.setTweet(tweets.get(i));
    		tbl.setWidget(nrows+i, 0, blip);
    	}
    }
    
	private static TweetsUiBinder uiBinder = GWT.create(TweetsUiBinder.class);

	interface TweetsUiBinder extends UiBinder<Widget, TweetViewImpl> {
	}

	public TweetViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter p) {
		this.presenter=p;
	}

	@Override
	public void clear() {
		tbl.removeAllRows();
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		presenter.getMoreTweets();
	}
}
