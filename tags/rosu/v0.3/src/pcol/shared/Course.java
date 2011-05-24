package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Course implements IsSerializable{
	public int id;
	public String name;
	public Integer credits;
	public String shortname;
	public String msgChan;
	
	public Course(){
		
	}
	
	public Course(int id, String nume, String abreviere,String msgChan, int ncredite) {
		this.id = id;
		this.name=nume;
		this.shortname =abreviere;
		this.credits = ncredite;
		this.msgChan=msgChan;
	}

	//clone se pare ca nu-i suporatat de gwt
	public Course copy() {
		return new Course(id,name,shortname,msgChan,credits);
	}
	
	
}
