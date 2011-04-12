package pcol.client.schedule;

import pcol.client.schedule.ScheduleItem.Day;
import pcol.client.schedule.ScheduleItem.Week;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ScheduleActivity extends AbstractActivity implements
		WeekView.Presenter {
	
	private static WeekView view;
	private static boolean firstrun = true;
	
	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				if(view==null){
					//creerea poate fi deferita la un factory 
					view = new WeekViewImpl();
				}
				//caching the widget; o umplem o data;
				if(firstrun){
					firstrun=false;
					view.setPresenter(ScheduleActivity.this);
					view.clear();
					view.addEvent(new ScheduleItem(14, 16, Day.MON,Week.ALL, "Tehnici de optimizare", "curs", "5/I"));
					view.addEvent(new ScheduleItem(16, 18, Day.MON,Week.EVEN, "Tehnici de optimizare", "seminar", "5/I"));
					
					view.addEvent(new ScheduleItem(8, 10, Day.TUE,Week.ALL, "Retele de calculatoare", "curs", "2/I"));
					view.addEvent(new ScheduleItem(10, 12, Day.TUE,Week.ALL, "ingineria siistemelor soft", "curs", "2/I"));
					view.addEvent(new ScheduleItem(14, 16, Day.TUE,Week.ALL, "inteligenta atificiala", "curs", "2/I"));
					view.addEvent(new ScheduleItem(14, 16, Day.TUE,Week.ALL, "Structuri de date si algoritmi", "curs", "7/I"));
					
					view.addEvent(new ScheduleItem(8, 10, Day.WED,Week.ALL, "Retele de calculatoare", "lab", "L302"));
					view.addEvent(new ScheduleItem(10, 12, Day.WED,Week.ODD, "ingineria sistemelor soft", "lab", "L339"));
					view.addEvent(new ScheduleItem(10, 12, Day.WED,Week.EVEN, "inteligenta atificiala", "lab", "L302"));
					view.addEvent(new ScheduleItem(18, 20, Day.WED,Week.ALL, "proiect colectiv", "lab", "L307"));
					
					view.addEvent(new ScheduleItem(8, 10, Day.THU,Week.EVEN, "Metodologia documentarii si elaborarii unei lucrari stiintifice", "curs", "5/I"));
					view.addEvent(new ScheduleItem(10, 12, Day.THU,Week.EVEN, "Ingineria sistemelor soft", "seminar", "7/I"));
					view.addEvent(new ScheduleItem(12, 14, Day.THU,Week.EVEN, "inteligenta atificiala", "seminar", "5/I"));
					view.addEvent(new ScheduleItem(14, 16, Day.THU,Week.ALL, "Sisteme de operare", "curs", "C310"));
					view.addEvent(new ScheduleItem(16, 18, Day.THU,Week.ALL, "Sisteme de operare", "lab", "L339"));
					
					view.addEvent(new ScheduleItem(14, 16, Day.FRI,Week.EVEN, "Structuri de date si algoritmi", "seminar", "C509"));
				}
				panel.setWidget(view.asWidget());
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}
