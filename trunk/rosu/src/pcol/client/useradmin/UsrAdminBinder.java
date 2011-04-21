package pcol.client.useradmin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
/**
 * tine de Adeluta
 * Usradnimbinder e widgetul care se deseneaza pe ecran
 * foloseste *.ui.xml ca sa declare unele sub-componente
 */
public class UsrAdminBinder extends Composite implements UsrAdminView{
	private Presenter p;
	private static UsrAdminBinderUiBinder uiBinder = GWT
			.create(UsrAdminBinderUiBinder.class);
	@UiField Button Next;
	@UiField Button inmatriculeaza;
	@UiField TextBox txtnume;
	@UiField TextBox txtnrmatr;
	@UiField TextBox txtan;
	@UiField TextBox txtsectie;
	@UiField TextBox txtgrupa;
	@UiField Label errnrmatr;
	@UiField Label erran;
	@UiField Label errgrupa;
	@UiField Grid gridnext;
	@UiField Grid gridlogin;
	@UiField TextBox txtuser;
	@UiField TextBox txtparola;
	@UiField TextBox txtparola2;
	
	interface UsrAdminBinderUiBinder extends UiBinder<Widget, UsrAdminBinder> {
	}

	int nrmatr,an,grupa;

	public UsrAdminBinder() {
		initWidget(uiBinder.createAndBindUi(this));
		errnrmatr.setVisible(false);
		erran.setVisible(false);
		errgrupa.setVisible(false);
		gridlogin.setVisible(false);
		

		Next.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				try{
					nrmatr=Integer.parseInt(txtnrmatr.getText());
					errnrmatr.setVisible(false);
				}
				catch(NumberFormatException e){
					errnrmatr.setVisible(true);
					return;
				}
				
				try{
					an=Integer.parseInt(txtan.getText());
				}
				catch(NumberFormatException e){
					erran.setVisible(true);
					return;
				}
				
				try{
					grupa =Integer.parseInt(txtgrupa.getText());
				}
			    catch(NumberFormatException e){
			    	errgrupa.setVisible(true);
			    	return;
				}
			    
			    gridnext.setVisible(false);
			    gridlogin.setVisible(true);
			}
		});
		
		inmatriculeaza.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(txtparola.getText().equals(txtparola2.getText())){
//				p.saveUser(nume, nrmatr, an, sectie, grupa, cont)
					
				}else{
//					show error label
				}
			}
		});
	}

	@Override
	public void setPresenter(Presenter p) {
		this.p=p;
		
	}

}
