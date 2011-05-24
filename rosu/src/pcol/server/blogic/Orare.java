package pcol.server.blogic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import pcol.server.domain.CurCourse;
import pcol.server.domain.Orar;
import pcol.server.domain.Profesori;
import pcol.server.domain.Studenti;

//TODO: ar fi fost frumos ca student extends user ; prof extends user si 
// sa avem o metoda vistuala getschedule care sa fie impl specific de fiecare clasa
public class Orare {
	@SuppressWarnings("unchecked")
	public static Set<Orar> getSchedule(Studenti student, Session session) {
		Query selectOrarForContract = session.createQuery(
			"select orar "+
			"from  "+
			"Studenti as s "+ 
			"join s.contracteStudius as con "+
			"join con.curicul as cur "+
	
			"join s.orgGroups as gr "+
			"join gr.orars as orar "+
	
			"where  "+
			"orar.curCourse = cur.curCourse "+ 
			"and s.nrMatr=:nrmatr "+
			"and   con.id.contractVersion = "+ 
			"(select max(c.id.contractVersion) from ContracteStudiu as c "+
			"  where c.studenti.nrMatr=:nrmatr) ")
		.setParameter("nrmatr", student.getNrMatr());
		
		HashSet<Orar> ret = new HashSet<Orar>();
		ret.addAll((List<Orar>)selectOrarForContract.list());
		return ret;
	}
	
	public static  Set<Orar> getSchedule(Profesori prof, Session session) {
		HashSet<Orar> ret = new HashSet<Orar>();
		for(CurCourse curs:prof.getCurCourses()){
			ret.addAll(curs.getOrars());
		}
		return ret;
	}
}
