package pcol.client.materii;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MaterieActivity extends AbstractActivity implements MaterieView.Presenter {
	private static Logger log = Logger.getLogger(MaterieActivity.class.getName());
	private MaterieView materieView;
	private MateriePlace place;

	public MaterieActivity(MaterieView materieView, MateriePlace place) {
		this.materieView=materieView;
		this.place=place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		materieView.setPresenter(this);
		materieView.clearSchedule();
		if(place.getMaterieid().equals("DB")){
			materieView.addScheduleItem(1, 10, 12, "seminar");
			materieView.addScheduleItem(4 , 16, 18, "curs");
			materieView.setMateriale("Miller, Bradley, and David Ranum. Problem Solving " +
					"with Algorithms and Data Structures Using Python. Wilsonville, OR: " +
					"Franklin, Beedle and Associates,");
		}else{
			materieView.addScheduleItem(2, 10, 12, "seminar");
			materieView.addScheduleItem(3 , 8, 10, "curs");
			materieView.setMateriale("zabadum ");

		}
		panel.setWidget(materieView.asWidget());
	}


}
