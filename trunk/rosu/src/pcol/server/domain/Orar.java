package pcol.server.domain;

// Generated May 15, 2011 7:09:00 PM by Hibernate Tools 3.4.0.CR1

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
 * Orar generated by hbm2java
 */
@Entity
@Table(name = "orar", catalog = "proj_col")
public class Orar implements java.io.Serializable {

	private Integer id;
	private CurCourse curCourse;
	private OrgGroup orgGroup;
	private String tipActivitate;
	private byte zi;
	private byte saptamana;
	private byte startOra;
	private byte endOra;
	private String sala;

	public Orar() {
	}

	public Orar(CurCourse curCourse, OrgGroup orgGroup, String tipActivitate,
			byte zi, byte saptamana, byte startOra, byte endOra, String sala) {
		this.curCourse = curCourse;
		this.orgGroup = orgGroup;
		this.tipActivitate = tipActivitate;
		this.zi = zi;
		this.saptamana = saptamana;
		this.startOra = startOra;
		this.endOra = endOra;
		this.sala = sala;
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
	@JoinColumn(name = "curs_id", nullable = false)
	public CurCourse getCurCourse() {
		return this.curCourse;
	}

	public void setCurCourse(CurCourse curCourse) {
		this.curCourse = curCourse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupa", nullable = false)
	public OrgGroup getOrgGroup() {
		return this.orgGroup;
	}

	public void setOrgGroup(OrgGroup orgGroup) {
		this.orgGroup = orgGroup;
	}

	@Column(name = "tip_activitate", nullable = false, length = 45)
	public String getTipActivitate() {
		return this.tipActivitate;
	}

	public void setTipActivitate(String tipActivitate) {
		this.tipActivitate = tipActivitate;
	}

	@Column(name = "zi", nullable = false)
	public byte getZi() {
		return this.zi;
	}

	public void setZi(byte zi) {
		this.zi = zi;
	}

	@Column(name = "saptamana", nullable = false)
	public byte getSaptamana() {
		return this.saptamana;
	}

	public void setSaptamana(byte saptamana) {
		this.saptamana = saptamana;
	}

	@Column(name = "start_ora", nullable = false)
	public byte getStartOra() {
		return this.startOra;
	}

	public void setStartOra(byte startOra) {
		this.startOra = startOra;
	}

	@Column(name = "end_ora", nullable = false)
	public byte getEndOra() {
		return this.endOra;
	}

	public void setEndOra(byte endOra) {
		this.endOra = endOra;
	}

	@Column(name = "sala", nullable = false, length = 45)
	public String getSala() {
		return this.sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

}
