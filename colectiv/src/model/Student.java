package model;


public class Student {

	private int nr_mat;
	private int id_pers;
	private int id_grupa;
	private int version;
	
	public Student(){
		this.nr_mat=0;
		this.id_pers=0;
		this.id_grupa=0;
		this.version=0;
	}
	
	public Student(int nr, int id_p, int id_g, int v){
		this.nr_mat=nr;
		this.id_pers=id_p;
		this.id_grupa=id_g;
		this.version=v;
	}
	
	public int getNr_mat(){
		return this.nr_mat;
	}
	
	public int getId_pers(){
		return this.id_pers;
	}
	public int getId_grupa(){
		return this.id_grupa; 
	}
	public int getVersion(){
		return this.version;
	}
	
	public void setNr_mat(int nr){
		this.nr_mat=nr;
	}
	
	public void setId_pers(int i){
		this.id_pers=i;
	}
	public void setId_grupa(int i){
		this.id_grupa=i;
	}
	public void setVersion(int v){
		this.version=v;
	}
	
}

