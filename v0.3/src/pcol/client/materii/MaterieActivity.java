package pcol.client.materii;

import java.util.List;

import pcol.client.security.AppAsyncCallback;
import pcol.shared.Course;
import pcol.shared.Resource;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MaterieActivity extends AbstractActivity implements
		MaterieView.Presenter {
//	private static Logger log = Logger.getLogger(MaterieActivity.class
//			.getName());
	private static MaterieShell shell;
	private static MaterieView view;
	private static MateriiServiceAsync rpc;
	
	private StudentOverviewPlace place;

	public MaterieActivity(StudentOverviewPlace place) {
		this.place = place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if (rpc == null) {
			rpc = GWT.create(MateriiService.class);
		}
		
		if (view == null) {
			view = new MaterieViewImpl();
			shell = new MaterieShell();
			shell.panel.setWidget(view);
			shell.backlink.setText("<- innapoi la orar");
		}
		
		shell.backlink.setTargetHistoryToken("orar");
		view.setPresenter(this);
		
		rpc.getCourseDescription(place.getMaterieid(),new AppAsyncCallback<String>() {
			
			@Override
			public void onSuccess(String desc) {
				view.setDescription(desc);
			}
			
		});
		
		view.clearMateriale();
		rpc.getMateriale(place.getMaterieid(),
				new AppAsyncCallback<List<Resource>>() {
					@Override
					public void onSuccess(List<Resource> resources) {
						for (Resource r : resources) {
							view.addMaterial(r.description,"uploads/"+ r.resourceName);
						}
					}
		});
		rpc.getCourse(place.getMaterieid(), new AppAsyncCallback<Course>() {
			
			@Override
			public void onSuccess(Course arg0) {
				shell.setCaption(arg0.name);
			}
		});

		panel.setWidget(shell.asWidget());
	}

}
