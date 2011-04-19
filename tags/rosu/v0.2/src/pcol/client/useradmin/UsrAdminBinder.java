package pcol.client.useradmin;

import org.hibernate.type.NumericBooleanType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

public class UsrAdminBinder extends Composite implements UsrAdminView{
	private Presenter p;
	private static UsrAdminBinderUiBinder uiBinder = GWT
			.create(UsrAdminBinderUiBinder.class);
	@UiField Button inmatriculare;
	@UiField TextBox txtnume;
	@UiField TextBox txtnrmatr;
	@UiField TextBox txtan;
	@UiField TextBox txtsectie;
	@UiField TextBox txtgrupa;
	@UiField TextBox txtcont;

	interface UsrAdminBinderUiBinder extends UiBinder<Widget, UsrAdminBinder> {
	}

	public UsrAdminBinder() {
		initWidget(uiBinder.createAndBindUi(this));
		inmatriculare.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				try{
					int nrmatr=Integer.parseInt(txtnrmatr.getText());
					int an=Integer.parseInt(txtan.getText());
					int sectie =Integer.parseInt(txtsectie.getText());
				
				p.saveUser(txtnume.getText(), nrmatr, an, sectie, txtgrupa.getText(), txtcont.getText());
				}
				catch(Exception e){
					
				}
				
			}
		});
	}

	@Override
	public void setPresenter(Presenter p) {
		this.p=p;
		
	}

}
