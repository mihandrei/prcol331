package pcol.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TemaStudent implements IsSerializable{
	public enum Status{
		PENDING_SUBMISSION,
		PENDING_REVIEW,
		DONE,
		MISSED
	}
	
	public Status status = Status.PENDING_SUBMISSION;
	public Date deadline;
	public String name;
	public String id;
	public TemaStudent(){
		
	}
	public TemaStudent(String status, Date deadline, String name,
			String id) {
		this.status = Status.valueOf(status);
		this.deadline = deadline;
		this.name = name;
		this.id = id;
	}
}
