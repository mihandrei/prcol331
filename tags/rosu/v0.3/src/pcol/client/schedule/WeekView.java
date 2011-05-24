package pcol.client.schedule;

import pcol.shared.OrarDto;

import com.google.gwt.user.client.ui.IsWidget;

public interface WeekView extends IsWidget{
	public interface Presenter {

	}
	void addEvent(OrarDto e);
	void clear();
	void setPresenter(ScheduleActivity scheduleActivity);
}
