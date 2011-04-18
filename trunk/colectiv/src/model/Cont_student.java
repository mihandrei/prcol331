package model;


public class Cont_student {

	private int nr_mat;
	private String login_name;
	private String pass_hash;
	private String last_login;
	
	public Cont_student(){
		this.nr_mat=0;
		this.login_name="";
		this.pass_hash="";
		this.last_login="";
	}
	
	public Cont_student(int nr, String log, String pass, String ll){
		this.nr_mat=nr;
		this.login_name=log;
		this.pass_hash=pass;
		this.last_login=ll;
	}
	
	public int getNr_mat(){
		return this.nr_mat;
	}
	
	public String getLogin_name(){
		return this.login_name;
	}
	public String getPass_hash(){
		return this.pass_hash;
	}
	public String getLast_login(){
		return this.last_login;
	}
	
	public void setNr_mat(int nr){
		this.nr_mat=nr;
	}
	
	public void setLogin_name(String log){
		this.login_name=log;
	}
	public void setPass_hash(String pass){
		this.pass_hash=pass;
	}
	public void setLast_login(String ll){
		this.last_login=ll;
	}
	
}
