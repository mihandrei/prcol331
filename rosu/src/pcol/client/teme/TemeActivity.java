package pcol.client.teme;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import pcol.client.teme.AssignmentOverview.Status;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TemeActivity extends AbstractActivity implements TemeView.Presenter {
private TemeView view;
public TemeActivity(TemeView view){
	this.view = view;
}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		List<AssignmentOverview> aos = Arrays.asList( 
				new AssignmentOverview(Status.DONE,new Date(),"LAb 1","bd/lab1"),
				new AssignmentOverview(Status.PENDING_SUBMISSION,new Date(2011,6,12),"LAb 4","bd/lab4")
		);
		for(AssignmentOverview ao:aos){
			view.addItem(ao.status.toString(),ao.name,ao.id,ao.deadline);
		}
		panel.setWidget(view);
	}
	
}