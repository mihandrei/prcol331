package pcol.server.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="tweets"
   // ,catalog="proj_col"
)
public class Tweet implements java.io.Serializable {

	@Id @GeneratedValue(strategy=IDENTITY)
	@Column(name="id")
    private Long id;
    private String message;
     
    @Version
    @Column(name="version", nullable=false)
    private int version;

    public Tweet() {
    }
	
    public Tweet(String message) {
    	this.setMessage(message);
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public int getVersion() {
        return this.version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
    

}


