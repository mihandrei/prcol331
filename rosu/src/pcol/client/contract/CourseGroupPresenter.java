package pcol.client.contract;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pcol.client.contract.ContractView.Presenter;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

class MultipleSelectionModel implements CourseGroupWidget.SelectionModel{
	private Set<Object> selection = new HashSet<Object>();
	
	@Override
	public void setSelected(Object object, Boolean inscris) {
		if(inscris) {
			selection.add(object);
		}else{
			selection.remove(object);
		}
	}

	@Override
	public Set<Object> getSelected(){
		return selection;
	}
	
	public boolean isSelected(Object object) {
		return selection.contains(object);
	}
}

class SingleSelectionModel implements CourseGroupWidget.SelectionModel{
	Object selection = null;

	@Override
	public void setSelected(Object object, Boolean inscris) {
		selection = inscris? object: null;
	}

	@Override
	public boolean isSelected(Object object) {
		return selection!=null && selection.equals(object);
	}

	@Override
	public Set<Object> getSelected() {
		return new HashSet<Object>(Arrays.asList(selection));
	}
}

/*are acces direct la contractactivity care-i presentarul "parinte"
 * Alta varianta: publica un valuechangedevent pe eventbus, astfel ne decuplam de 
 * contractActivity. Dar nu merita sa mai complic flowwu de dragul decuplarii
 * */

public class CourseGroupPresenter implements CourseGroupWidget.Presenter {
    	private CourseGroupWidget view;
		private CourseGroupWidget.SelectionModel selectionmodel;
		private CourseGroup cg;
		private Presenter parentPresenter;
		
		@Override
		public void setParentPresenter(ContractView.Presenter parentPresenter) {
			this.parentPresenter = parentPresenter;
		}

		CourseGroupPresenter(CourseGroupWidget cgw){
    		this.view=cgw;
    	}
    	
    	//poate ar trebui sa pun o notificare pe eventbus
    	//si widgetul care se ocupa de sumar sa se updateze de acolo
		@Override
		public void onSelectionChanged(Object object) {
			selectionmodel.setSelected(object, ! selectionmodel.isSelected(object));
			view.setSelection(selectionmodel);

			//updatez modelul , notific activitatea parinte de schimbari
			CourseGroup old  = cg.copy();

			for(Course c : cg.courses){
				c.inscris = selectionmodel.isSelected(c);
			}
			
			if(parentPresenter!=null){
				parentPresenter.onCgChanged(old, cg);
			}
		}
		
		public void start(){
			if(cg.exclusive){
				selectionmodel = new SingleSelectionModel();
			}else{
				selectionmodel = new MultipleSelectionModel();
			}
			view.setRowData(cg.courses);        
	        view.setTitle(cg.name);
	        
	        for(Course c:cg.courses){
	        	selectionmodel.setSelected(c, c.inscris);
	        }
			view.setSelection(selectionmodel);
		}
		@Override
		public void setCourseGroup(CourseGroup cg){
			this.cg=cg;
		}

		
}