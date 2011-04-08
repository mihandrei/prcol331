package pcol.client.contract;

import java.util.List;
import java.util.Set;

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

//conform mvp 2 ar fi CourseGroupWidget<T> si un colundefinition 
//s-ar ocupa de randat un row.
//Nu ma complic dar ca sa ilustrez ca widgetu nu tre sa stie modelu decat la randare
//rows e  List<Object>

public class CourseGroupWidget extends Composite {
	public interface Presenter{
		void onSelectionChanged(Object object);
		void start();
		void setCourseGroup(CourseGroup cg);
		void setParentPresenter(ContractView.Presenter parentPresenter);
	}
	public interface SelectionModel{
		void setSelected(Object object, Boolean inscris);
		boolean isSelected(Object object);
		Set<Object> getSelected();
	}
	
	@UiField
	FlexTable tbl;
	@UiField
	Label caption;
	@UiField
	MyStyle style;

	private List<?> rowData;
	private Presenter presenter;
	
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
	}
	
	public void setPresenter(Presenter presenter){
		this.presenter = presenter;
	}
	
	public void setTitle(String s){
		caption.setText(s);
	}
	
	public void setRowData(List<Course> courses){
		rowData = courses;
		tbl.removeAllRows();
		
		for (int i = 0; i < courses.size(); i++) {

			Course c = courses.get(i);
			
			CheckBox inscris = new CheckBox();
			inscris.setValue(c.inscris);
			tbl.setWidget(i, 0, inscris);  
			tbl.setText(i, 1, c.id);
			tbl.setText(i, 2, c.name);
			tbl.setText(i, 3, c.credits + " credite");
			
			String status = "";
			if(c.nota != null){
			//asta e business rule, tre mutata de aici; 
			//sau consider metoda asta un fel de columnmodel responsabil de randare? 
			   if(c.nota < 5){  
				   status = "<span class=\"" + style.picat()+ "\"> cazut cu " +
				   c.nota + "</span>";
			   }else {
				   status = "<span class=\"" + style.luat()+"\"> luat cu " +
				   c.nota + "</span>";
			   }
			}	
			tbl.setHTML(i, 4, status);
		}
		
		tbl.getColumnFormatter().setStylePrimaryName(0, style.col0());
		tbl.getColumnFormatter().setStylePrimaryName(1, style.col1());
		tbl.getColumnFormatter().setStylePrimaryName(3, style.col3());
		tbl.getColumnFormatter().setStylePrimaryName(4, style.col4());
	}
	
	@UiHandler("tbl")
	void onTblClick(ClickEvent event) {
		int rowidx = tbl.getCellForEvent(event).getRowIndex();
		presenter.onSelectionChanged(rowData.get(rowidx));
	}
	
	/**
	 * culori pentru randuri in functie de selectie
	 */
	public void setSelection(SelectionModel selection) {
		for (int i = 0; i < rowData.size(); i++) {
			Object object = rowData.get(i);
			((CheckBox)tbl.getWidget(i, 0)).setValue(selection.isSelected(object),false);
			
			if (selection.isSelected(object)) {
				tbl.getRowFormatter().addStyleName(i, style.selected());
			}else{
				tbl.getRowFormatter().removeStyleName(i, style.selected());
			}
		}
	}

}
