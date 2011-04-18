package model;


public class Nota {
	private int nr_mat;
	private int id_schema;
	private double valoare;
	
	public Nota(){
		this.nr_mat=0;
		this.id_schema=0;
		this.valoare=0.0;
	}
	
	public Nota(int nr ,int id, double v){
		this.nr_mat=nr;
		this.id_schema=id;
		this.valoare=v;
	}

	public int getNr_mat(){
		return this.nr_mat;
	}
	
	public int getId_schema(){
		return this.id_schema;
	}
	
	public double getValoare(){
		return this.valoare;
	}
	
	public void setNr_mat(int nr){
		this.nr_mat=nr;
	}
	
	public void setId_schema(int i){
		this.id_schema=i;
	}
	
	public void setValoare(double v){
		this.valoare=v;
	}
	
}

