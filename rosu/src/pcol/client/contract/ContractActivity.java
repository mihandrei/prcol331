package pcol.client.contract;

import java.util.Arrays;

import pcol.client.contract.CourseGroupWidget.Presenter;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class ContractActivity extends AbstractActivity implements
		ContractView.Presenter {
	
	private ContractView view;

	public ContractActivity(ContractView view) {
		this.view=view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		String semid = "sem1";
        
        CourseGroupWidget cgw = new CourseGroupWidget();        
        Presenter cgwPresenter  = new CourseGroupPresenter(cgw, semid);
//        cgwPresenter.onAggregatesChange()
		cgw.setPresenter(cgwPresenter);
		cgwPresenter.start();
		
        view.clearAllCategories();
        view.addToCategory(semid, Arrays.asList(cgw));
        view.addToCategory("traalala", Arrays.asList(cgw));
        panel.setWidget(view);
	}

	@Override
	public void onSave() {
		// TODO Auto-generated method stub
		
	}

    
}
