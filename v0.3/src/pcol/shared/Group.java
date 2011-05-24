package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Group implements IsSerializable{
	public int id;
	public String name;
	
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Group(){
		
	}
}
