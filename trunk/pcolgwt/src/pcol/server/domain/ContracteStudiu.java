package pcol.server.domain;
// Generated Mar 13, 2011 10:37:29 AM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ContracteStudiu generated by hbm2java
 */
@Entity
@Table(name="contracte_studiu"
    ,catalog="proj_col"
)
public class ContracteStudiu  implements java.io.Serializable {


     private Integer nrmat;
     private Studenti studenti;
     private Cursuri cursuri;
     private String nota;

    public ContracteStudiu() {
    }

	
    public ContracteStudiu(Studenti studenti, Cursuri cursuri) {
        this.studenti = studenti;
        this.cursuri = cursuri;
    }
    public ContracteStudiu(Studenti studenti, Cursuri cursuri, String nota) {
       this.studenti = studenti;
       this.cursuri = cursuri;
       this.nota = nota;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="nrmat", unique=true, nullable=false)
    public Integer getNrmat() {
        return this.nrmat;
    }
    
    public void setNrmat(Integer nrmat) {
        this.nrmat = nrmat;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="nrmat", unique=true, nullable=false, insertable=false, updatable=false)
    public Studenti getStudenti() {
        return this.studenti;
    }
    
    public void setStudenti(Studenti studenti) {
        this.studenti = studenti;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_curs", nullable=false)
    public Cursuri getCursuri() {
        return this.cursuri;
    }
    
    public void setCursuri(Cursuri cursuri) {
        this.cursuri = cursuri;
    }
    
    @Column(name="nota", length=45)
    public String getNota() {
        return this.nota;
    }
    
    public void setNota(String nota) {
        this.nota = nota;
    }




}


