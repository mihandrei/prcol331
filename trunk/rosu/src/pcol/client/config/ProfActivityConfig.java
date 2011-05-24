package pcol.client.config;

import pcol.client.materii.EditDescriereActivity;
import pcol.client.materii.EditDescrierePlace;
import pcol.client.materii.EditMaterialeActivity;
import pcol.client.materii.EditMaterialePlace;
import pcol.client.materii.ProfOverviewActivity;
import pcol.client.materii.ProfOverviewPlace;
import pcol.client.schedule.ScheduleActivity;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.EditTemeActivity;
import pcol.client.teme.EditTemePlace;
import pcol.client.tweet.SendTweetActivity;
import pcol.client.tweet.SendTweetPlace;
import pcol.client.tweet.TweetActivity;
import pcol.client.tweet.TweetPlace;

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
		}else if (place instanceof SendTweetPlace){
			return new SendTweetActivity((SendTweetPlace)place);
		}else if (place instanceof ProfOverviewPlace){
			return new ProfOverviewActivity((ProfOverviewPlace)place);
		}else if (place instanceof EditDescrierePlace){
			return new EditDescriereActivity((EditDescrierePlace)place);
		}else if (place instanceof EditMaterialePlace){
			return new EditMaterialeActivity((EditMaterialePlace)place);
		}else if (place instanceof EditTemePlace){
			return new EditTemeActivity((EditTemePlace)place);
		}else if (place instanceof SchedulePlace) {
			return new ScheduleActivity();
		}
		return null;
	}

}
