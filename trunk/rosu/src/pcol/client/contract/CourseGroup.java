package pcol.client.contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * model gui pentru coursegroupwidget
 * 
 * @author miha
 */
public class CourseGroup implements IsSerializable {
	public List<Course> courses = new ArrayList<Course>();
	// TODO: cred ca ar fi mai bine de inlocuit flagul Course.inscris cu un
	// selectionmodel pe coursegroup
	// caci flagu pote zice exclusive si toate cursurile sa fie marcate ca
	// inscris = true
	public boolean exclusive = false;
	public String name = "obligatorii";

	static CourseGroup getMock1() {
		CourseGroup cg = new CourseGroup();
		cg.courses = Arrays.asList(
				new Course("MF 002", "Analiza complexa", 4,null, false),
				new Course("MF 022", "Algebra", 4, 3.5f, true),
				new Course("MF 042", "Geometrie afina", 6, 6.5f, false),
				new Course("MF 102", "Mecanica", 4, null, false),
				new Course("MF 302", "Structuri de date si algoritmi", 4, null, false));
		cg.exclusive = false;
		cg.name = "obligatorii";
		return cg;
	}

	static CourseGroup getMock2() {
		CourseGroup cg = new CourseGroup();
		cg.courses = Arrays.asList( 
				new Course("AC 002", "Branzeturi, metode industriale", 4, null, false),
				new Course("OZ 022", "Metode moderne de abordare a cartofului", 4, 3.5f, false),
				new Course("MF 102", "Razboaiele luminii", 4, null, true));
		cg.exclusive = true;
		cg.name = "optional 2";
		return cg;
	}
	
	static CourseGroup getMock3() {
		CourseGroup cg = new CourseGroup();
		cg.courses = Arrays.asList( 
				new Course("MF 002", "estetica", 4, null, false),
				new Course("FZ 022", "istoria artei", 4,null, false));
		cg.exclusive = true;
		cg.name = "optional 1";
		return cg;
	}
	
	static CourseGroup getMock4() {
		CourseGroup cg = new CourseGroup();
		cg.courses = Arrays.asList( 
				new Course("Stalk", "that is the most terrible place in the zone", 4, null, false),
				new Course("2000", "my mind is going", 4,null, false),
				new Course("MOON", "2 weeks 2 go buddy", 4,null, false));
		cg.exclusive = false;
		cg.name = "obligatorii";
		return cg;
	}
	
	static CourseGroup getMock5() {
		CourseGroup cg = new CourseGroup();
		cg.courses = Arrays.asList( 
				new Course("fF 002", "kin dza dza", 4, null, false),
				new Course("FZ 022", "despre gavitzapa", 4,null, false));
		cg.exclusive = true;
		cg.name = "optioonaal";
		return cg;
	}

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
