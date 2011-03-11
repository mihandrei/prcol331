package pcol.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pcol.shared.Course;
import pcol.shared.CourseGroup;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
//TODO vezi editoarele din gwt si requestfactory
//daca ramane simplu atunci gaseste o metoda sa nu redesenez tot pe update
//si pe click sa nu iterez tot ca sa pun stilurile
//TODO: arunca o privire la mvp, sau mvc
//acum viewul e widgetu si tot el se ocupa sa tina modelul in sync
public class CourseGroupWidget extends Composite implements ClickHandler {

	private static CourseGroupUiBinder uiBinder = GWT
			.create(CourseGroupUiBinder.class);

	@UiField
	FlexTable tbl;
	@UiField
	CaptionPanel caption;
	@UiField
	MyStyle style;

	interface CourseGroupUiBinder extends UiBinder<Widget, CourseGroupWidget> {
	}

	interface MyStyle extends CssResource {
		String selected();
		String deselected();
		String row();
		String col2();
		String col1();
		String col3();
		String caption();
	}

	public CourseGroupWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	List<CheckBox> cboxes = new ArrayList<CheckBox>();

	CourseGroup cg;

	public void onModelChange(CourseGroup cg) {
		this.cg = cg;
		tbl.clear();
		cboxes.clear();

		caption.setCaptionText(cg.name);

		for (int i = 0; i < cg.courses.size(); i++) {
			Course curs = cg.courses.get(i);
			tbl.setText(i, 0, curs.name);
			tbl.setText(i, 1, curs.credits.toString());

			CheckBox inscris = new CheckBox();
			cboxes.add(inscris);
			inscris.addClickHandler(this);
			
			inscris.setValue(curs.inscris);
			tbl.setWidget(i, 2, inscris);
			tbl.getRowFormatter().setStylePrimaryName(i, style.row());
			tbl.getRowFormatter().addStyleName(i, style.deselected());
		}
		tbl.getColumnFormatter().setStylePrimaryName(0, style.col1());
		tbl.getColumnFormatter().setStylePrimaryName(1, style.col2());
		tbl.getColumnFormatter().setStylePrimaryName(2, style.col3());
		applystyle();
		fireModelChanged();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		CheckBox src = (CheckBox) event.getSource();
		for (int i = 0; i < cg.courses.size(); i++) {
			Course curs = cg.courses.get(i);
			if (cboxes.get(i) == src) {
				curs.inscris = src.getValue();
			} else {
				if (src.getValue() && cg.exclusive){
					cboxes.get(i).setValue(false);
					curs.inscris = false;
				}
			}
		}
		applystyle();
		fireModelChanged();
	}
	
	public void applystyle() {
		for (int i = 0; i < cg.courses.size(); i++) {
			Course c = cg.courses.get(i);
			if (c.inscris) {
//				if(cg.exclusive){ //in mod exclusiv daca gasim o bifata colorez tot gridu
//					tbl.getElement().addClassName(style.selected());
//					return;
//				}else{ //a;tfel doar rowu
					tbl.getRowFormatter().addStyleName(i, style.selected());
//				}
			}else{
				tbl.getRowFormatter().removeStyleName(i, style.selected());
			}
		}
//		tbl.getElement().removeClassName(style.selected());
	}

	private List<CgChangedListener> listeners = new LinkedList<CgChangedListener>();
	
	public void addModelChangedListener(CgChangedListener cgChangedListener) {
		listeners.add(cgChangedListener);
	}
	private void fireModelChanged(){
		for(CgChangedListener l:listeners){
			l.onCgChanged(this);
		}
	}
	
}
