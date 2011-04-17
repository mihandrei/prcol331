package pcol.client.materii;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MateriePlace extends Place {
	private String materieid;
	private String section;
	
	public String getMaterieid() {
		return materieid;
	}

	public String getSection() {
		return section;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ materieid.hashCode();
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MateriePlace))
			return false;
		MateriePlace other = (MateriePlace) obj;
		if (!materieid.equals(other.materieid))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	//TODO: enforce materieid!= null
	public MateriePlace(String materieid){
		this.materieid = materieid;
	}
	
	public MateriePlace(String materieid,String section){
		this.materieid = materieid;
		this.section=section;
	}
	
	public static class Tokenizer implements PlaceTokenizer<MateriePlace>{
		@Override
		public String getToken(MateriePlace place) {		
			String token = "";
			token+=place.materieid;
			if(place.section!=null){
				token+="/"+place.section;
			}
			return token;
		}

		@Override
		public MateriePlace getPlace(String token) {
			String[] frags = token.split("/");
			
			if(frags.length == 1){
				return new MateriePlace(frags[0]);
			}else if(frags.length == 2){
				return new MateriePlace(frags[0], frags[1]);
			}else {
				return null;
			}
		}
	}
}
