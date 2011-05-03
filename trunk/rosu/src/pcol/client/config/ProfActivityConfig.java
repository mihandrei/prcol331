package pcol.client.config;

import pcol.client.tweet.TweetActivity;
import pcol.client.tweet.TweetPlace;
import pcol.client.useradmin.UsrAdminActivity;
import pcol.client.useradmin.UsrAdminPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * clasa de configurare; ar putea fi construita din ceva fisier de config
 * @author miha
 *
 */
public class ProfActivityConfig implements ActivityMapper {

	public ProfActivityConfig() {
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TweetPlace){
			return new TweetActivity();
		}else if (place instanceof UsrAdminPlace){
			return new UsrAdminActivity();
		}
		return null;
	}

}
