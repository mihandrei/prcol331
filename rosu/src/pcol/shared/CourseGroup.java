package pcol.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * model gui pentru coursegroupwidget
 * 
 * @author miha
 */
public class CourseGroup implements IsSerializable {
	public List<Course> courses = new ArrayList<Course>();

	public boolean exclusive;
	public String name ;
	public int an;
	public int semester;

	public CourseGroup copy() {
		CourseGroup ret = new CourseGroup();
		ret.exclusive = exclusive;
		ret.name = name;
		ret.courses = new ArrayList<Course>();
		for (Course c : courses) {
			ret.courses.add(c.copy());
		}
		return ret;
	}
}
