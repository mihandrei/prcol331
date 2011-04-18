package model;


public class Syllabus {
	
	private int id_curs;
	private String nume_fisier;
	private int version;
	
	public Syllabus(){
		this.id_curs=0;
		this.nume_fisier="";
		this.version=0;
	}
	
	public Syllabus(int id_c, String n, int v){
		this.id_curs=id_c;
		this.nume_fisier=n;
		this.version=v;
	}
	
	public int getId_curs(){
		return this.id_curs;
	}
	
	public String getNume_fisier(){
		return this.nume_fisier;
	}
	
	public int getVersion(){
		return this.version;
	}
	
	public void setId_curs(int i){
		this.id_curs=i;
	}
	
	public void setNume_fisier(String n){
		this.nume_fisier=n;
	}
	
	public void setVersion(int v){
		this.version=v;
	}
	
}

