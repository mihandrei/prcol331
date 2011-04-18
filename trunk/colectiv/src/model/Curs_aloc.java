package model;


public class Curs_aloc {
	
	private int id_prof;
	private int id_curs;
	
	public Curs_aloc(){
		this.id_prof=0;
		this.id_curs=0;
	}
	
	public Curs_aloc (int id_p ,int id_c){
		this.id_prof=id_p;
		this.id_curs=id_c;
	}

	public int getId_prof(){
		return this.id_prof;
	}
	
	public int getId_curs(){
		return this.id_curs;
	}
	
	public void setId_prof(int i){
		this.id_prof=i;
	}
	
	public void setId_curs(int i){
		this.id_curs=i;
	}	

}

