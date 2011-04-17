package pcol.client.schedule;

import pcol.client.App;
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
					
					view.addEvent(new ScheduleItem(8, 10, Day.TUE,Week.ALL, "Retele", "curs", "2/I"));
					view.addEvent(new ScheduleItem(10, 12, Day.TUE,Week.ALL, "Inginerie soft", "curs", "2/I"));
					view.addEvent(new ScheduleItem(14, 16, Day.TUE,Week.ALL, "AI", "curs", "2/I"));
					view.addEvent(new ScheduleItem(14, 16, Day.TUE,Week.ALL, "Algoritmi", "curs", "7/I"));
					view.addEvent(new ScheduleItem(8, 10, Day.TUE,Week.ODD, "OOP", "lab", "L336"));
					
					view.addEvent(new ScheduleItem(8, 10, Day.WED,Week.ALL, "Retele", "lab", "L302"));
					view.addEvent(new ScheduleItem(10, 12, Day.WED,Week.ODD, "Inginerie soft", "lab", "L339"));
					view.addEvent(new ScheduleItem(10, 12, Day.WED,Week.EVEN, "AI", "lab", "L302"));
					view.addEvent(new ScheduleItem(12, 14, Day.WED,Week.ALL, "proj col", "lab", "L307"));
					
					view.addEvent(new ScheduleItem(8, 10, Day.THU,Week.EVEN, "Paper", "curs", "5/I"));
					view.addEvent(new ScheduleItem(10, 12, Day.THU,Week.EVEN, "Inginerie soft", "seminar", "7/I"));
					view.addEvent(new ScheduleItem(12, 14, Day.THU,Week.EVEN, "AI", "seminar", "5/I"));
					view.addEvent(new ScheduleItem(14, 16, Day.THU,Week.ALL, "SO", "curs", "C310"));
					view.addEvent(new ScheduleItem(16, 18, Day.THU,Week.ALL, "SO", "lab", "L339"));
					
					view.addEvent(new ScheduleItem(8, 10, Day.FRI,Week.ALL, "OOP", "curs", "C335"));
					view.addEvent(new ScheduleItem(14, 16, Day.FRI,Week.ODD, "OOP", "seminar", "C509"));
					view.addEvent(new ScheduleItem(14, 16, Day.FRI,Week.EVEN, "Algoritmi", "seminar", "C509"));
					view.addEvent(new ScheduleItem(16, 18, Day.FRI,Week.EVEN, "OOP", "lab", "L307"));
				}
				panel.setWidget(view.asWidget());

				App.getInstance().showTipFor("orar");
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}
