package pcol.client.materii;

import java.util.List;

import pcol.client.security.AppAsyncCallback;
import pcol.shared.Course;
import pcol.shared.Group;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Hyperlink;

public class ProfOverviewActivity extends AbstractActivity {
	private static ProfOverView view;
	private static MateriiServiceAsync rpc;

	private ProfOverviewPlace place;

	public ProfOverviewActivity(ProfOverviewPlace place) {
		this.place = place;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		if (view == null) {
			view = new ProfOverView();
		}
		if (rpc == null) {
			rpc = GWT.create(MateriiService.class);
		}

		view.setPresenter(this);
		view.setLinkMaterieId(place.getMaterieid());
		
		rpc.getCourse(place.getMaterieid(), new AppAsyncCallback<Course>() {
			
			@Override
			public void onSuccess(Course curs) {
				view.msgLink.setTargetHistoryToken("compune/"+curs.msgChan);
				view.setCaption(curs.name);
			}
		});
		rpc.getGrupeForCourse(place.getMaterieid(),
				new AppAsyncCallback<List<Group>>() {

					@Override
					public void onSuccess(List<Group> arg0) {
						view.temelinks.clear();
						view.notelinks.clear();
						for (Group gr : arg0) {
							view.temelinks.add(new Hyperlink(gr.name,
									"temegrupa/" + gr.id));
							view.notelinks.add(new Hyperlink(gr.name,
									"notegrupa/" + gr.id));
						}

					}

				});

		panel.setWidget(view.asWidget());
	}

}
