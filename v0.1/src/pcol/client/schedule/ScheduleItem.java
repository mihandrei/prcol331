package pcol.client.schedule;

public class ScheduleItem {
	public final String name;
	public final int starthour;
	public final int endhour;
	public final Day day;
	public final String type;
	public final String sala;
	public final Week week;
	
	public enum Week{
		EVEN,ODD,ALL
	}
	public enum Day{
		  MON, TUE, WED, THU, FRI, SAT, SUN
	}
	
	public ScheduleItem(int starthour, int endhour, Day day, 
			Week week, String name, String type, String sala) {
		this.starthour = starthour;
		this.endhour = endhour;
		this.day=day;
		this.week = week;
		
		this.name = name;
		this.type=type;
		this.sala=sala;
	}
}
