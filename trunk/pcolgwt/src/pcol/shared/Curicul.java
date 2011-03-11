package pcol.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;



public class Curicul implements IsSerializable{
	public Map<Integer, List<CourseGroup>> cursuriPeSemestru = new HashMap<Integer, List<CourseGroup>>();

	static String[] discipline = new String[] { "Programare orientata obiect",
		"Structuri de date si algoritmi", "Sisteme de operare",
		"Tehnici de optimizare", "Ingineria sistemelor soft",
		"Inteligenta artificiala", "Retele de calculatoare",
		"Prooiect colectiv", "Statistica matematica", "Analiza Complexa" , 
		"Teoria masurii","electrodinamica","mecanica analitica","haos determinist",
		"spatii metrice","Termodinamica","Fizica atomului","Temeraturi ultrajoase"};

	static int[] credite = new int[] { 6, 4, 5, 4, 6, 6, 6, 6, 3 ,5,4 ,3,5,4,3,4,5,4,6};
	
	public static Curicul mock1() {
		Curicul ret = new Curicul();
		
		List<CourseGroup> li1 = new ArrayList<CourseGroup>();
		li1.add(mockCourseGroup(false,0,3));
		li1.add(mockCourseGroup(true,3,5));
		li1.add(mockCourseGroup(true,5,8));
		
		List<CourseGroup> li2 = new ArrayList<CourseGroup>();
		li2.add(mockCourseGroup(false,8,12));
		li2.add(mockCourseGroup(true,12,14));
		
		ret.cursuriPeSemestru.put(2,li1 );
		ret.cursuriPeSemestru.put(4,li2 );
		return ret;
	}


	private static CourseGroup mockCourseGroup(boolean opt, int s, int e) {
		CourseGroup mock = new CourseGroup();
		for (int i = s; i < e; i++) {
			mock.courses.add(new Course(discipline[i], credite[i], false));
		}
		mock.exclusive = opt;
		if(opt){
			mock.name="opttional";
		}else{
			mock.name="obligatorii";
		}
		return mock;
	}

}
