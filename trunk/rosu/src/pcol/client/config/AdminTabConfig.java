package pcol.client.config;

import java.util.Arrays;
import java.util.List;

import pcol.client.tweet.TweetPlace;
import pcol.client.useradmin.UsrAdminPlace;

import com.google.gwt.place.shared.Place;

public class AdminTabConfig implements TabPlaceMapper {
	public static final String NOUTATI = "noutati";
	public static final String INMATRICULARE = "inmatriculare";
	@Override
	public List<String> getTabs(){
		return Arrays.asList(NOUTATI,INMATRICULARE);
	}

	@Override
	public String getTab(Place place){
		if (place instanceof TweetPlace){
			return NOUTATI;
		}else if (place instanceof UsrAdminPlace){
	    	return INMATRICULARE;
		}else {
			return "";
		}
	}

	@Override
	public Place getPlace(String selectedItem) {
		if(selectedItem.equals(NOUTATI)){
			return new TweetPlace();
		}else if(selectedItem.equals(INMATRICULARE)){
	    	return new UsrAdminPlace();
		}else{
			return null;
		}
	}
}
