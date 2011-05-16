package pcol.client.materii;

import java.util.List;

import pcol.client.security.AppAsyncCallback;
import pcol.shared.Resource;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditMaterialeActivity extends AbstractActivity {
	private static MaterieShell shell;
	private static EditMaterialeView view;
	private EditMaterialePlace place;
	private int lastUploadedResourceid;
	private static MateriiServiceAsync rpc;

	public EditMaterialeActivity(EditMaterialePlace place) {
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
			shell.backlink.setTargetHistoryToken("materie/"
					+ place.getMaterieid());
		}

		if (rpc == null) {
			rpc = GWT.create(MateriiService.class);
		}
		view.clear();
		rpc.getMateriale(place.getMaterieid(),
				new AppAsyncCallback<List<Resource>>() {
					@Override
					public void onSuccess(List<Resource> resources) {
						for (Resource r : resources) {
							view.addRow(r.name, r.resourceUrl);
						}
					}
				});

		view.setPresenter(this);
		panel.setWidget(shell.asWidget());

	}

	void onFileChange() {
		String fname = view.getFilePth();
		if (!fname.isEmpty()) {
			view.submit();
		}
	}

	public void onUploaded(String res) {
		if (res.startsWith("ok:")) {
			String r = res.substring(3);
			view.hideForm(r);
		} else {
			Window.alert(res);
		}
	}

	public void onRemove(int rowidx) {
//		rpc.removeMaterial(place.getMaterieid(),resid)
		view.removerow(rowidx);
		view.resetForm();
	}

	public void onAdd() {
		rpc.addMaterial(place.getMaterieid(), view.getDescription(),
				lastUploadedResourceid, new AppAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void arg0) {
						view.addRow(view.getDescription(), view.getFilePth());
					}
		});

	}
}
