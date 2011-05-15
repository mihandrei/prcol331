package pcol.client.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class TabPlaceMapper {
	public static class Tab{
		public String name;
		public String historyToken;
		public Tab(String name, String historyToken) {
			this.name = name;
			this.historyToken = historyToken;
		}
	}
	
	protected List<Tab> tabs = new ArrayList<Tab>();
	private final PlaceHistoryMapper placeconfig;
	
	public TabPlaceMapper(PlaceHistoryMapper placeconfig){
		this.placeconfig=placeconfig;
	}
	
	public List<Tab> getTabs(){
		return tabs;
	}
	
	public String getTab(Place place){
		return placeconfig.getToken(place);
	}
}