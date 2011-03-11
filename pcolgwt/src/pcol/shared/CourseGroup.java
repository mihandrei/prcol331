package pcol.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * model gui pentru coursegroupwidget
 * @author miha
 */
public class CourseGroup implements IsSerializable {
	public List<Course> courses = new ArrayList<Course>();
	public boolean exclusive = false;
	public String name = "obligatorii";
}
