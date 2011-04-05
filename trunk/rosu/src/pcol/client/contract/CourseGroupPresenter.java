package pcol.client.contract;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MultipleSelectionModel implements CourseGroupWidget.SelectionModel{
	Map<Object,Boolean> selection = new HashMap<Object, Boolean>();
	
	@Override
	public void setSelected(Object object, Boolean inscris) {
		selection.put(object,inscris);
	}

	@Override
	public boolean isSelected(Object object) {
		return selection.containsKey(object) && selection.get(object);
	}
}

public class CourseGroupPresenter implements CourseGroupWidget.Presenter {
	static CourseGroup getACourseGroup(){
		Course c1 =  new Course("MF 002","Analiza complexa",4,null,false);
		Course c2 =  new Course("MF 022","Algebra",4,3.5f,false);
		Course c3 =  new Course("MF 042","Algebra",6,6.5f,false);
		Course c4 =  new Course("MF 102","Mecanica",4,null,true);
		CourseGroup cg = new CourseGroup();
		cg.courses = Arrays.asList(c1,c2,c3,c4);
		cg.exclusive = true;
		cg.name = "obligatorii";
		return cg;
	}
	
    	private CourseGroupWidget view;
		private String semid;
		private CourseGroupWidget.SelectionModel selectionmodel;
    	
    	
		CourseGroupPresenter(CourseGroupWidget cgw, String semid){
    		this.view=cgw;
    		this.semid = semid;
    	}
    	
    	@Override
		public void onSelectionChanged(Object object) {
			selectionmodel.setSelected(object, ! selectionmodel.isSelected(object));
			view.setSelection(selectionmodel);
			//poate ar trebui sa pun o notificare pe eventbus
			//si widgetul care se ocupa de sumar sa se updateze de acolo
			
		}
		
		public void start(){
			CourseGroup cg = getACourseGroup();
			selectionmodel = new MultipleSelectionModel();
			view.setRowData(cg.courses);        
	        view.setTitle(cg.name);
	        
	        for(Course c:cg.courses){
	        	selectionmodel.setSelected(c, c.inscris);
	        }
			view.setSelection(selectionmodel);

		}
	}