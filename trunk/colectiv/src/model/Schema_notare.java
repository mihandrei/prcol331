package model;


public class Schema_notare {
	
	private int id_schema;
	private int id_curs;
	private String label;
	private int pondere;
	
	public Schema_notare(){
		this.id_schema=0;
		this.id_curs=0;
		this.label="";
		this.pondere=0;
	}
	
	public Schema_notare(int id_s, int id_c, String l, int p){
		this.id_schema=id_s;
		this.id_curs=id_c;
		this.label=l;
		this.pondere=p;
	}
	public int getId_schema(){
		return this.id_schema;
	}
	public int getId_curs(){
		return this.id_curs;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public int getPondere(){
		return this.pondere;
	}
	public void setId_schema(int i){
		this.id_schema=i;
	}
	public void setId_curs(int i){
		this.id_curs=i;
	}
	
	public void setLabel(String l){
		this.label=l;
	}
	
	public void setPondere(int p){
		this.pondere=p;
	}
	
}

