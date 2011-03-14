package pcol.server.domain;
// Generated Mar 13, 2011 10:37:29 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Cursuri generated by hbm2java
 */
@Entity
@Table(name="cursuri"
    ,catalog="proj_col"
)
public class Cursuri  implements java.io.Serializable {


     private Integer id;
     private int version;
     private String nume;
     private Set<Curicule> curicules = new HashSet<Curicule>(0);
     private Set<ContracteStudiu> contracteStudius = new HashSet<ContracteStudiu>(0);

    public Cursuri() {
    }

	
    public Cursuri(String nume) {
        this.nume = nume;
    }
    public Cursuri(String nume, Set<Curicule> curicules, Set<ContracteStudiu> contracteStudius) {
       this.nume = nume;
       this.curicules = curicules;
       this.contracteStudius = contracteStudius;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    @Version
    @Column(name="version", nullable=false)
    public int getVersion() {
        return this.version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }
    
    @Column(name="nume", nullable=false, length=45)
    public String getNume() {
        return this.nume;
    }
    
    public void setNume(String nume) {
        this.nume = nume;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cursuri")
    public Set<Curicule> getCuricules() {
        return this.curicules;
    }
    
    public void setCuricules(Set<Curicule> curicules) {
        this.curicules = curicules;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cursuri")
    public Set<ContracteStudiu> getContracteStudius() {
        return this.contracteStudius;
    }
    
    public void setContracteStudius(Set<ContracteStudiu> contracteStudius) {
        this.contracteStudius = contracteStudius;
    }




}


