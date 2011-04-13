package pcol.client.teme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TemePlace extends Place{
	@Override
	public int hashCode() {
		return 3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		return obj instanceof TemePlace;
	}
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
