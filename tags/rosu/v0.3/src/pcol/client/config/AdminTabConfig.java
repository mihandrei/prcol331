package pcol.client.config;

public class AdminTabConfig extends TabPlaceMapper {
	public AdminTabConfig(PlaceConfigBase placeconfig) {
		super(placeconfig);
		tabs.add(new Tab("inmatriculare", "inmatriculare", false));
	}
}
