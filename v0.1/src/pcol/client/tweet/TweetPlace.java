package pcol.client.tweet;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
/**
 * @author miha
 * toate instantele sunt .equals intre ele
 */
public class TweetPlace extends Place {
	@Override
	public int hashCode() {
		return 4;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		return obj instanceof TweetPlace;
	}
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
