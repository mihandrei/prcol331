package pcol.client.contract;

import java.util.Arrays;
import java.util.List;

import pcol.client.App;
import pcol.client.TweetService;
import pcol.client.TweetServiceAsync;
import pcol.client.contract.CourseGroupWidget.Presenter;
import pcol.client.security.AppAsyncCallback;
import pcol.shared.CourseGroup;
import pcol.shared.Curicul;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class ContractActivity extends AbstractActivity implements
		ContractView.Presenter {
	
	private static ContractView view;
	private static ContractServiceAsync rpc = null;
	
	private int credittotal;
	private boolean dirty;
	private Curicul curicul;

	public ContractActivity() {
	
	}

	private void addCgs(List<CourseGroup> cgs,String semid){
		for(CourseGroup courseGroup:cgs){
		
			credittotal += getSelectedCredits(courseGroup);	
			view.setCreditTotal(credittotal);
		
	        CourseGroupWidget cgw = new CourseGroupWidget();        
	        Presenter cgwPresenter  = new CourseGroupPresenter(cgw);
	        cgw.setPresenter(cgwPresenter);
	        cgwPresenter.setParentPresenter(this);
			cgwPresenter.setCourseGroup(courseGroup );
			cgwPresenter.start();
		
			view.addToCategory(semid, Arrays.asList(cgw));
		}
	}
	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		GWT.runAsync(new RunAsyncCallback() {
			private AsyncCallback<Curicul> getCuriculCallback = new AppAsyncCallback<Curicul>() {
				@Override
				public void onSuccess(Curicul c) {
					curicul = c;
					for(int sem : c.cursuriPeSemestru.keySet()){
						addCgs(c.cursuriPeSemestru.get(sem), "sem "+sem);
					}
					dirty = false;
					view.setSaveEnabled(dirty);
					App.getInstance().showInfo("tip: puteti sa va inscieti la cursuri din ani diferiti");
				}
			};
			
			@Override
			public void onSuccess() {
				if(view == null){
					view = new ContractViewImpl();
				}
				
				if(rpc == null){
					rpc = GWT.create(ContractService.class);
				}
				view.setPresenter(ContractActivity.this);
				view.clearAllCategories();
				credittotal = 0;
				panel.setWidget(view);
				rpc.getCuricula(App.getInstance().loginManager.getUser().getNrMatr(),
						getCuriculCallback);
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Window.alert(reason.getLocalizedMessage());
			}
		});
	}

	@Override
	public void onSave() {
		rpc.submitContract(curicul, new AppAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				App.getInstance().showInfo("contractul a fost salvat");
				dirty = false;
				view.setSaveEnabled(dirty);
			}
		});
	}

	private int getSelectedCredits(CourseGroup cg){
		int credits = 0;
		for(int i =0;i<cg.courses.size();i++){
			if(cg.courses.get(i).inscris){
				credits +=  cg.courses.get(i).credits;
			}
		}
		return credits;
	}
	
	public void onCgChanged(CourseGroup old, CourseGroup cg) {
		dirty = true;
		view.setSaveEnabled(dirty);
		credittotal += getSelectedCredits(cg);
		credittotal -= getSelectedCredits(old);
		view.setCreditTotal(credittotal);
	}

	public String mayStop() {
		if(dirty){
			return "discard your changes?";
		}
	    return null;
	}
}
