package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Data transfer object
 * Scopul: sa fie trimis pe fir intre serer si client
 * Entitatile hibernate *NU* pot fi trimise pe fir
 * deci trebuiesc obiecte aproape asemanatoare doar pentru transfer
 */
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
