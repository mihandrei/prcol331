package pcol.server.domain;

// Generated Apr 17, 2011 10:03:09 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Persoane generated by hbm2java
 */
@Entity
@Table(name = "persoane", uniqueConstraints = {
		@UniqueConstraint(columnNames = "pasaport"),
		@UniqueConstraint(columnNames = "cnp") })
public class Persoane implements java.io.Serializable {

	private Integer id;
	private int version;
	private String cnp;
	private String pasaport;
	private String prenume;
	private String nume;
	private Set<Users> userses = new HashSet<Users>(0);

	public Persoane() {
	}

	public Persoane(String nume) {
		this.nume = nume;
	}

	public Persoane(String cnp, String pasaport, String prenume, String nume,
			Set<Users> userses) {
		this.cnp = cnp;
		this.pasaport = pasaport;
		this.prenume = prenume;
		this.nume = nume;
		this.userses = userses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version
	@Column(name = "version", nullable = false)
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "cnp", unique = true, length = 13)
	public String getCnp() {
		return this.cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	@Column(name = "pasaport", unique = true, length = 45)
	public String getPasaport() {
		return this.pasaport;
	}

	public void setPasaport(String pasaport) {
		this.pasaport = pasaport;
	}

	@Column(name = "prenume", length = 45)
	public String getPrenume() {
		return this.prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	@Column(name = "nume", nullable = false, length = 45)
	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "persoane")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}
