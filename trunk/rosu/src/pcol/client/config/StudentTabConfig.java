package pcol.client.config;

import com.google.gwt.place.shared.PlaceHistoryMapper;


public class StudentTabConfig extends TabPlaceMapper {
	public StudentTabConfig(PlaceHistoryMapper placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("noutati", "noutati",false));
		tabs.add(new Tab("orar", "orar",false));
		tabs.add(new Tab("teme", "teme",false));
		tabs.add(new Tab("contract", "contract",true));
	}
}
