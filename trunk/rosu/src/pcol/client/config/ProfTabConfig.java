package pcol.client.config;

import com.google.gwt.place.shared.PlaceHistoryMapper;

public class ProfTabConfig extends TabPlaceMapper {
	public ProfTabConfig(PlaceHistoryMapper placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("noutati", "noutati"));
		tabs.add(new Tab("inmatriculare", "inmatriculare"));
	}
}
