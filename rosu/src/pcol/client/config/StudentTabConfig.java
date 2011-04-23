package pcol.client.config;

import java.util.Arrays;
import java.util.List;

import pcol.client.contract.ContractPlace;
import pcol.client.materii.MateriePlace;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.TemePlace;
import pcol.client.tweet.TweetPlace;

import com.google.gwt.place.shared.Place;

public class StudentTabConfig implements TabPlaceMapper {
	public static final String TEME = "teme";
	public static final String ORAR = "orar";
	public static final String CONTRACT = "contract";
	public static final String NOUTATI = "noutati";

	@Override
	public List<String> getTabs() {
		return Arrays.asList(NOUTATI, ORAR, TEME, CONTRACT);
	}

	@Override
	public String getTab(Place place) {

		if (place instanceof TweetPlace) {
			return NOUTATI;
		} else if (place instanceof ContractPlace) {
			return CONTRACT;
		} else if (place instanceof MateriePlace) {
			return ORAR;
		} else if (place instanceof TemePlace) {
			return TEME;
		} else if (place instanceof SchedulePlace) {
			return ORAR;
		} else {
			return "";
		}
	}

	@Override
	public Place getPlace(String selectedItem) {
		if (selectedItem.equals(NOUTATI)) {
			return new TweetPlace();
		} else if (selectedItem.equals(CONTRACT)) {
			return new ContractPlace();
		} else if (selectedItem.equals(ORAR)) {
			return new SchedulePlace();
		} else if (selectedItem.equals(TEME)) {
			return new TemePlace();
		} else {
			return null;
		}
	}
}
