package pcol.server.domain;

// Generated Apr 28, 2011 7:37:08 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StudentiGrupeId generated by hbm2java
 */
@Embeddable
public class StudentiGrupeId implements java.io.Serializable {

	private int nrmatr;
	private int groupid;

	public StudentiGrupeId() {
	}

	public StudentiGrupeId(int nrmatr, int groupid) {
		this.nrmatr = nrmatr;
		this.groupid = groupid;
	}

	@Column(name = "nrmatr", nullable = false)
	public int getNrmatr() {
		return this.nrmatr;
	}

	public void setNrmatr(int nrmatr) {
		this.nrmatr = nrmatr;
	}

	@Column(name = "groupid", nullable = false)
	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StudentiGrupeId))
			return false;
		StudentiGrupeId castOther = (StudentiGrupeId) other;

		return (this.getNrmatr() == castOther.getNrmatr())
				&& (this.getGroupid() == castOther.getGroupid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getNrmatr();
		result = 37 * result + this.getGroupid();
		return result;
	}

}