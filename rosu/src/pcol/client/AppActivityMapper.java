package pcol.client;

import pcol.client.contract.ContractActivity;
import pcol.client.contract.ContractPlace;
import pcol.client.materii.MaterieActivity;
import pcol.client.materii.MateriePlace;
import pcol.client.materii.MateriiActivity;
import pcol.client.materii.MateriiPlace;
import pcol.client.teme.TemeActivity;
import pcol.client.tweet.TweetActivity;
import pcol.client.tweet.TweetPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;


public class AppActivityMapper implements ActivityMapper {

	private App app;

	public AppActivityMapper(App app) {
		this.app=app;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TweetPlace)
			return new TweetActivity(app.getTweetView());
		else if (place instanceof ContractPlace)
			return new ContractActivity(app.getContractView());
		else if (place instanceof MateriiPlace)
			return new MateriiActivity(app.getMateriiView());
		else if (place instanceof MateriePlace)
			return new MaterieActivity(app.getMaterieView(),(MateriePlace)place);
		else if(place instanceof TemePlace){
			return new TemeActivity(app.getTemeView());
		}
		return null;
	}

}
