package pcol.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public final class Tweet implements IsSerializable{
	public enum Level{
		INFO,
		TASK,
		WARN,
		CRITICAL
	}
	
	private String text;
	private Date date;
	private String source;
	private Level lvl;

	public Tweet(){
		
	}
	public Tweet(String text,String source,Date date,Level lvl){
		this.date=date;
		this.source = source;
		this.lvl=lvl;
		this.text=text;
	}

	public String getText() {
		return text;
	}
	public Date getDate() {
		return date;
	}
	public String getSource() {
		return source;
	}
	public Level getLvl() {
		return lvl;
	}
	
}