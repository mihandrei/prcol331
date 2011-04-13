package pcol.client.schedule;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SchedulePlace extends Place {
	@Override
	public int hashCode() {
		return 2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		return obj instanceof SchedulePlace;
	}
	
	public static class Tokenizer implements PlaceTokenizer<SchedulePlace>{
        private SchedulePlace place = new SchedulePlace();
		
        @Override
		public SchedulePlace getPlace(String token) {
			return place;
		}

		@Override
		public String getToken(SchedulePlace place) {
			return null;
		}
	}
	
}
