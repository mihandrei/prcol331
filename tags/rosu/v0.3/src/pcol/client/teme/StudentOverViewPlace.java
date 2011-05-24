package pcol.client.teme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StudentOverViewPlace extends Place{
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
		if (!(obj instanceof StudentOverViewPlace))
			return false;
		
		StudentOverViewPlace other = (StudentOverViewPlace) obj;
		return materieid == other.materieid;
	}
	
	//TODO: enforce materieid!= null
	public StudentOverViewPlace(int materieid){
		this.materieid = materieid;
	}
	
	public static class Tokenizer implements PlaceTokenizer<StudentOverViewPlace>{
		@Override
		public String getToken(StudentOverViewPlace place) {		
			return ""+place.materieid;
		}

		@Override
		public StudentOverViewPlace getPlace(String token) {
			try {
				int cid = Integer.parseInt(token);
				return new StudentOverViewPlace(cid);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
	}
}
