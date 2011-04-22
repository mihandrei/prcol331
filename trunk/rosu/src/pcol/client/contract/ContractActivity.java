package pcol.client.contract;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pcol.client.App;
import pcol.client.AppLoader;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.Contract;
import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.Curicul;
import pcol.shared.Tuple;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class ContractActivity extends AbstractActivity implements
		ContractView.Presenter , CourseGroupWidget.Presenter{
	
	private static ContractView view;
	private static ContractServiceAsync rpc = null;
	
	private Set<Integer> initiallySelectedCourseIds;
	private Set<Integer> selectedCourseIds;
	
	private Map<Integer, Course> courses;

	private boolean isDirty(){
		//may be null if rpc has not finished
		if(initiallySelectedCourseIds==null) {
			return false; 
		}
		
		return ! initiallySelectedCourseIds.equals(selectedCourseIds);
	}
	
	private int countCreditTotal(){
		int total =0;
	
		for(int key:selectedCourseIds){
			total+= courses.get(key).credits;
		}
		return total;
	}
	
	private class GetCuriculAsyncCallBack extends AppAsyncCallback<Tuple<Curicul,Contract>>{

		@Override
		public void onSuccess(Tuple<Curicul, Contract> result) {
			initiallySelectedCourseIds = new HashSet<Integer>(result.s.selectedcourses);
			courses = new HashMap<Integer, Course>();

			Map<Integer, Float> note = new HashMap<Integer, Float>();
			
//				note.put(ci.cursId,ci.nota);
			selectedCourseIds = new HashSet<Integer>(initiallySelectedCourseIds);
			
			Curicul curicul = result.f;
			for(int sem : curicul.cursuriPeSemestru.keySet()){
				addCgs(curicul.cursuriPeSemestru.get(sem),note, "sem "+sem);
			}
			
			view.setSaveEnabled(isDirty());
			view.setCreditTotal(countCreditTotal());
			AppLoader.getApp().showTipFor("contract");
		}
	}

	private void addCgs(List<CourseGroup> cgs, Map<Integer, Float> note, String semid){
		List<CourseGroupWidget> widgets = new LinkedList<CourseGroupWidget>();
		
		for(CourseGroup cg:cgs){
			CourseGroupWidget cgw = new CourseGroupWidget();  
			cgw.setTitle(cg.name);
			cgw.setPresenter(this);
			
			SelectionModel<Integer> selectionmodel;
			if(cg.exclusive){
				selectionmodel = new SingleSelectionModel<Integer>();
				widgets.add(cgw);
			}else{
				selectionmodel = new MultipleSelectionModel<Integer>();
				widgets.add(0, cgw); //ca sa fie obligatoriile primele 
			}
			
			Collections.sort(cg.courses, new Comparator<Course>() {
				@Override
				public int compare(Course o1, Course o2) {
					return o1.name.compareTo(o2.name);
				}
			});

			for(Course c : cg.courses){
				courses.put(c.id, c);
			
				Float nota = null;
				
				if (selectedCourseIds.contains(c.id)) {
					selectionmodel.setSelected(c.id, true);
					nota = note.get(c.id);
				}
					
				cgw.addRowData(c.id, c.name, c.credits, nota);
			}
			cgw.setSelection(selectionmodel);
		}
		view.addToCategory(semid, widgets);
		
	}
	
	@Override
	public void onSelectionChanged(CourseGroupWidget sender, Integer key) {
		SelectionModel<Integer> selectionmodel = sender.getSelectionModel();
		//deselect this course group in global model, chnage selection then add to global and widget  
		selectedCourseIds.removeAll(selectionmodel.getSelected());
		selectionmodel.setSelected(key, ! selectionmodel.isSelected(key));
		selectedCourseIds.addAll(selectionmodel.getSelected());
		sender.setSelection(selectionmodel);
		
		view.setSaveEnabled(isDirty());
		
		view.setCreditTotal(countCreditTotal());
	}
	
	@Override
	public void onSave() {
		if(isDirty()){
		rpc.submitContract(AppLoader.getApp().getSid(),selectedCourseIds, new AppAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				AppLoader.getApp().showInfo("contractul a fost salvat");
				initiallySelectedCourseIds = new HashSet<Integer>(selectedCourseIds);
				view.setSaveEnabled(isDirty());
			}
		});
		}
	}

	public String mayStop() {
		if(isDirty()){
			return "discard your changes?";
		}
	    return null;
	}
	
	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {
			private GetCuriculAsyncCallBack	getCuriculCallback = new GetCuriculAsyncCallBack();
			
			@Override
			public void onSuccess() {
				if(view == null){
					view = new ContractViewImpl();
				}
				
				if(rpc == null){
					rpc = GWT.create(ContractService.class);
				}
				view.setPresenter(ContractActivity.this);
				view.clearAllCategories();
				panel.setWidget(view);
				rpc.getContractAndCuricul(AppLoader.getApp().getSid(),getCuriculCallback);
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}
