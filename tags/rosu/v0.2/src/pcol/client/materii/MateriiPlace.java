package pcol.client.materii;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MateriiPlace extends Place {
	private MateriiPlace(){}
	
	public static class Tokenizer implements PlaceTokenizer<MateriiPlace>{
        private MateriiPlace place = new MateriiPlace();
		
        @Override
		public MateriiPlace getPlace(String token) {
			return place;
		}

		@Override
		public String getToken(MateriiPlace place) {
			return null;
		}
	}
	
}
