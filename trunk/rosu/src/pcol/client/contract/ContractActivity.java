package pcol.client.contract;

import java.util.Arrays;
import java.util.List;

import pcol.client.contract.CourseGroupWidget.Presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class ContractActivity extends AbstractActivity implements
		ContractView.Presenter {
	
	private ContractView view;
	private int credittotal;

	public ContractActivity(ContractView view) {
		this.view=view;
	}

	private void addCgs(List<CourseGroup> cgs,String semid){
		for(CourseGroup courseGroup:cgs){
		
			credittotal += getSelectedCredits(courseGroup);	
			view.setCreditTotal(credittotal);
		
	        CourseGroupWidget cgw = new CourseGroupWidget();        
	        Presenter cgwPresenter  = new CourseGroupPresenter(cgw);
	        cgw.setPresenter(cgwPresenter);
	        cgwPresenter.setParentPresenter(this);
			cgwPresenter.setCourseGroup(courseGroup );
			cgwPresenter.start();
		
			view.addToCategory(semid, Arrays.asList(cgw));
		}
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		view.clearAllCategories();
		credittotal = 0;
		
		addCgs(Arrays.asList(CourseGroup.getMock1(),
				CourseGroup.getMock3(),CourseGroup.getMock2()), "sem1");
		addCgs(Arrays.asList(CourseGroup.getMock4(),
				       CourseGroup.getMock5()), "sem2");
		
        panel.setWidget(view);
	}

	@Override
	public void onSave() {
		// TODO Auto-generated method stub
		
	}

	private int getSelectedCredits(CourseGroup cg){
		int credits = 0;
		for(int i =0;i<cg.courses.size();i++){
			if(cg.courses.get(i).inscris){
				credits +=  cg.courses.get(i).credits;
			}
		}
		return credits;
	}
	
	public void onCgChanged(CourseGroup old, CourseGroup cg) {
		credittotal += getSelectedCredits(cg);
		credittotal -= getSelectedCredits(old);
		view.setCreditTotal(credittotal);
	}

    
}
