package pcol.client.tweet;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SendTweetPlace extends Place {
	private String dest;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SendTweetPlace))
			return false;
		SendTweetPlace other = (SendTweetPlace) obj;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		return true;
	}

	public SendTweetPlace() {
	}
	
	public SendTweetPlace(String token) {
		dest=token;
	}

	public String getDest() {
		return dest;
	}

	public static class Tokenizer implements PlaceTokenizer<SendTweetPlace>{
        @Override
		public SendTweetPlace getPlace(String token) {
        	if(token.trim().isEmpty()){
        		return new SendTweetPlace();
        	}else{
        		return new SendTweetPlace(token);
        	}
		}

		@Override
		public String getToken(SendTweetPlace place) {
			return place.getDest();
		}
	}
}
