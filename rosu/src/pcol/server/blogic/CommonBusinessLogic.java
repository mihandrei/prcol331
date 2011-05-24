package pcol.server.blogic;

import java.util.Calendar;

public class CommonBusinessLogic {
	public static boolean isFirstSemester(){
		return Calendar.getInstance().get(Calendar.MONTH) < Calendar.MARCH;
	}
	
}
