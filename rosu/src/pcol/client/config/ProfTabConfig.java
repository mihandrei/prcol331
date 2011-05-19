package pcol.client.config;

import com.google.gwt.place.shared.Place;

public class ProfTabConfig extends TabPlaceMapper {
	public ProfTabConfig(PlaceConfigBase placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("noutati", "noutati", false));
		tabs.add(new Tab("orar", "orar", false));
	}
	
	
	@Override
	public String getTab(Place place) {
		String tabToken =  super.getTab(place);
		if("materie".equals(tabToken)){
			tabToken = "orar";
		}
		return tabToken;
	}
}
