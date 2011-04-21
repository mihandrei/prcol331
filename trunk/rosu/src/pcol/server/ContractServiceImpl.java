package pcol.server;

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
import pcol.server.domain.Orar;
import pcol.server.domain.Studenti;
import pcol.server.domain.Users;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Contract;
import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.Curicul;
import pcol.shared.OrarDto;
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
			}//lalalaa
		});
	}

	@Override
	public Contract getContract(String sid) throws AuthenticationException{
		return withUser(sid,new UserCall<Contract>() {
			@Override
			public Contract call(Users user, Session session) {
				session.beginTransaction();
				List<Integer> selection = new ArrayList<Integer>();
				if(user.getStudentis().size() != 1){
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				Query grq = session.createQuery(
						" from  ContracteStudiu as cs " +
						" where cs.id.nrmat = :nrmatr " +   
						" and cs.id.contractVersion = ("+
						" select max(c.id.contractVersion) from ContracteStudiu as c"+   
						" where c.studenti.nrMatr=:nrmatr)")
						.setParameter("nrmatr",student.getNrMatr());

				int ver = 0;
				for(ContracteStudiu contr:(List<ContracteStudiu>) grq.list()){
					selection.add(contr.getId().getIdCurs());
					ver = contr.getId().getContractVersion();
				}
				session.getTransaction().commit();
				
				Contract ret = new Contract();
				ret.selectedcourses = selection;
				ret.version = ver;
				return ret ;
			}
		});
	}
	@Override	
	public Tuple<Curicul, Contract> getContractAndCuricul(String sid) throws AuthenticationException{
		return new Tuple<Curicul,Contract>(getCuricula(sid),getContract(sid));
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
				
				//latest version
				Integer contractVersion = (Integer) session.createQuery(
						" select max(c.id.contractVersion) from ContracteStudiu as c"+   
						" where c.studenti.nrMatr=:nrmatr)")
						.setParameter("nrmatr",student.getNrMatr()).uniqueResult();
				 
				for(Integer idCurs : selectedCourseIds){
					ContracteStudiu newci = new ContracteStudiu();
					newci.setId(new ContracteStudiuId(student.getNrMatr(), idCurs, contractVersion+1));
					session.save(newci);
//					student.getContracteStudius().add(newci);
				}
				session.getTransaction().commit();
				return null;
			}
		});
		
	}
	
	@Override
	public List<OrarDto> getSchedule(String sid) throws AuthenticationException {
		return withUser(sid,new UserCall<List<OrarDto>>() {
			@Override
			public List<OrarDto> call(Users user, Session session) {
				session.beginTransaction();
				if(user.getStudentis().size() != 1){
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				
				List<OrarDto> ret = new ArrayList<OrarDto>();
				for(Orar orar : student.getOrgGroup().getOrars()){
					ret.add(new OrarDto(orar.getOrgGroup().getId(),
							orar.getCurCourse().getId(),
							orar.getCurCourse().getAbbrev(),
							orar.getTipActivitate(),
							orar.getZi(),
							orar.getSaptamana(),
							orar.getStartOra(),
							orar.getEndOra(),
							orar.getSala()));
				}
				session.getTransaction().commit();
				return ret;
			}
		});
		
	}

}
