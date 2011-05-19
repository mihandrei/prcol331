package pcol.client.teme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditTemePlace extends Place {
	private int materieid;

	public int getMaterieid() {
		return materieid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.valueOf(materieid).hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EditTemePlace))
			return false;

		EditTemePlace other = (EditTemePlace) obj;
		return materieid == other.materieid;
	}

	// TODO: enforce materieid!= null
	public EditTemePlace(int materieid) {
		this.materieid = materieid;
	}

	public static class Tokenizer implements PlaceTokenizer<EditTemePlace> {
		@Override
		public String getToken(EditTemePlace place) {
			return "" + place.materieid;
		}

		@Override
		public EditTemePlace getPlace(String token) {
			try {
				int cid = Integer.parseInt(token);
				return new EditTemePlace(cid);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
	}
}
