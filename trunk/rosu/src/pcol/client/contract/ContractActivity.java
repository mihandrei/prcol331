package pcol.client.contract;

import java.util.Arrays;
import java.util.List;

import pcol.client.contract.CourseGroupWidget.Presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class ContractActivity extends AbstractActivity implements
		ContractView.Presenter {
	
	private static ContractView view;
	private int credittotal;
	private boolean dirty;

	public ContractActivity() {
	
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
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				if(view == null){
					view = new ContractViewImpl();
				}
				view.setPresenter(ContractActivity.this);
				view.clearAllCategories();
				credittotal = 0;
				
				addCgs(Arrays.asList(CourseGroup.getMock1(),
						CourseGroup.getMock3(),CourseGroup.getMock2()), "sem1");
				addCgs(Arrays.asList(CourseGroup.getMock4(),
						CourseGroup.getMock5()), "sem2");
				
				panel.setWidget(view);
				
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

	@Override
	public void onSave() {
		// TODO Auto-generated method stub
		dirty = false;
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
		dirty = true;
		credittotal += getSelectedCredits(cg);
		credittotal -= getSelectedCredits(old);
		view.setCreditTotal(credittotal);
	}

	public String mayStop() {
		if(dirty){
			return "discard your changes?";
		}
	    return null;
	}
}
