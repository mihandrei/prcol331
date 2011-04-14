package pcol.server.domain;
// Generated Apr 15, 2011 12:35:22 AM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TmStudentId generated by hbm2java
 */
@Embeddable
public class TmStudentId  implements java.io.Serializable {


     private int tema;
     private int nrmatr;

    public TmStudentId() {
    }

    public TmStudentId(int tema, int nrmatr) {
       this.tema = tema;
       this.nrmatr = nrmatr;
    }
   

    @Column(name="tema", nullable=false)
    public int getTema() {
        return this.tema;
    }
    
    public void setTema(int tema) {
        this.tema = tema;
    }

    @Column(name="nrmatr", nullable=false)
    public int getNrmatr() {
        return this.nrmatr;
    }
    
    public void setNrmatr(int nrmatr) {
        this.nrmatr = nrmatr;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TmStudentId) ) return false;
		 TmStudentId castOther = ( TmStudentId ) other; 
         
		 return (this.getTema()==castOther.getTema())
 && (this.getNrmatr()==castOther.getNrmatr());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getTema();
         result = 37 * result + this.getNrmatr();
         return result;
   }   


}


