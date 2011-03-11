package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Course implements IsSerializable{

	public Course(){
		
	}
	
	public Course(String name, int credits, boolean inscris) {
		this.name=name;
		this.credits=credits;
		this.inscris=inscris;
	}
	public String name;
	public Integer credits;
	public Boolean inscris;

}
