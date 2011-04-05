package pcol.client.tweet;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TweetPlace extends Place {
	private TweetPlace(){}
	public static class Tokenizer implements PlaceTokenizer<TweetPlace>{
        private TweetPlace place = new TweetPlace();
		
        @Override
		public TweetPlace getPlace(String token) {
			return place;
		}

		@Override
		public String getToken(TweetPlace place) {
			return null;
		}
	}
}
