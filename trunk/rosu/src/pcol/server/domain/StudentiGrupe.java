package pcol.server.domain;

// Generated Apr 28, 2011 7:37:08 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * StudentiGrupe generated by hbm2java
 */
@Entity
@Table(name = "studenti_grupe", catalog = "proj_col")
public class StudentiGrupe implements java.io.Serializable {

	private StudentiGrupeId id;
	private OrgGroup orgGroup;

	public StudentiGrupe() {
	}

	public StudentiGrupe(StudentiGrupeId id, OrgGroup orgGroup) {
		this.id = id;
		this.orgGroup = orgGroup;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "nrmatr", column = @Column(name = "nrmatr", nullable = false)),
			@AttributeOverride(name = "groupid", column = @Column(name = "groupid", nullable = false)) })
	public StudentiGrupeId getId() {
		return this.id;
	}

	public void setId(StudentiGrupeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupid", nullable = false, insertable = false, updatable = false)
	public OrgGroup getOrgGroup() {
		return this.orgGroup;
	}

	public void setOrgGroup(OrgGroup orgGroup) {
		this.orgGroup = orgGroup;
	}

}