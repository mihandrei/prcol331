package pcol.server.domain;

// Generated Apr 17, 2011 10:03:09 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Teme generated by hbm2java
 */
@Entity
@Table(name = "teme")
public class Teme implements java.io.Serializable {

	private int id;
	private CurCourse curCourse;
	private int cerintePage;
	private Set<TmStudent> tmStudents = new HashSet<TmStudent>(0);

	public Teme() {
	}

	public Teme(int id, CurCourse curCourse, int cerintePage) {
		this.id = id;
		this.curCourse = curCourse;
		this.cerintePage = cerintePage;
	}

	public Teme(int id, CurCourse curCourse, int cerintePage,
			Set<TmStudent> tmStudents) {
		this.id = id;
		this.curCourse = curCourse;
		this.cerintePage = cerintePage;
		this.tmStudents = tmStudents;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curs", nullable = false)
	public CurCourse getCurCourse() {
		return this.curCourse;
	}

	public void setCurCourse(CurCourse curCourse) {
		this.curCourse = curCourse;
	}

	@Column(name = "cerinte_page", nullable = false)
	public int getCerintePage() {
		return this.cerintePage;
	}

	public void setCerintePage(int cerintePage) {
		this.cerintePage = cerintePage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teme")
	public Set<TmStudent> getTmStudents() {
		return this.tmStudents;
	}

	public void setTmStudents(Set<TmStudent> tmStudents) {
		this.tmStudents = tmStudents;
	}

}