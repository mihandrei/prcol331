package pcol.client.tweet;

import java.util.List;

import pcol.shared.Tweet;

import com.google.gwt.user.client.ui.IsWidget;


public interface TweetView extends IsWidget{
	public interface Presenter {
		void getMoreTweets();
	}

	public void appendTweets(List<Tweet> tweets);
	public void setPresenter(Presenter p);
	public void clear();
}
