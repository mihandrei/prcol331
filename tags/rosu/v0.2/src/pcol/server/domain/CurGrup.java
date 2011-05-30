package pcol.server.domain;

// Generated Apr 17, 2011 10:03:09 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CurGrup generated by hbm2java
 */
@Entity
@Table(name = "cur_grup")
public class CurGrup implements java.io.Serializable {

	private int id;
	private boolean exclusiv;
	private int semester;
	private Set<OrgSection> orgSections = new HashSet<OrgSection>(0);
	private Set<CurGrupCours> curGrupCourses = new HashSet<CurGrupCours>(0);

	public CurGrup() {
	}

	public CurGrup(int id, boolean exclusiv, int semester) {
		this.id = id;
		this.exclusiv = exclusiv;
		this.semester = semester;
	}

	public CurGrup(int id, boolean exclusiv, int semester,
			Set<OrgSection> orgSections, Set<CurGrupCours> curGrupCourses) {
		this.id = id;
		this.exclusiv = exclusiv;
		this.semester = semester;
		this.orgSections = orgSections;
		this.curGrupCourses = curGrupCourses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "exclusiv", nullable = false)
	public boolean isExclusiv() {
		return this.exclusiv;
	}

	public void setExclusiv(boolean exclusiv) {
		this.exclusiv = exclusiv;
	}

	@Column(name = "semester", nullable = false)
	public int getSemester() {
		return this.semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "cur_curicul", joinColumns = { @JoinColumn(name = "groupid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "section", nullable = false, updatable = false) })
	public Set<OrgSection> getOrgSections() {
		return this.orgSections;
	}

	public void setOrgSections(Set<OrgSection> orgSections) {
		this.orgSections = orgSections;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curGrup")
	public Set<CurGrupCours> getCurGrupCourses() {
		return this.curGrupCourses;
	}

	public void setCurGrupCourses(Set<CurGrupCours> curGrupCourses) {
		this.curGrupCourses = curGrupCourses;
	}

}