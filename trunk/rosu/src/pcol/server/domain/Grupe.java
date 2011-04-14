package pcol.server.domain;
// Generated Apr 15, 2011 12:35:22 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grupe generated by hbm2java
 */
@Entity
@Table(name="grupe"
    ,catalog="proj_col"
)
public class Grupe  implements java.io.Serializable {


     private String id;
     private Sectii sectii;
     private Set<Studenti> studentis = new HashSet<Studenti>(0);
     private Set<Orar> orars = new HashSet<Orar>(0);

    public Grupe() {
    }

	
    public Grupe(String id, Sectii sectii) {
        this.id = id;
        this.sectii = sectii;
    }
    public Grupe(String id, Sectii sectii, Set<Studenti> studentis, Set<Orar> orars) {
       this.id = id;
       this.sectii = sectii;
       this.studentis = studentis;
       this.orars = orars;
    }
   
     @Id 
    
    @Column(name="id", unique=true, nullable=false, length=5)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sectie", nullable=false)
    public Sectii getSectii() {
        return this.sectii;
    }
    
    public void setSectii(Sectii sectii) {
        this.sectii = sectii;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grupe")
    public Set<Studenti> getStudentis() {
        return this.studentis;
    }
    
    public void setStudentis(Set<Studenti> studentis) {
        this.studentis = studentis;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grupe")
    public Set<Orar> getOrars() {
        return this.orars;
    }
    
    public void setOrars(Set<Orar> orars) {
        this.orars = orars;
    }




}

