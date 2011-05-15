package pcol.client.config;

import com.google.gwt.place.shared.PlaceHistoryMapper;

public class AdminTabConfig extends TabPlaceMapper {
	public AdminTabConfig(PlaceHistoryMapper placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("inmatriculare", "inmatriculare"));
	}
}
