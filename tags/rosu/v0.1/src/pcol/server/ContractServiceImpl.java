package pcol.server;

import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import pcol.client.contract.ContractService;
import pcol.server.domain.ContracteStudiu;
import pcol.server.domain.ContracteStudiuId;
import pcol.server.domain.CurGrup;
import pcol.server.domain.CurGrupCours;
import pcol.server.domain.Grade;
import pcol.server.domain.Studenti;
import pcol.server.domain.Users;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.ContractItem;
import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.Curicul;
import pcol.shared.Tuple;

public class ContractServiceImpl extends AuthRemoteServiceServlet implements
		ContractService {

	@Override
	public Curicul getCuricula(String sid) throws AuthenticationException {	
		return withUser(sid, new UserCall<Curicul>() {
			@Override
			public Curicul call(Users user, Session session) {
				//sau HQL: select ss.curGrups from Studenti as st join st.orgGroup.orgSection as ss
				//         where st.users.loginName=:login
				session.beginTransaction();
				if(user.getStudentis().size() != 1){
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				Curicul ret = new Curicul();
				for(CurGrup curicule: student.getOrgGroup().getOrgSection().getCurGrups()){
					//grupam dupa semestre
					int semestru = curicule.getSemester();
					if(!ret.cursuriPeSemestru.containsKey(semestru)){
						ret.cursuriPeSemestru.put(semestru, new ArrayList<CourseGroup>());
					}
					CourseGroup cg = new CourseGroup();				
					cg.exclusive = curicule.isExclusiv();
					cg.name = cg.exclusive?"optionale":"obligatorii"; 
					for(CurGrupCours grc: curicule.getCurGrupCourses()){
						cg.courses.add(new Course(grc.getCurCourse().getId(), 
								grc.getCurCourse().getName(),
								grc.getCurCourse().getAbbrev(),
								grc.getNcredits()));
					}
					ret.cursuriPeSemestru.get(semestru).add(cg);
				}
				session.getTransaction().commit();
				return ret;			
			}
		});
	}

	@Override
	public List<ContractItem> getContract(String sid) throws AuthenticationException{
		return withUser(sid,new UserCall<List<ContractItem>>() {
			@Override
			public List<ContractItem> call(Users user, Session session) {
				session.beginTransaction();
				List<ContractItem> ret = new ArrayList<ContractItem>();
				if(user.getStudentis().size() != 1){
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				for(ContracteStudiu contr: student.getContracteStudius()){
//					Query grq = session.createQuery("from Grade as g where g.nrmatr");
					Float nota = null;
					ContractItem ci = new ContractItem(contr.getId().getIdCurs(), nota);
					ret.add(ci);
				}
				session.getTransaction().commit();
				return ret;
			}
		});
	}
	@Override	
	public Tuple<Curicul, List<ContractItem>> getContractAndCuricul(String sid) throws AuthenticationException{
		return new Tuple<Curicul, List<ContractItem>>(getCuricula(sid),getContract(sid));
	}

	@Override
	public void submitContract(String sid, final Set<Integer> selectedCourseIds) throws AuthenticationException {
		withUser(sid,new UserCall<Void>() {
			@Override
			public Void call(Users user, Session session) {
				session.beginTransaction();
				if(user.getStudentis().size() != 1){
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				for(Integer idCurs : selectedCourseIds){
//					student.getContracteStudius().add(contractitem);
//					ContracteStudiuId csid = new ContracteStudiuId(student.getNrMatr(), idCurs);
//					new ContracteStudiu(csid, student,);
				}
				//;
				session.getTransaction().commit();
				return null;
			}
		});
		
	}

}
