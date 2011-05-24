package pcol.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import pcol.client.contract.ContractService;
import pcol.server.blogic.Contracte;
import pcol.server.blogic.Orare;
import pcol.server.domain.ContracteStudiu;
import pcol.server.domain.CurCourse;
import pcol.server.domain.Curicul;
import pcol.server.domain.Logins;
import pcol.server.domain.Orar;
import pcol.server.domain.Profesori;
import pcol.server.domain.Studenti;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Contract;
import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.OrarDto;
import pcol.shared.Tuple;

@SuppressWarnings("serial")
public class ContractServiceImpl extends AuthRemoteServiceServlet implements
		ContractService {

	@Override
	public pcol.shared.Curicul getCuricula(String sid) throws AuthenticationException {
		return withUser(sid, new UserCall<pcol.shared.Curicul>() {
			@Override
			public pcol.shared.Curicul call(Logins user, Session session) {
				session.beginTransaction();
				if (user.getStudentis().size() != 1) {
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				
				//key1: anul; key2 : id-ul grupei de curs ; valoare: grupa de curs
				//al doilea e map si nu list ca sa pot sa retrieve un elem in O(1)
				Map<Integer, Map<Integer,CourseGroup>> anGrupCursuri = new HashMap<Integer, Map<Integer,CourseGroup>>();
				
				for (Curicul citem : Contracte.getCuricula(student, session)) {
					//ensure an
					if (!anGrupCursuri.containsKey(citem.getAn())) {
						anGrupCursuri.put(citem.getAn(), new HashMap<Integer,CourseGroup>());
					}
					
					//grupurile de cursuri pt un an dat
					Map<Integer, CourseGroup> grupCursuri = anGrupCursuri.get(citem.getAn());
					
					Integer optGroup = citem.getOptionalGroup();
					//ensure coursegroup
					if(!grupCursuri.containsKey(optGroup)){
						CourseGroup cg = new CourseGroup();
						cg.exclusive = citem.getOptionalGroup()!= null;
						cg.name = cg.exclusive ? "optionale":"obligatorii";
						cg.an = citem.getAn();
						cg.semester = citem.getSemester();
						grupCursuri.put(optGroup, cg);
					}
					CurCourse curs = citem.getCurCourse();
					grupCursuri.get(optGroup).courses.add(new Course(
							curs.getId(),
							curs.getName(),
							curs.getAbbrev(),
							curs.getMsgChannel().getId(),
							citem.getNcredite()));
				}
				
				//flatten the map
				pcol.shared.Curicul ret = new pcol.shared.Curicul();					
				for(Integer an: anGrupCursuri.keySet()){
					ArrayList<CourseGroup> flattened = new ArrayList<CourseGroup>(anGrupCursuri.get(an).values());
					ret.cursuriPeAn.put(an,flattened);
				}
				session.getTransaction().commit();
				return ret;
			}
		});
	}

	@Override
	public Contract getContract(String sid) throws AuthenticationException {
		return withUser(sid, new UserCall<Contract>() {
			@Override
			public Contract call(Logins user, Session session) {
				session.beginTransaction();
				List<Integer> selection = new ArrayList<Integer>();
				if (user.getStudentis().size() != 1) {
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();

				int ver = 0;
				for (ContracteStudiu contr : Contracte.getContract(student, session)) {
					selection.add(contr.getCuricul().getCurCourse().getId());
					ver = contr.getId().getContractVersion();
				}
				session.getTransaction().commit();

				Contract ret = new Contract();
				ret.selectedcourses = selection;
				ret.version = ver;
				return ret;
			}
		});
	}

	@Override
	public Tuple<pcol.shared.Curicul, Contract> getContractAndCuricul(String sid)
			throws AuthenticationException {
		return new Tuple<pcol.shared.Curicul, Contract>(getCuricula(sid), getContract(sid));
	}

	@Override
	public void submitContract(String sid, final Set<Integer> selectedCourseIds)
			throws AuthenticationException {
		withUser(sid, new UserCall<Void>() {
			@Override
			public Void call(Logins user, Session session) {
				session.beginTransaction();
				if (user.getStudentis().size() != 1) {
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				Contracte.onContractSumbitted(student, session, selectedCourseIds);
				session.getTransaction().commit();
				return null;
			}
		});
	}
	
	@Override
	
	public List<OrarDto> getSchedule(String sid) throws AuthenticationException {
		return withUser(sid, new UserCall<List<OrarDto>>() {
			@Override
			public List<OrarDto> call(Logins user, Session session) {
				session.beginTransaction();
				Iterable<Orar> events;
				if (user.getStudentis().size() == 1) {
					Studenti student = user.getStudentis().iterator().next();
					events = Orare.getSchedule(student,session);
				}else if(user.getProfesoris().size() == 1){
					Profesori prof = user.getProfesoris().iterator().next();
					events = Orare.getSchedule(prof,session);
				}else{
					throw new RuntimeException("userul nu e student sau profesor");
				}

				List<OrarDto> ret = new ArrayList<OrarDto>();

				for (Orar orar : events) {
					ret.add(new OrarDto(orar.getOrgGroup().getId(), orar
							.getCurCourse().getId(), orar.getCurCourse()
							.getAbbrev(), orar.getTipActivitate(), orar
							.getZi(), orar.getSaptamana(), orar
							.getStartOra(), orar.getEndOra(), orar
							.getSala()));
				}
				session.getTransaction().commit();
				return ret;
			}
		});
	}

}
