package model;


public class Anunt {
		
		private int id_anunt;
		private int id_curs;
		private String titlu;
		private String continut;
		
		public Anunt (){
			this.id_anunt=0;
			this.id_curs=0;
			this.titlu="";
			this.continut="";
		}
		
		public Anunt (int id_a ,int id_c, String t, String c){
			this.id_anunt=id_a;
			this.id_curs=id_c;
			this.titlu=t;
			this.continut=c;
		}
	
		public int getId_anunt(){
			return this.id_anunt;
		}
		
		public int getId_curs(){
			return this.id_curs;
		}
		
		public String getTitlu(){
			return this.titlu;
		}
		
		public String getContinut(){
			return this.continut;
		}
		
		public void setId_anunt(int i){
			this.id_anunt=i;
		}
		
		public void setId_curs(int i){
			this.id_curs=i;
		}
		
		public void setTitlu(String t){
			this.titlu=t;
		}
		
		public void setContinut(String c){
			this.continut=c;
		}
		
}

