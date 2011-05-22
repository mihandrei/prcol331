package pcol.server;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.dev.util.collect.HashMap;

import pcol.client.contract.ContractService;
import pcol.server.domain.ContracteStudiu;
import pcol.server.domain.ContracteStudiuId;
import pcol.server.domain.CurCourse;
import pcol.server.domain.Curicul;
import pcol.server.domain.Orar;
import pcol.server.domain.OrgGroup;
import pcol.server.domain.OrgSection;
import pcol.server.domain.Profesori;
import pcol.server.domain.Studenti;
import pcol.server.domain.Logins;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Contract;
import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.OrarDto;
import pcol.shared.Tuple;

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
				
				Query grq = session
				.createQuery(
						"select cur "+
						"from Curicul as cur , Studenti as s where "+ 
						"s.nrMatr=:nrmatr "+
						"and cur.orgSection = s.orgSection " +
						"and cur.semester = :sem ")
				.setParameter("nrmatr", student.getNrMatr())
				.setParameter("sem", CommonBusinessLogic.isFirstSemester()?1:2);
				
				//grupez in grupe de cursuri
				Map<Integer,CourseGroup> grouped = new HashMap<Integer,CourseGroup>();
				
				for (Curicul citem : (List<Curicul>) grq.list()) {
					Integer optGroup = citem.getOptionalGroup();
					if(!grouped.containsKey(optGroup)){
						CourseGroup cg = new CourseGroup();
						cg.exclusive = citem.getOptionalGroup()!= null;
						cg.name = cg.exclusive ? "optionale":"obligatorii";
						cg.an = citem.getAn();
						cg.semester = citem.getSemester();
						grouped.put(optGroup, cg);
					}
					
					grouped.get(optGroup).courses.add(new Course(
							citem.getCurCourse().getId(),
							citem.getCurCourse().getName(),
							citem.getCurCourse().getAbbrev(),
							citem.getNcredite()));
				}
				
				//grupez dupa an 
				pcol.shared.Curicul ret = new pcol.shared.Curicul();					
				for(Integer optGroup: grouped.keySet()){
					CourseGroup cg = grouped.get(optGroup);
					
					if (!ret.cursuriPeSemestru.containsKey(cg.an)) {
						ret.cursuriPeSemestru.put(cg.an, new ArrayList<CourseGroup>());
					}
					
					ret.cursuriPeSemestru.get(cg.an).add(cg);
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
				Query grq = session
						.createQuery(
								" from  ContracteStudiu as cs "
										+ " where cs.id.nrmat = :nrmatr "
										+ " and cs.id.contractVersion = ("
										+ " select max(c.id.contractVersion) from ContracteStudiu as c"
										+ " where c.studenti.nrMatr=:nrmatr)")
						.setParameter("nrmatr", student.getNrMatr());

				int ver = 0;
				for (ContracteStudiu contr : (List<ContracteStudiu>) grq.list()) {
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
				submitContract(student, selectedCourseIds, session);
				session.getTransaction().commit();
				return null;
			}
		});

	}
	/**
	 * @return versiunea contractului
	 */
	private int submitContract(Studenti student, 
			Set<Integer> selectedCourseIds, Session session){
		// latest version
		Integer contractVersion = (Integer) session.createQuery(
						" select max(c.id.contractVersion) from ContracteStudiu as c"
					  + " where c.studenti.nrMatr=:nrmatr)")
				.setParameter("nrmatr", student.getNrMatr())
				.uniqueResult();
		
		if(contractVersion == null){
			contractVersion=0;
		}

		for (Integer idCurs : selectedCourseIds) {
			Curicul curicul = (Curicul)session.createQuery(
					"select cur "+
					" from Curicul as cur , Studenti as s" +
					" where cur.curCourse.id=:cursid " +
					" and cur.orgSection = s.orgSection "+
					" and s.nrMatr=:nrmatr ")
			.setParameter("nrmatr", student.getNrMatr())
			.setParameter("cursid", idCurs)
			.uniqueResult();

			ContracteStudiu newci = new ContracteStudiu();
			newci.setId(new ContracteStudiuId(student.getNrMatr(),
					curicul.getId(), contractVersion + 1));
			session.save(newci);
			student.getContracteStudius().add(newci);
		}
		
		return contractVersion + 1;
	}
	
	private void assignStudentToGroup(Studenti student ){
		for(ContracteStudiu citem : student.getContracteStudius()){
			citem.getCuricul().getAn();
			student.getOrgSection();
		}
	}

	@Override
	// TODO: ar fi fost frumos ca student extends user ; prof extends user si 
	// sa avem o metoda vistuala getschedule care sa fie impl specific de fiecare clasa
	public List<OrarDto> getSchedule(String sid) throws AuthenticationException {
		return withUser(sid, new UserCall<List<OrarDto>>() {
			@Override
			public List<OrarDto> call(Logins user, Session session) {
				session.beginTransaction();
				Iterable<Orar> events;
				if (user.getStudentis().size() == 1) {
					Studenti student = user.getStudentis().iterator().next();
					events = getSchedule(student);
				}else if(user.getProfesoris().size() == 1){
					Profesori prof = user.getProfesoris().iterator().next();
					events = getSchedule(prof);
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

	protected Set<Orar> getSchedule(Profesori prof) {
		HashSet<Orar> ret = new HashSet<Orar>();
		for(CurCourse curs:prof.getCurCourses()){
			ret.addAll(curs.getOrars());
		}
		return ret;
	}

	protected Set<Orar> getSchedule(Studenti student) {
		HashSet<Orar> ret = new HashSet<Orar>();
		for(OrgGroup grup: student.getOrgGroups()){
			ret.addAll(grup.getOrars());
		}
		return ret;
	}

}
