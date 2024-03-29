package pcol.server.domain;

// Generated May 24, 2011 2:57:40 PM by Hibernate Tools 3.4.0.CR1

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

/**
 * CurCourse generated by hbm2java
 */
@Entity
@Table(name = "cur_course", catalog = "proj_col")
public class CurCourse implements java.io.Serializable {

	private Integer id;
	private Profesori profesori;
	private MsgChannel msgChannel;
	private String name;
	private String abbrev;
	private String description;
	private Set<Resource> resources = new HashSet<Resource>(0);
	private Set<SchemaNotare> schemaNotares = new HashSet<SchemaNotare>(0);
	private Set<Teme> temes = new HashSet<Teme>(0);
	private Set<Curicul> curiculs = new HashSet<Curicul>(0);
	private Set<Orar> orars = new HashSet<Orar>(0);

	public CurCourse() {
	}

	public CurCourse(Profesori profesori, MsgChannel msgChannel, String name,
			String abbrev) {
		this.profesori = profesori;
		this.msgChannel = msgChannel;
		this.name = name;
		this.abbrev = abbrev;
	}

	public CurCourse(Profesori profesori, MsgChannel msgChannel, String name,
			String abbrev, String description, Set<Resource> resources,
			Set<SchemaNotare> schemaNotares, Set<Teme> temes,
			Set<Curicul> curiculs, Set<Orar> orars) {
		this.profesori = profesori;
		this.msgChannel = msgChannel;
		this.name = name;
		this.abbrev = abbrev;
		this.description = description;
		this.resources = resources;
		this.schemaNotares = schemaNotares;
		this.temes = temes;
		this.curiculs = curiculs;
		this.orars = orars;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profId", nullable = false)
	public Profesori getProfesori() {
		return this.profesori;
	}

	public void setProfesori(Profesori profesori) {
		this.profesori = profesori;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "msg_channel", nullable = false)
	public MsgChannel getMsgChannel() {
		return this.msgChannel;
	}

	public void setMsgChannel(MsgChannel msgChannel) {
		this.msgChannel = msgChannel;
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

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<Resource> getResources() {
		return this.resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<SchemaNotare> getSchemaNotares() {
		return this.schemaNotares;
	}

	public void setSchemaNotares(Set<SchemaNotare> schemaNotares) {
		this.schemaNotares = schemaNotares;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<Teme> getTemes() {
		return this.temes;
	}

	public void setTemes(Set<Teme> temes) {
		this.temes = temes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<Curicul> getCuriculs() {
		return this.curiculs;
	}

	public void setCuriculs(Set<Curicul> curiculs) {
		this.curiculs = curiculs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curCourse")
	public Set<Orar> getOrars() {
		return this.orars;
	}

	public void setOrars(Set<Orar> orars) {
		this.orars = orars;
	}

}
