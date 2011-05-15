package pcol.client.config;

import com.google.gwt.place.shared.PlaceHistoryMapper;


public class StudentTabConfig extends TabPlaceMapper {
	public StudentTabConfig(PlaceHistoryMapper placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("noutati", "noutati"));
		tabs.add(new Tab("orar", "orar"));
		tabs.add(new Tab("teme", "teme"));
		tabs.add(new Tab("contract", "contract"));
	}
}
