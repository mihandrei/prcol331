package pcol.client.config;

import com.google.gwt.place.shared.PlaceHistoryMapper;

public class ProfTabConfig extends TabPlaceMapper {
	public ProfTabConfig(PlaceHistoryMapper placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("noutati", "noutati",false));
		tabs.add(new Tab("orar", "orar",false));
		tabs.add(new Tab("optimizare", "materie/1",true));
		tabs.add(new Tab("Ai", "materie/5",true));
	}
}
