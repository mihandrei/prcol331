package pcol.client.schedule;

import java.util.ArrayList;
import java.util.List;

import pcol.client.schedule.EventWidget.Range;
import pcol.client.schedule.ScheduleItem.Week;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WeekViewImpl extends Composite implements WeekView {

	private static final int NCOLS = 5;

	private static AbsUiBinder uiBinder = GWT.create(AbsUiBinder.class);

	interface AbsUiBinder extends UiBinder<Widget, WeekViewImpl> {
	}

	@UiField
	HTMLPanel weekWrap;

	private List<List<EventWidget>> dayWidgets;
	
	public WeekViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		dayWidgets = new ArrayList<List<EventWidget>>(NCOLS);
		for(int i=0;i<NCOLS;i++){
			dayWidgets.add(new ArrayList<EventWidget>(6));
		}
	}

	@Override
	public void addEvent(ScheduleItem e) {
		final int day = e.day.ordinal();

		Range range = new Range();
		range.startcol = day;
		range.endcol = range.startcol+1;
		range.startrow  = e.starthour - 7;
		range.endrow = e.endhour - 7;
		
		int overlaps = 0;
		
		for(EventWidget ew: dayWidgets.get(day)){
			Range r = ew.getRange();
			//if intersects
			if(range.startrow<r.endrow && range.endrow>r.startrow){
				r.coldivident+=1;
				ew.setRange(r);
				overlaps += 1;
			}
		}
		
		EventWidget ew = new EventWidget();
		
		range.indent = overlaps;
		range.coldivident = overlaps+1;

		ew.setRange(range);

		String head = e.starthour + "-"+ e.endhour;
		if(e.week==Week.EVEN){
			head += " sapt.2";
		}else if(e.week==Week.ODD){
			head += " sapt1";
		}
		ew.setTexts(head, e.name, e.type, e.sala);
		ew.getElement().getStyle().setBackgroundColor("#EEEEFF");
		
		dayWidgets.get(day).add(ew);
		weekWrap.add(ew);
	}

	@Override
	public void clear() {
		weekWrap.clear();
		for(List<EventWidget> l:dayWidgets){
			l.clear();
		}
	}

	@Override
	public void setPresenter(ScheduleActivity scheduleActivity) {
		// TODO Auto-generated method stub
		
	}

}
