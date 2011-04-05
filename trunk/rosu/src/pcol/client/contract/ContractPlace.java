package pcol.client.contract;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContractPlace extends Place{
	private ContractPlace (){}
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
