package pcol.server.domain;

// Generated Apr 28, 2011 8:02:06 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * MsgChannel generated by hbm2java
 */
@Entity
@Table(name = "msg_channel", catalog = "proj_col")
public class MsgChannel implements java.io.Serializable {

	private String id;
	private Set<Logins> loginses = new HashSet<Logins>(0);
	private Set<MsgMessage> msgMessages = new HashSet<MsgMessage>(0);

	public MsgChannel() {
	}

	public MsgChannel(String id) {
		this.id = id;
	}

	public MsgChannel(String id, Set<Logins> loginses,
			Set<MsgMessage> msgMessages) {
		this.id = id;
		this.loginses = loginses;
		this.msgMessages = msgMessages;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 45)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "msg_subscription", catalog = "proj_col", joinColumns = { @JoinColumn(name = "chanid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "login", nullable = false, updatable = false) })
	public Set<Logins> getLoginses() {
		return this.loginses;
	}

	public void setLoginses(Set<Logins> loginses) {
		this.loginses = loginses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "msg_message_channel", catalog = "proj_col", joinColumns = { @JoinColumn(name = "chanid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "msgid", nullable = false, updatable = false) })
	public Set<MsgMessage> getMsgMessages() {
		return this.msgMessages;
	}

	public void setMsgMessages(Set<MsgMessage> msgMessages) {
		this.msgMessages = msgMessages;
	}

}