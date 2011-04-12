package pcol.client.teme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TemePlace extends Place{
	private TemePlace (){}
	public static class Tokenizer implements PlaceTokenizer<TemePlace> {
		private TemePlace place = new TemePlace();
		
		@Override
		public TemePlace getPlace(String token) {
			return place;
		}

		@Override
		public String getToken(TemePlace place) {
			return null;
		}

	}

}
