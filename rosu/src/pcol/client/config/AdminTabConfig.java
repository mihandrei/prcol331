package pcol.client.config;

import java.util.Arrays;
import java.util.List;

import pcol.client.useradmin.UsrAdminPlace;

import com.google.gwt.place.shared.Place;

public class AdminTabConfig implements TabPlaceMapper {
	public static final String INMATRICULARE = "inmatriculare";
	@Override
	public List<String> getTabs(){
		return Arrays.asList(INMATRICULARE);
	}

	@Override
	public String getTab(Place place){
		if (place instanceof UsrAdminPlace){
	    	return INMATRICULARE;
		}else {
			return "";
		}
	}

	@Override
	public Place getPlace(String selectedItem) {
		if(selectedItem.equals(INMATRICULARE)){
	    	return new UsrAdminPlace();
		}else{
			return null;
		}
	}
}
