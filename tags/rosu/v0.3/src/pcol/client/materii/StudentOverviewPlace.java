package pcol.client.materii;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StudentOverviewPlace extends Place{
	private int materieid;
	
	public int getMaterieid() {
		return materieid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ Integer.valueOf(materieid).hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentOverviewPlace))
			return false;
		
		StudentOverviewPlace other = (StudentOverviewPlace) obj;
		return materieid == other.materieid;
	}
	
	//TODO: enforce materieid!= null
	public StudentOverviewPlace(int materieid){
		this.materieid = materieid;
	}
	
	public static class Tokenizer implements PlaceTokenizer<StudentOverviewPlace>{
		@Override
		public String getToken(StudentOverviewPlace place) {		
			return ""+place.materieid;
		}

		@Override
		public StudentOverviewPlace getPlace(String token) {
			try {
				int cid = Integer.parseInt(token);
				return new StudentOverviewPlace(cid);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
	}
}
