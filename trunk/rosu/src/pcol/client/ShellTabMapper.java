package pcol.client;

import pcol.client.contract.ContractPlace;
import pcol.client.materii.MateriePlace;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.TemePlace;
import pcol.client.tweet.TweetPlace;
import pcol.client.useradmin.UsrAdminPlace;

import com.google.gwt.place.shared.Place;

public class ShellTabMapper {
	public String getTab(Place place){
		
		if (place instanceof TweetPlace)
			return "noutati";
		else if (place instanceof ContractPlace)
			return "contract";
		else if (place instanceof MateriePlace)
			return "orar";
		else if(place instanceof TemePlace){
			return "teme";
		}else if (place instanceof SchedulePlace){
			return "orar";
		}else if (place instanceof UsrAdminPlace){
			return "inmatriculare";
		}else {
			return "";
		}
	}

	public Place getPlace(String selectedItem) {
		if(selectedItem.equals("noutati")){
			return new TweetPlace();
		}else if(selectedItem.equals("contract")){
			return new ContractPlace();
		}else if(selectedItem.equals("orar")){
			return new SchedulePlace();
		}else if(selectedItem.equals("teme")){
			return new TemePlace();
		}else if(selectedItem.equals("inmatriculare")){
			return new UsrAdminPlace();
		}else{
			return null;
		}
	}
}
