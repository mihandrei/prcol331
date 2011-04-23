package pcol.client.config;

import java.util.List;

import com.google.gwt.place.shared.Place;

public interface TabPlaceMapper {
	List<String> getTabs();
	String getTab(Place place);
	Place getPlace(String selectedItem);
}