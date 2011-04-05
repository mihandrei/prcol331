package pcol.client.materii;

import com.google.gwt.user.client.ui.IsWidget;

public interface MaterieView extends IsWidget{
	public interface Presenter {
	}

	void setPresenter(Presenter presenter);
	void addScheduleItem(int dayOfWeek, int startHour,
			int endHour, String name);
	void clearSchedule();
	void setMateriale(String string);
}
