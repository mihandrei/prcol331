package pcol.client.config;

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
public class AdminActivityConfig implements ActivityMapper {

	public AdminActivityConfig() {
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof UsrAdminPlace){
			return new UsrAdminActivity();
		}
		return null;
	}

}
