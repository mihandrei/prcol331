package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OrarDto implements IsSerializable {
//	public enum Week{
//		EVEN,ODD,ALL
//	}
//	public enum Day{
//		  MON, TUE, WED, THU, FRI, SAT, SUN
//	}
	
	public Integer groupId;
	public Integer courseId;
	public String abbrev;
	public String activityType;
	public byte day;
	public byte week;
	public byte endHour;
	public byte startHour;
	public String room;
	
	public OrarDto(int groupId, Integer courseId, String abbrev,
			String activityType, byte day, byte week,byte starthour, byte endHour,
			 String room) {
		super();
		this.groupId = groupId;
		this.courseId = courseId;
		this.abbrev = abbrev;
		this.activityType = activityType;
		this.day = day;
		this.week = week;
		this.endHour = endHour;
		this.startHour = starthour;
		this.room = room;
	}

	public OrarDto(){
		
	}

}
