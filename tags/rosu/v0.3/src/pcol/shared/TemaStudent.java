package pcol.shared;

import java.util.Date;

public class TemaStudent {
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

	public TemaStudent(Status status, Date deadline, String name,
			String id) {
		this.status = status;
		this.deadline = deadline;
		this.name = name;
		this.id = id;
	}
}
