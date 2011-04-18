package model;


public class Tema_probleme {
	
	private int id_tema_probl;
	private int id_curs;
	private String descriere;
	private String nume_fisier;
	private int version;
	
	public Tema_probleme(){
		this.id_tema_probl=0;
		this.id_curs=0;
		this.descriere="";
		this.nume_fisier="";
		this.version=0;
	}
	
	public Tema_probleme(int id_tp, int id_c, String d, String n, int v){
		this.id_tema_probl=id_tp;
		this.id_curs=id_c;
		this.descriere=d;
		this.nume_fisier=n;
		this.version=v;
	}
	public int getId_tema_probl(){
		return this.id_tema_probl;
	}
	public int getId_curs(){
		return this.id_curs;
	}
	
	public String getDescriere(){
		return this.descriere;
	}
	public String getNume_fisier(){
		return this.nume_fisier;
	}
	public int getVersion(){
		return this.version;
	}
	public void setId_tema_probl(int i){
		this.id_tema_probl=i;
	}
	public void setId_curs(int i){
		this.id_curs=i;
	}
	
	public void setDescriere(String d){
		this.descriere=d;
	}
	
	public void setNume_fisier(String n){
		this.nume_fisier=n;
	}
	public void setVersion(int v){
		this.version=v;
	}
}

