package pcol.client.config;

import pcol.client.materii.EditDescriereActivity;
import pcol.client.materii.EditDescrierePlace;
import pcol.client.materii.EditMaterialeActivity;
import pcol.client.materii.EditMaterialePlace;
import pcol.client.materii.EditTemeActivity;
import pcol.client.materii.EditTemePlace;
import pcol.client.materii.ProfOverviewActivity;
import pcol.client.materii.ProfOverviewPlace;
import pcol.client.tweet.TweetActivity;
import pcol.client.tweet.TweetPlace;
import pcol.client.useradmin.UsrAdminActivity;
import pcol.client.useradmin.UsrAdminPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ProfActivityConfig implements ActivityMapper {

	public ProfActivityConfig() {
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TweetPlace){
			return new TweetActivity();
		}else if (place instanceof ProfOverviewPlace){
			return new ProfOverviewActivity((ProfOverviewPlace)place);
		}else if (place instanceof EditDescrierePlace){
			return new EditDescriereActivity((EditDescrierePlace)place);
		}else if (place instanceof EditMaterialePlace){
			return new EditMaterialeActivity((EditMaterialePlace)place);
		}else if (place instanceof EditTemePlace){
			return new EditTemeActivity((EditTemePlace)place);
		}
		return null;
	}

}
