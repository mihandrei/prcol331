package pcol.client.materii;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditMaterialePlace extends Place{
	private String materieid;
	
	public String getMaterieid() {
		return materieid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ materieid.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EditMaterialePlace))
			return false;
		
		EditMaterialePlace other = (EditMaterialePlace) obj;
		return materieid.equals(other.materieid);
	}
	
	//TODO: enforce materieid!= null
	public EditMaterialePlace(String materieid){
		this.materieid = materieid;
	}
	
	public static class Tokenizer implements PlaceTokenizer<EditMaterialePlace>{
		@Override
		public String getToken(EditMaterialePlace place) {		
			return place.materieid;
		}

		@Override
		public EditMaterialePlace getPlace(String token) {
			return new EditMaterialePlace(token);
		}
	}
}
