package model;


public class Curicula {

	private int id_curs;
	private int id_sect;
	private int semestru;
	private int credite;
	private String tip;
	
	public Curicula(){
		this.id_curs=0;
		this.id_sect=0;
		this.semestru=0;
		this.credite=0;
		this.tip="";
	}
	
	public Curicula(int id_c ,int id_s, int s, int c, String t){
		this.id_curs=id_c;
		this.id_sect=id_s;
		this.semestru=s;
		this.credite=c;
		this.tip=t;
	}
	
	public int getId_curs(){
		return this.id_curs;
	}
	
	public int getId_sect(){
		return this.id_sect;
	}
	
	public int getSemestru(){
		return this.semestru;
	}
	
	public int getCredite(){
		return this.credite;
	}
	
	public String getTip(){
		return this.tip;
	}
	
	public void setId_curs(int i){
		this.id_curs=i;
	}
	
	public void setId_sect(int i){
		this.id_sect=i;
	}
	
	public void setSemestru(int s){
		this.semestru=s;
	}
	
	public void setCredite(int c){
		this.credite=c;
	}
	
	public void setTip(String t){
		this.tip=t;
	}
	
}
