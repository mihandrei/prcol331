package pcol.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable, Serializable{
	public User(){
		
	}
	public User(String loginName, String name, int nrmat, String[] authActivities,String sid) {
		this.loginName = loginName;
		this.name = name;
		this.nrmat = nrmat;
		this.authActivities = authActivities;
		this.sid = sid;
	}

	private String loginName;
	private String name;
	private int nrmat;
	private String[] authActivities;
	private String sid;

	public String getLoginName(){
		return loginName;
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
