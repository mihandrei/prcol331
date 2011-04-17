package pcol.client.contract;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Place-urile is menite sa fie lightweight : instantiate si aruncate.
 * 
 * Insa 2 instante a unui ContractPlace sunt intodeuna egale coceptual 
 * pentru ca se refera la acelasi loc.
 * 
 * De aceea mai e o ciudata implementare de hashcode si equals
 * hashcode e mereu 1 si mereu contractplace.equals(altconntractplace)
 */
public class ContractPlace extends Place{
	
	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		return obj instanceof ContractPlace;
	}

	public static class Tokenizer implements PlaceTokenizer<ContractPlace> {
		private ContractPlace place = new ContractPlace();
		
		@Override
		public ContractPlace getPlace(String token) {
			return place;
		}

		@Override
		public String getToken(ContractPlace place) {
			return null;
		}
	}

}
