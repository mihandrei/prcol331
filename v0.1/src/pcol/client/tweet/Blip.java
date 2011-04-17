package pcol.client.tweet;

import pcol.shared.Tweet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;


public class Blip extends Composite {

	private static TweetWidgetUiBinder uiBinder = GWT
			.create(TweetWidgetUiBinder.class);
	@UiField HTML text;
	@UiField InlineLabel source;
	@UiField DateLabel date;
	@UiField Style style;
	
	interface Style extends CssResource {
		String blip();
	}
	
	interface TweetWidgetUiBinder extends UiBinder<Widget, Blip> {
	}

	public Blip() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTweet(Tweet tw){
		text.setHTML(tw.getText());
		this.source.setText(tw.getSource());
		this.date.setValue(tw.getDate());
		

		switch(tw.getLvl()){
		case INFO:
			break;
		case TASK:
			
			break;
		case WARN:
			break;
		case CRITICAL:
			break;
		}
	}
}
