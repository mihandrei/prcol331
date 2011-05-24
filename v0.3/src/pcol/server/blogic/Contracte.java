package pcol.server.blogic;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import pcol.server.domain.ContracteStudiu;
import pcol.server.domain.ContracteStudiuId;
import pcol.server.domain.CurCourse;
import pcol.server.domain.Curicul;
import pcol.server.domain.OrgGroup;
import pcol.server.domain.OrgSection;
import pcol.server.domain.Studenti;

public class Contracte {
	public static final int MAX_STUDENTI_GRUPA = 3;
	
	public static Integer getLatestContractVersion(int nrmatr, Session session){
		Integer contractVersion = (Integer) session.createQuery(
						" select max(c.id.contractVersion) from ContracteStudiu as c"
					  + " where c.studenti.nrMatr=:nrmatr)")
				.setParameter("nrmatr", Integer.valueOf(nrmatr))
				.uniqueResult();
		return contractVersion;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ContracteStudiu> getContract(Studenti student, Session session){
		Query grq = session
				.createQuery(
						" from  ContracteStudiu as cs "
								+ " where cs.id.nrmat = :nrmatr "
								+ " and cs.id.contractVersion = ("
								+ " select max(c.id.contractVersion) from ContracteStudiu as c"
								+ " where c.studenti.nrMatr=:nrmatr)")
				.setParameter("nrmatr", student.getNrMatr());

		return (List<ContracteStudiu>) grq.list();
	}
	
	/**
	 * @return versiunea contractului
	 */
	public static int createNewContract(Studenti student, 
			Set<Integer> selectedCourseIds, Session session){
		
		Integer contractVersion = getLatestContractVersion(student.getNrMatr(), session);
		
		if(contractVersion == null){
			contractVersion=0;
		}

		for (Integer idCurs : selectedCourseIds) {
			//selectCuricul for given CursId
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
	
	@SuppressWarnings("unchecked")
	public static  void assignStudentToGroup(Studenti student, Integer contractVersion, Session session ){
		Query selectAniInContract = session.createQuery(
				" select distinct contr.curicul.an,  contr.curicul.orgSection.id "+
				" from ContracteStudiu as contr " +
				" where contr.studenti.nrMatr=:nrmatr " +
				" and contr.id.contractVersion=:contractVersion")
			.setParameter("nrmatr", student.getNrMatr())
			.setParameter("contractVersion", contractVersion);
		
		Query selectGrupaLibera = session.createQuery(
				"select gr from OrgGroup as gr " + 
				"join gr.studentis as s " +
				"where gr.an=:an and gr.orgSection.id=:sectieId "+ 
				"group by gr having count(s) <= :maxStudentiInGrupa")
			.setParameter("maxStudentiInGrupa", Long.valueOf(MAX_STUDENTI_GRUPA));
		
//		student.getOrgGroups().clear();
//		session.persist(student);
		
		for(Object[] anid : (List<Object[]>)selectAniInContract.list()){
			Integer an = (Integer) anid[0];
			Integer sectieId = (Integer) anid[1];
			
			selectGrupaLibera.setParameter("an",an)
				.setParameter("sectieId", sectieId);
			
			OrgGroup grupa = (OrgGroup) selectGrupaLibera.uniqueResult();
			
			if(grupa==null){
				OrgSection sectie = (OrgSection) session.get(OrgSection.class, sectieId);
				//todo: naming pt grupe
				grupa = new OrgGroup(sectie, sectie.getName() , an);
				session.persist(grupa);
			}
			
			student.getOrgGroups().add(grupa);
			session.persist(student);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Curicul> getCuricula(Studenti student,Session session){
		Query grq = session
		.createQuery(
				"select cur "+
				"from Curicul as cur , Studenti as s where "+ 
				"s.nrMatr=:nrmatr "+
				"and cur.orgSection = s.orgSection " +
				"and cur.semester = :sem ")
		.setParameter("nrmatr", student.getNrMatr())
		.setParameter("sem", CommonBusinessLogic.isFirstSemester()?1:2);
		return (List<Curicul>) grq.list();
	}
	
	public static void onContractSumbitted(Studenti student,Session session, final Set<Integer> selectedCourseIds){
		int contractVersion = createNewContract(student, selectedCourseIds, session);
		assignStudentToGroup(student,contractVersion,session);
		
		Mesaje.unSubscribeAll(student.getLogins(),session);
		for (Integer idCurs : selectedCourseIds) {
			CurCourse c = (CurCourse) session.get(CurCourse.class, idCurs);
			Mesaje.subscribe(student.getLogins(),c.getMsgChannel(),session);
		}
	}
}
