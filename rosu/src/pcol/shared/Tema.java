package pcol.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Tema implements IsSerializable {
	public Resource res;
	public int materieid;
	public Date deadline;
	public TemaStudent ts;
	public String description;
	
	public Tema(){
		
	}
	public Tema(int materieid, String description,  Date deadline,
			Resource res, TemaStudent ts) {
		
		this.materieid = materieid;
		this.description=description;
		this.deadline = deadline;
		this.res = res;
		this.ts=ts;
	}
}
