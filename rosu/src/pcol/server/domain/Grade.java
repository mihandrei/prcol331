package pcol.server.domain;

// Generated Apr 17, 2011 9:35:43 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Grade generated by hbm2java
 */
@Entity
@Table(name = "grade", catalog = "proj_col", uniqueConstraints = @UniqueConstraint(columnNames = "data"))
public class Grade implements java.io.Serializable {

	private Integer id;
	private Studenti studenti;
	private CurCourse curCourse;
	private float nota;
	private Date data;

	public Grade() {
	}

	public Grade(Studenti studenti, CurCourse curCourse, float nota, Date data) {
		this.studenti = studenti;
		this.curCourse = curCourse;
		this.nota = nota;
		this.data = data;
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
	@JoinColumn(name = "nrmatr", nullable = false)
	public Studenti getStudenti() {
		return this.studenti;
	}

	public void setStudenti(Studenti studenti) {
		this.studenti = studenti;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curs", nullable = false)
	public CurCourse getCurCourse() {
		return this.curCourse;
	}

	public void setCurCourse(CurCourse curCourse) {
		this.curCourse = curCourse;
	}

	@Column(name = "nota", nullable = false, precision = 12, scale = 0)
	public float getNota() {
		return this.nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", unique = true, nullable = false, length = 19)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
