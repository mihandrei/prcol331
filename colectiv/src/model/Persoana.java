package model;

public class Persoana {
	private int id_pers;
	private String cnp;
	private String nume_pers;
	private String prenume_pers;
	private int version;
	public int getId_pers() {
		return id_pers;
	}
	public void setId_pers(int id_pers) {
		this.id_pers = id_pers;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getNume_pers() {
		return nume_pers;
	}
	public void setNume_pers(String nume_pers) {
		this.nume_pers = nume_pers;
	}
	public String getPrenume_pers() {
		return prenume_pers;
	}
	public void setPrenume_pers(String prenume_pers) {
		this.prenume_pers = prenume_pers;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
