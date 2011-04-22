package pcol.client.teme;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import pcol.client.AppLoader;
import pcol.client.teme.AssignmentOverview.Status;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TemeActivity extends AbstractActivity implements
		TemeView.Presenter {
	private static TemeView view;

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {

			@Override
			public void onSuccess() {
				if (view == null) {
					view = new TemeViewImpl();
				}
				view.setPresenter(TemeActivity.this);
				view.clear();
				List<AssignmentOverview> aos = Arrays.asList(
						new AssignmentOverview(Status.DONE, new Date(),
								"LAb 1", "bd/lab1"), new AssignmentOverview(
								Status.PENDING_SUBMISSION,
								new Date(2011, 6, 12), "LAb 4", "bd/lab4"));
				for (AssignmentOverview ao : aos) {
					view.addItem(ao.status.toString(), ao.name, ao.id,
							ao.deadline);
				}
				panel.setWidget(view);
				AppLoader.getApp().showTipFor("teme");
			}

			@Override
			public void onFailure(Throwable reason) {
				// TODO: centralize, offer to reload page
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

}