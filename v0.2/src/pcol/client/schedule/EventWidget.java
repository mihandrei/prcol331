package pcol.client.schedule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
/**
 * poate fi impartit in2 widgeturi unu layer care se ocupa 
 * de pozitionare si un compozit arbitrar cu content
 * @author miha
 */
class EventWidget extends Composite {

	private static EventWidgetUiBinder uiBinder = GWT
			.create(EventWidgetUiBinder.class);

	interface EventWidgetUiBinder extends UiBinder<Widget, EventWidget> {
	}

	@UiField
	HTMLPanel eventPanel;
	@UiField 
	Label eventTitle;
	@UiField 
	Hyperlink cursHy;
	@UiField 
	Label nameLbl;
	@UiField 
	Label salaHy;
	
	static class Range{
		int startrow ;
		int endrow;
		
		int startcol;
		int endcol ;
		int coldivident = 1;
		int indent=0;
		
		int totalrows = 13;
		int totalcols = 5 ;
	}
	
	private Range range;
	
	public Range getRange() {
		return range;
	}
	
	public void setRange(Range range) {
		this.range = range;
		
		float dy = 100f/range.totalrows;
		float dx = 100f/range.totalcols;
		
		float top = range.startrow * dy;
		float height = (range.endrow-range.startrow) * dy;
		float width = (range.endcol - range.startcol) * dx /((float) range.coldivident);
		float left = range.startcol * dx + range.indent*width;
		//hack ca sa nu dea overflow, sa incapa cu border si float err cu tot
		left += 0.2;
		width -= 0.4;
		height -=0.4;
		Style style = getElement().getStyle();
		style.setTop(top, Unit.PCT);
		style.setHeight(height, Unit.PCT);
		style.setLeft(left, Unit.PCT);
		style.setWidth(width, Unit.PCT);
	}


	public void setTexts(String targetToken, String header, String name, String typen, String salan){
		eventTitle.setText(header);
		nameLbl.setText(name);
		cursHy.setText(typen);
		cursHy.setTargetHistoryToken(targetToken);
		salaHy.setText(salan);
	}
	
	public EventWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
