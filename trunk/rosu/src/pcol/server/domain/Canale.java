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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Canale generated by hbm2java
 */
@Entity
@Table(name="canale"
    ,catalog="proj_col"
)
public class Canale  implements java.io.Serializable {


     private String id;
     private Set<Users> userses = new HashSet<Users>(0);
     private Set<Mesaje> mesajes = new HashSet<Mesaje>(0);

    public Canale() {
    }

	
    public Canale(String id) {
        this.id = id;
    }
    public Canale(String id, Set<Users> userses, Set<Mesaje> mesajes) {
       this.id = id;
       this.userses = userses;
       this.mesajes = mesajes;
    }
   
     @Id 
    
    @Column(name="id", unique=true, nullable=false, length=45)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="subscriptii", catalog="proj_col", joinColumns = { 
        @JoinColumn(name="id_canal", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="login", nullable=false, updatable=false) })
    public Set<Users> getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set<Users> userses) {
        this.userses = userses;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="msg_chan", catalog="proj_col", joinColumns = { 
        @JoinColumn(name="chanid", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="msgid", nullable=false, updatable=false) })
    public Set<Mesaje> getMesajes() {
        return this.mesajes;
    }
    
    public void setMesajes(Set<Mesaje> mesajes) {
        this.mesajes = mesajes;
    }




}

