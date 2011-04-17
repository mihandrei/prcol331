package pcol.client.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pcol.shared.Course;
import pcol.shared.CourseGroup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CourseGroupWidget extends Composite {
	public interface Presenter{
		void onSelectionChanged(CourseGroupWidget sender, Integer key);
	}
	
	@UiField
	FlexTable tbl;
	@UiField
	Label caption;
	@UiField
	MyStyle style;

	private List<Integer> rowkeys = new ArrayList<Integer>();
	private Presenter presenter;
	private SelectionModel<Integer> selectionmodel;
	
	private static CourseGroupUiBinder uiBinder = GWT.create(CourseGroupUiBinder.class);

	interface CourseGroupUiBinder extends UiBinder<Widget, CourseGroupWidget> {
	}
	
	interface MyStyle extends CssResource {
		String selected();
		String col3();
		String col1();
		String col0();
		String caption();
		String picat();
		String luat();
		String col4();
	}
	
	public CourseGroupWidget() {
		initWidget(uiBinder.createAndBindUi(this));
//		tbl.insertCell(0, 5);
		tbl.getColumnFormatter().setStylePrimaryName(0, style.col0());
		tbl.getColumnFormatter().setStylePrimaryName(1, style.col1());
		tbl.getColumnFormatter().setStylePrimaryName(3, style.col3());
		tbl.getColumnFormatter().setStylePrimaryName(4, style.col4());
	}
	
	public void setPresenter(Presenter presenter){
		this.presenter = presenter;
	}
	
	public void setTitle(String s){
		caption.setText(s);
	}
	
	@UiHandler("tbl")
	void onTblClick(ClickEvent event) {
		int rowidx = tbl.getCellForEvent(event).getRowIndex();
		presenter.onSelectionChanged(this,rowkeys.get(rowidx));
		
	}
	
	/**
	 * culori pentru randuri in functie de selectie
	 */
	public void setSelection(SelectionModel<Integer> selection) {
		selectionmodel = selection;
		for (int i = 0; i < rowkeys.size(); i++) {
			Integer key = rowkeys.get(i);
			((CheckBox)tbl.getWidget(i, 0)).setValue(selection.isSelected(key),false);
			
			if (selection.isSelected(key)) {
				tbl.getRowFormatter().addStyleName(i, style.selected());
			}else{
				tbl.getRowFormatter().removeStyleName(i, style.selected());
			}
		}
	}

	public void clear(){
		tbl.removeAllRows();
		rowkeys.clear();
	}
	
	public void addRowData(int id, String name, Integer credits, Float nota) {
		rowkeys.add(id);
		int i = tbl.getRowCount();
		CheckBox inscris = new CheckBox();
		tbl.setWidget(i, 0, inscris);  
		tbl.setText(i, 1, "id:"+id);
		tbl.setText(i, 2, name);
		tbl.setText(i, 3, credits + " credite");
		
		String status = "";
		if(nota != null){
		   if(nota < 5){  
			   status = "<span class=\"" + style.picat()+ "\"> cazut cu " +
			   nota + "</span>";
		   }else {
			   status = "<span class=\"" + style.luat()+"\"> luat cu " +
			   nota + "</span>";
		   }
		}	
		tbl.setHTML(i, 4, status);
	}

	public SelectionModel<Integer> getSelectionModel() {
		return selectionmodel;
	}

}
