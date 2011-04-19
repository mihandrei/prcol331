package pcol.client.useradmin;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UsrAdminPlace extends Place {
	@Override
	public int hashCode() {
		return 14;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		return obj instanceof UsrAdminPlace;
	}
	
	public static class Tokenizer implements PlaceTokenizer<UsrAdminPlace>{
		
		@Override
		public UsrAdminPlace getPlace(String token) {
			// TODO Auto-generated method stub
			return new UsrAdminPlace();
		}

		@Override
		public String getToken(UsrAdminPlace place) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
