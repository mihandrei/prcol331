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
/**
 * Deseneaza un grup de cursuri
 * 
 */
public class CourseGroupWidget extends Composite implements ClickHandler {
	//creaza un binder de tipul definit de interfata CourseGroupUiBinder
	private static CourseGroupUiBinder uiBinder = GWT
			.create(CourseGroupUiBinder.class);

	//corespund widgeturilor din ....ui.xml care au ui:field cu acelasi nume
	@UiField
	FlexTable tbl;
	@UiField
	CaptionPanel caption;
	@UiField
	MyStyle style;

	/** uibinderul va fi de un tip care injecteaza un Widget intr-un CourseGroupWidget*/
	interface CourseGroupUiBinder extends UiBinder<Widget, CourseGroupWidget> {
	}
	
	/** Cu ajutorul interfetei pot accesa stilurile din ...ui.xml in mod programatic
	 * */
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
		//creaza interfata din ui.xml si o injecteaza in this
		//widgetul intors tre trimis la super.initwidget
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * lista checkboxurilor, in aceasi ordine ca si cursurile din model
	 */
	List<CheckBox> cboxes = new ArrayList<CheckBox>();

	/**
	 * modelul randat de widgetul asta
	 */
	CourseGroup cg;

	/**
	 * invoca metoda asta pentru a da pune in widget date noi
	 * TODO: slabut implementata; itereaza toate cboxurile
	 * ascociaza un index cu fiecare cbox si cauta direct in cursuri
	 */
	public void onModelChange(CourseGroup cg) {
		this.cg = cg;
		tbl.clear();
		cboxes.clear();

		caption.setCaptionText(cg.name);

		for (int i = 0; i < cg.courses.size(); i++) {//pentru fiecare curs
			Course curs = cg.courses.get(i);
			tbl.setText(i, 0, curs.name);//adaug numele in tabela
			tbl.setText(i, 1, curs.credits.toString());

			CheckBox inscris = new CheckBox();//creez un cbox
			cboxes.add(inscris);    //pe care-l adaug listei tuturol chackboxurilor 
			inscris.addClickHandler(this); //atasez un event handler
			
			inscris.setValue(curs.inscris); //il bifez 
			tbl.setWidget(i, 2, inscris); //il adaug 
			tbl.getRowFormatter().setStylePrimaryName(i, style.row()); //asociez un stil
			tbl.getRowFormatter().addStyleName(i, style.deselected());
		}
		tbl.getColumnFormatter().setStylePrimaryName(0, style.col1());
		tbl.getColumnFormatter().setStylePrimaryName(1, style.col2());
		tbl.getColumnFormatter().setStylePrimaryName(2, style.col3());
		applystyle();
		fireModelChanged(); //notific listeneri/oberverii ca modelul randat de widget so schimbat
	}
	
	@Override
	public void onClick(ClickEvent event) {
		CheckBox src = (CheckBox) event.getSource();
		for (int i = 0; i < cg.courses.size(); i++) {//pt fiecare curs si checkbox corespunzator
			Course curs = cg.courses.get(i);
			if (cboxes.get(i) == src) {         //daca cboxu clickanit e cboxu curent
				curs.inscris = src.getValue();  //editeaza cursul corespunzator
			} else {  
				if (src.getValue() && cg.exclusive){ //altfel daca widgetul e exclusiv debifeaza celelate
					cboxes.get(i).setValue(false);
					curs.inscris = false;
				}
			}
		}
		applystyle();
		fireModelChanged();
	}
	
	/**
	 * culori pentru randuri in functie de selectie
	 */
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

	//mai jos implementare obisnuita de event listeneri
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
