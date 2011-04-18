package pcol.client.schedule;

import java.util.ArrayList;
import java.util.List;

import pcol.client.schedule.EventWidget.Range;
import pcol.shared.OrarDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
/**
 * implementeaza un calendar pentru saptamana de lucru
 * evenimentele sunt intr-un pozitionate absolut peste grid
 * 
 * logica de layout ii impartita intre weekviewimpl si eventwidget
 * Asta nu-i o problema, eventwidget face parte conceptual din clasa asta
 * e fisier separat doar ca sa-l folosesc ca template 
 * (s-ar putea emite html-ul dinamic din clasa asta)
 * 
 * idee pentru pixel perfect width:
 * http://www.web-delicious.com/jquery-plugins-demo/wdCalendar/sample.php
 * nucleu' ii un tabel 7*2
 * primul row are un td span 7 care contine un div relative; 
 * in divul asta is puse divuri care deseneaza liniile
 * al doilea row  are un div relative in td-uri
 * fata de divul asta sunt pozitionate absolut eventurile
 * top & height e dat in pixeli absoluti, 
 * restul is procente relative la latimea td-ului  
 * 
 * @author miha
 */
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
	public void addEvent(OrarDto e) {
		Range range = new Range();
		range.startcol = e.day - 1;
		range.endcol = range.startcol+1;
		range.startrow  = e.startHour - 7;
		range.endrow = e.endHour - 7;
		
		int overlaps = 0;
		
		for(EventWidget ew: dayWidgets.get(e.day)){
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

		String head = e.startHour + "-"+ e.endHour;
		if(e.week==2){
			head += " sapt.2";
		}else if(e.week==1){
			head += " sapt1";
		}
		
		String token="";
		//HACK: FIXME: decizia asta nu tre sa fie in view
		if(e.activityType.equals("curs")){
			token+="materie";
		}else if(e.activityType.equals("sem")){
			token+="materie";
		}else if(e.activityType.equals("lab")){
			token+="teme";
		}
		token+="/"+e.courseId;
		ew.setTexts(token,head, e.abbrev, e.activityType,e.room);
		ew.getElement().getStyle().setBackgroundColor("#EEEEFF");
		
		dayWidgets.get(e.day).add(ew);
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
