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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * CurCourse generated by hbm2java
 */
@Entity
@Table(name = "cur_course", catalog = "proj_col")
public class CurCourse implements java.io.Serializable {

	private Integer id;
	private int version;
	private Resource resource;
	private String name;
	private String abbrev;
	private Set<ContracteStudiu> contracteStudius = new HashSet<ContracteStudiu>(
			0);
	private Set<Teme> temes = new HashSet<Teme>(0);
	private Set<Orar> orars = new HashSet<Orar>(0);
	private Set<CurGrupCours> curGrupCourses = new HashSet<CurGrupCours>(0);

	public CurCourse() {
	}

	public CurCourse(String name, String abbrev) {
		this.name = name;
		this.abbrev = abbrev;
	}

	public CurCourse(Resource resource, String name, String abbrev,
			Set<ContracteStudiu> contracteStudius, Set<Teme> temes,
			Set<Orar> orars, Set<CurGrupCours> curGrupCourses) {
		this.resource = resource;
		this.name = name;
		this.abbrev = abbrev;
		this.contracteStudius = contracteStudius;
		this.temes = temes;
		this.orars = orars;
		this.curGrupCourses = curGrupCourses;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_page")
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "abbrev", nullable = false, length = 12)
	public String getAbbrev() {
		return this.abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<ContracteStudiu> getContracteStudius() {
		return this.contracteStudius;
	}

	public void setContracteStudius(Set<ContracteStudiu> contracteStudius) {
		this.contracteStudius = contracteStudius;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<Teme> getTemes() {
		return this.temes;
	}

	public void setTemes(Set<Teme> temes) {
		this.temes = temes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<Orar> getOrars() {
		return this.orars;
	}

	public void setOrars(Set<Orar> orars) {
		this.orars = orars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<CurGrupCours> getCurGrupCourses() {
		return this.curGrupCourses;
	}

	public void setCurGrupCourses(Set<CurGrupCours> curGrupCourses) {
		this.curGrupCourses = curGrupCourses;
	}

}
