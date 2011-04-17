package pcol.client.teme;

import java.util.Date;

public class AssignmentOverview {
	public enum Status{
		PENDING_SUBMISSION,
		PENDING_REVIEW,
		DONE,
		MISSED
	}
	
	Status status = Status.PENDING_SUBMISSION;
	Date deadline;
	String name;
	String id;

	public AssignmentOverview(Status status, Date deadline, String name,
			String id) {
		this.status = status;
		this.deadline = deadline;
		this.name = name;
		this.id = id;
	}
}
