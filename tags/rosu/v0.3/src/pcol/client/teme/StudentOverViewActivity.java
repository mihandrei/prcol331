package pcol.client.teme;

import java.util.Date;

import pcol.client.materii.MaterieShell;
import pcol.client.materii.MateriiService;
import pcol.client.materii.MateriiServiceAsync;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.Course;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class StudentOverViewActivity extends AbstractActivity implements StudentOverView.Presenter{
	private static MaterieShell shell;
	private static StudentOverView view;
	private static MateriiServiceAsync rpc;

	private StudentOverViewPlace place;

	public StudentOverViewActivity(StudentOverViewPlace place) {
		this.place = place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if (view == null) {
			view = new StudentOverViewImpl();
			shell = new MaterieShell();
			shell.panel.setWidget(view);
			shell.backlink.setText("");
		}
		
		if (rpc == null) {
			rpc = GWT.create(MateriiService.class);
		}

		view.setPresenter(this);
		

		rpc.getCourse(place.getMaterieid(), new AppAsyncCallback<Course>() {
			
			@Override
			public void onSuccess(Course arg0) {
				shell.setCaption("teme la: " + arg0.name);
			}
		});
		view.clear();
		view.addItem("de predat","lab 4","tema/"+3, new Date());		
//		rpc.getTemeStudent(AppLoader.getApp().getSid(),
//				place.getMaterieid(),
//			new AppAsyncCallback<List<TemaStudent>>() {
//
//				@Override
//				public void onSuccess(List<TemaStudent> temestudent) {
//					for(TemaStudent tema:temestudent){
//						view.addItem(tema.status.toString(),tema.name,"tema/"+tema.id, tema.deadline);
//					}
//				}

//		});

		panel.setWidget(shell.asWidget());
	}

}
