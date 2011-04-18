package model;


public class Curs {

	private int id_curs;
	private String nume_curs;
	private int version;
	
	public Curs(){
		this.id_curs=0;
		this.nume_curs="";
		this.version=0;
	}
	
	public Curs(int id_c, String n, int v){
		this.id_curs=id_c;
		this.nume_curs=n;
		this.version=v;
	}
	
	public int getId_curs(){
		return this.id_curs;
	}
	
	public String getNume_curs(){
		return this.nume_curs;
	}
	
	public int getVersion(){
		return this.version;
	}
	
	public void setId_curs(int i){
		this.id_curs=i;
	}
	
	public void setNume_curs(String n){
		this.nume_curs=n;
	}
	
	public void setVersion(int v){
		this.version=v;
	}
	
}

