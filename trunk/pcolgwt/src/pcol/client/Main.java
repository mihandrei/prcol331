package pcol.client;

import java.util.ArrayList;
import java.util.List;

import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.Curicul;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 *  Un widget maare care afiseaza contractul de studiu
 *
 */
public class Main extends Composite implements CgChangedListener {
	//standard uibind
	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	@UiField TabLayoutPanel tabpanel;
	@UiField Label total;
	@UiField Label plata;
	@UiField Button button;
	@UiField InlineLabel nrzile;

	interface MainUiBinder extends UiBinder<DockLayoutPanel, Main> {
	}
	
	//lista grupurilor de cursuri
	List<CourseGroupWidget> cgws = new ArrayList<CourseGroupWidget>();
	private ContractServiceAsync contractService;
	private Curicul curicul;//modelul randat
	
	//command obj pt serviciul de curicule
	class ContractCallback implements AsyncCallback<Curicul> {
			@Override
			public void onSuccess(Curicul result) {
				curicul=result;
				onModelChange(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				new ErrorDialog(caught.toString()).show();
			}
	}
	
	public Main(){
		initWidget(uiBinder.createAndBindUi(this));
		contractService = GWT.create(ContractService.class);
	}
	
	public void userchanged(int nrmatr){
		contractService.getCuricula(Integer.toString(nrmatr), new ContractCallback());
	}
	
	void onModelChange(Curicul model){
		tabpanel.clear();
		cgws.clear();
		for(int semestru:model.cursuriPeSemestru.keySet()){//pt fiecare semestru
			List<CourseGroup> cursuriSemestru = model.cursuriPeSemestru.get(semestru);
			VerticalPanel vpanel = new VerticalPanel();
			vpanel.setWidth("100%");
			tabpanel.add(vpanel,"semestrul "+semestru); //adaug un tab nou

			for(int i=0;i< cursuriSemestru .size();i++){//si pt fiecare cursgrup din sem
				CourseGroup cg = cursuriSemestru.get(i);
				CourseGroupWidget cgw = new CourseGroupWidget(); //creez un widget sa-l randeze
				vpanel.insert(cgw, i);
				cgw.onModelChange(cg);
				cgw.addModelChangedListener(this);  //vreau sa fiu notificat cand coursegroupwidgetul schimba modelul
				cgws.add(cgw);
			}
		}
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		//trimit serverului contractul
		contractService.submitContract(curicul, new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				new ErrorDialog("ok").show();
			}
			@Override
			public void onFailure(Throwable caught) {
				new ErrorDialog(caught.toString()).show();
			}
		});
	}

	@Override
	//daca coursegroupwidgetul o modificat modelul updatez totalul
	//TODO: senderul sa zica mai precis ce so schimbat ca asa iterez pana-n panzele albe
	public void onCgChanged(CourseGroupWidget sender) {
		Integer totalcr =0;
		for(CourseGroupWidget cgw:cgws){
			for (Course curs: cgw.cg.courses) {
				if(curs.inscris){
					totalcr+=curs.credits;
				}
			}
		}

		total.setText(totalcr.toString());
		int nplata = (totalcr-30)*50;
		if (nplata<0)nplata=0;
		plata.setText(Integer.toString(nplata));
	}
}
