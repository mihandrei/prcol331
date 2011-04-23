package pcol.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable, Serializable{
	private Role role;
	private String loginName;
	private String name;
	private int nrmat;
	private String sid;

	public enum Role{
		STUDENT,
		ADMIN,
		PROFESOR
	}
	
	public User(){
		
	}

	public User(String loginName, String name, int nrmat, Role role,String sid) {
		this.loginName = loginName;
		this.name = name;
		this.nrmat = nrmat;
		this.role=role;
		this.sid = sid;
	}


	public String getLoginName(){
		return loginName;
	}
	
	public String getName() {
		return name;
	}

	public int getNrMatr() {
		return nrmat;
	}

	public Role getRole() {
		return role;
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String id) {
		sid = id;
		
	}

}
