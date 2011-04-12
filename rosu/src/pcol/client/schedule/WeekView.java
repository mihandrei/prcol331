package pcol.client.schedule;

import com.google.gwt.user.client.ui.IsWidget;

public interface WeekView extends IsWidget{
	public interface Presenter {

	}
	void addEvent(ScheduleItem e);
	void clear();
	void setPresenter(ScheduleActivity scheduleActivity);
}
