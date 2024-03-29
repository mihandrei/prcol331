package pcol.client.materii;

import pcol.client.AppLoader;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.Course;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditDescriereActivity extends AbstractActivity{
	private static MaterieShell shell;
	private static EditDescriereView view;
	private static MateriiServiceAsync rpc;

	private EditDescrierePlace place;

	public EditDescriereActivity(EditDescrierePlace place) {
		this.place=place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if (view == null) {
			view = new EditDescriereView();
			shell = new MaterieShell();
			shell.panel.setWidget(view);
			shell.backlink.setText("<- innapoi la sumar");
		}
		
		if(rpc == null){
			rpc = GWT.create(MateriiService.class);
		}

		shell.backlink.setTargetHistoryToken("materie/"+place.getMaterieid());
		view.setPresenter(this);
		view.setDescription("");
		view.previewMode();

		rpc.getCourse(place.getMaterieid(), new AppAsyncCallback<Course>() {
			
			@Override
			public void onSuccess(Course arg0) {
				shell.setCaption(arg0.name);
			}
		});
		
		rpc.getCourseDescription(place.getMaterieid(),new AppAsyncCallback<String>() {
			
			@Override
			public void onSuccess(String desc) {
				view.setDescription(desc);
			}
			
		});
		
		
		panel.setWidget(shell.asWidget());
	}
	
	void saveDescription(String desc){
		rpc.updateCourseDescription(place.getMaterieid(), desc, new AppAsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void arg0) {
				AppLoader.getApp().showInfo("salvat");
				History.newItem("materie/"+place.getMaterieid());
			}
		});
	}

}
