package pcol.shared;

import java.util.Arrays;
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
		
		List<CourseGroup> li1 = Arrays.asList(CourseGroup.getMock1(),
				CourseGroup.getMock3(),CourseGroup.getMock2());
		List<CourseGroup> li2 = Arrays.asList(CourseGroup.getMock4(),
				CourseGroup.getMock5());
		ret.cursuriPeSemestru.put(2,li1 );
		ret.cursuriPeSemestru.put(4,li2 );
		return ret;
	}

}
