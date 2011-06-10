package pcol.client.teme;

import java.util.Date;
import java.util.List;

import pcol.client.AppLoader;
import pcol.client.materii.MaterieShell;
import pcol.client.materii.MateriiService;
import pcol.client.materii.MateriiServiceAsync;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.Course;
import pcol.shared.Tema;
import pcol.shared.TemaStudent;

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
		rpc.getTemeStudent(AppLoader.getApp().getSid(),
				place.getMaterieid(),
			new AppAsyncCallback<List<Tema>>() {

				@Override
				public void onSuccess(List<Tema> temestudent) {
					for(Tema tema:temestudent){
						view.addItem("status", tema.description,"tema/"+tema.materieid, tema.deadline);
					}
				}

		});

		panel.setWidget(shell.asWidget());
	}

}
