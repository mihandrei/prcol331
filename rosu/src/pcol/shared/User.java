package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	public User(){
		
	}
	public User(String usr, String name, int nrmat, String[] authActivities,String sid) {
		this.usr = usr;
		this.name = name;
		this.nrmat = nrmat;
		this.authActivities = authActivities;
		this.sid = sid;
	}

	private String usr;
	private String name;
	private int nrmat;
	private String[] authActivities;
	private String sid;

	public String getUsr(){
		return usr;
	}
	
	public String getName() {
		return name;
	}

	public int getNrMatr() {
		return nrmat;
	}

	public String[] getAuthorizedActivities() {
		return authActivities;
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String id) {
		sid = id;
		
	}

}
