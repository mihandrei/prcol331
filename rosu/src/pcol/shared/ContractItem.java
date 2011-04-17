package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ContractItem implements IsSerializable{
	 public  int cursId;
     public  Float nota;

     public ContractItem(){
    	 
     }
     
     public ContractItem(int cursId, Float nota) {
    	 this.cursId = cursId;
    	 this.nota = nota;
     }
}
