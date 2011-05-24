package pcol.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Tema implements IsSerializable {
	public Resource res;
	public int materieid;
	public Date deadline;
	public Tema(){
		
	}
	public Tema(int materieid, Resource res,  Date deadline) {
		this.res = res;
		this.materieid = materieid;
		this.deadline = deadline;
	}
}
