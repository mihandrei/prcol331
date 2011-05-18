package pcol.client.materii;

import java.util.LinkedList;
import java.util.List;

import pcol.client.security.AppAsyncCallback;
import pcol.shared.Course;
import pcol.shared.Resource;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditTemeActivity extends AbstractActivity implements UploadView.Presenter{
	private static MaterieShell shell;
	private static UploadView view;
	private EditTemePlace place;
	private String lastUploadedResourceName;
	private static MateriiServiceAsync rpc;

	private LinkedList<String> resourceNames = new LinkedList<String>();
	
	public EditTemeActivity(EditTemePlace place) {
		this.place = place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if (view == null) {
			view = new EditMaterialeView(GWT.getModuleBaseURL() + "upload",
					"upl");
			shell = new MaterieShell();
			shell.panel.setWidget(view);
			shell.backlink.setText("<- innapoi la sumar");
		}
		shell.backlink.setTargetHistoryToken("materie/"
				+ place.getMaterieid());

		if (rpc == null) {
			rpc = GWT.create(MateriiService.class);
		}
		
		view.clear();
		view.waitForInputMode();
		
		rpc.getCourse(place.getMaterieid(), new AppAsyncCallback<Course>() {
			
			@Override
			public void onSuccess(Course arg0) {
				shell.setCaption(arg0.name);
			}
		});
		
		view.setPresenter(this);
		panel.setWidget(shell.asWidget());
	}

	public void onFileChange() {
		String fname = view.getFilePth();
		if (!fname.isEmpty()) {
			view.submit();	
			view.uploadingMode();
		}
	}

	public void onUploaded(String res) {
		if (res.startsWith("ok:")) {
			String r = res.substring(3);
			lastUploadedResourceName = r;
			view.readyToSaveMode(r);
		} else {
			Window.alert(res);
		}
	}

	public void onRemove(final int rowidx) {
		String resourceName = resourceNames.get(rowidx);
//		rpc.removeMaterial(place.getMaterieid(), resourceName  , new AppAsyncCallback<Void>() {
//			@Override
//			public void onSuccess(Void arg0) {
//				resourceNames.remove(rowidx);
//				view.removerow(rowidx);
//			}
//		});
	}

	public void onAdd() {
		if(view.getDescription().isEmpty()){
			view.descInvalid();
			return;
		}
		
//		rpc.addMaterial(place.getMaterieid(), view.getDescription(),
//				lastUploadedResourceName, new AppAsyncCallback<Void>() {
//					@Override
//					public void onSuccess(Void arg0) {
//						resourceNames.addLast(lastUploadedResourceName);
//						view.addRow(view.getDescription(),"uploads/"+ lastUploadedResourceName);
//						view.waitForInputMode();
//						lastUploadedResourceName="";
//					}
//		});

	}
}
