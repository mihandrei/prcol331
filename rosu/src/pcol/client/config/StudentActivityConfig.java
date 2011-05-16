package pcol.client.config;

import pcol.client.contract.ContractActivity;
import pcol.client.contract.ContractPlace;
import pcol.client.materii.MaterieActivity;
import pcol.client.materii.StudentOverviewPlace;
import pcol.client.schedule.ScheduleActivity;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.TemeActivity;
import pcol.client.teme.TemePlace;
import pcol.client.tweet.TweetActivity;
import pcol.client.tweet.TweetPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * clasa de configurare; ar putea fi construita din ceva fisier de config
 * 
 * @author miha
 * 
 */
public class StudentActivityConfig implements ActivityMapper {

	public StudentActivityConfig() {
	}

	@Override
	// WARN: historychanged -> runasync.
	// async ok :creeaza viewul daca nu-i cachat in app
	// apoi activitatea
	// DAR activity framework e sincrona asteapta
	// historyhanged->Place->activity->inject into shell
	// Astea ar trebui injectate cumva
	public Activity getActivity(Place place) {
		if (place instanceof TweetPlace)
			return new TweetActivity();
		else if (place instanceof ContractPlace)
			return new ContractActivity();

		else if (place instanceof StudentOverviewPlace)
			return new MaterieActivity((StudentOverviewPlace) place);
		else if (place instanceof TemePlace) {
			return new TemeActivity();
		} else if (place instanceof SchedulePlace) {
			return new ScheduleActivity();
		} else {
			return null;
		}
	}

}
