package pcol.client.contract;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Course implements IsSerializable{
	public String id;
	public Float nota;
	public String name;
	public Integer credits;
	public Boolean inscris;

	public Course(){
		
	}
	
	public Course(String id, String name, int credits, Float nota, boolean inscris) {
		this.id = id;
		this.name=name;
		this.credits=credits;
		this.nota = nota;
		this.inscris=inscris;
	}
	
	//clone se pare ca nu-i suporatat de gwt
	public Course copy() {
		return new Course(id,name,credits,nota,inscris);
	}
	
	
}
