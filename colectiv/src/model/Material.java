package model;


public class Material {
		
		private int id_material;
		private int id_curs;
		private String nume_fisier;
		private String descriere;
		private int version;
		
		public Material (){
			this.id_material=0;
			this.id_curs=0;
			this.nume_fisier="";
			this.descriere="";
			this.version=0;
		}
		
		public Material (int id_m ,int id_c, String n, String d, int v){
			this.id_material=id_m;
			this.id_curs=id_c;
			this.nume_fisier=n;
			this.descriere=d;
			this.version=v;
		}
	
		public int getId_material(){
			return this.id_material;
		}
		
		public int getId_curs(){
			return this.id_curs;
		}
		
		public String getNume_fisier(){
			return this.nume_fisier;
		}
		
		public String getDescriere(){
			return this.descriere;
		}
		public int getVersion(){
			return this.version;
		}
		public void setId_material(int i){
			this.id_material=i;
		}
		
		public void setId_curs(int i){
			this.id_curs=i;
		}
		
		public void setNume_fisier(String n){
			this.nume_fisier=n;
		}
		
		public void setDescriere(String d){
			this.descriere=d;
		}
		public void setVersion(int v){
			this.version=v;
		}
}

