package pcol.client.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class TabPlaceMapper {
	public static class Tab{
		public String name;
		public String historyToken;
		public boolean small = false;
		
		public Tab(String name, String historyToken,boolean small) {
			this.name = name;
			this.historyToken = historyToken;
			this.small=small;
		}
	}
	
	protected List<Tab> tabs = new ArrayList<Tab>();
	protected final PlaceConfigBase placeconfig;
	
	public TabPlaceMapper(PlaceConfigBase placeconfig){
		this.placeconfig=placeconfig;
	}
	
	public List<Tab> getTabs(){
		return tabs;
	}
	
	public String getTab(Place place){
		return placeconfig.getPlaceToken(place);
	}
}