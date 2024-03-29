package pcol.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.client.materii.MateriiService;
import pcol.server.blogic.Contracte;
import pcol.server.domain.ContracteStudiu;
import pcol.server.domain.CurCourse;
import pcol.server.domain.Logins;
import pcol.server.domain.OrgGroup;
import pcol.server.domain.Profesori;
import pcol.server.domain.Studenti;
import pcol.server.domain.Teme;
import pcol.server.domain.TmStudent;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Course;
import pcol.shared.Group;
import pcol.shared.Resource;
import pcol.shared.Tema;
import pcol.shared.TemaStudent;
/**
 * FIXME: security: un profesor poate modifica doar cursurile lui, 
 * pune operatiile in scopul unui withuser, trimite sid-ul 
 */
@SuppressWarnings("serial")
public class MateriiServiceImpl extends AuthRemoteServiceServlet implements MateriiService {

	@Override
	public List<Course> getMateriiForProf(String sid) throws AuthenticationException {
		return withUser(sid, new UserCall<List<Course>>() {
			@Override
			public List<Course> call(Logins user, Session session) {
				session.beginTransaction();
				if (user.getProfesoris().size() != 1) {
					throw new RuntimeException("userul nu e profesor");
				}
				Profesori prof = user.getProfesoris().iterator().next();
				ArrayList<Course> ret = new ArrayList<Course>();
				for(CurCourse cur : prof.getCurCourses()){
					ret.add(new Course(cur.getId(), cur.getName(),cur.getAbbrev(),cur.getMsgChannel().getId(),-1));
				}
				session.getTransaction().commit();
				return ret;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGrupeForCourse(int courseid){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Query grq = session
			.createQuery("select gr from OrgGroup as gr , Curicul as c "+
					"where gr.an = c.an and " +
					"gr.orgSection.id = c.orgSection.id "+
					"and c.curCourse.id = :id")
			.setParameter("id", courseid);

			List<Group> ret = new ArrayList<Group>();
			for (OrgGroup g : (List<OrgGroup>) grq.list()) {
				ret.add(new Group(g.getId(), g.getNume()));
			}
			session.getTransaction().commit();
			return ret;
		} finally {
			session.close();
		}
	}

	@Override
	public Course getCourse(int courseid) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			CurCourse cur = (CurCourse) session.get(CurCourse.class,courseid);
			session.getTransaction().commit();
			return new Course(cur.getId(), cur.getName(),cur.getAbbrev(),cur.getMsgChannel().getId(),-1); 
		} finally {
			session.close();
		}
	}

	@Override
	public String getCourseDescription(int courseid) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			CurCourse cur = (CurCourse) session.get(CurCourse.class,courseid);
			session.getTransaction().commit();
			return cur.getDescription();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void updateCourseDescription(int courseid, String desc) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			CurCourse course = (CurCourse) session.get(CurCourse.class,courseid);
			course.setDescription(desc);
			session.persist(course);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Override
	public void addMaterial(int courseid, Resource res){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Query grq = session
			.createQuery("from Resource as r where r.numefisier=:r")
			.setParameter("r", res.resourceName);
			
			pcol.server.domain.Resource domres  =(pcol.server.domain.Resource) grq.uniqueResult();
			CurCourse curCourse = (CurCourse) session.get(CurCourse.class, courseid);

			domres.setDescriere(res.description);
			domres.setCurCourse(curCourse);
			
			session.persist(domres);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Resource> getMateriale(int courseid) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			CurCourse course = (CurCourse) session.get(CurCourse.class,courseid);
			List<Resource> ret = new ArrayList<Resource>();
			
			for(pcol.server.domain.Resource r :course.getResources()){
				ret.add(new Resource(r.getDescriere(), r.getNumefisier()));
			}
			
			session.getTransaction().commit();
			return ret;
		} finally {
			session.close();
		}
	}

	@Override
	public void removeMaterialByName(int materieid, String resourceName) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Query grq = session
			.createQuery("from Resource as r where r.numefisier=:r")
			.setParameter("r", resourceName);
			
			pcol.server.domain.Resource res  =(pcol.server.domain.Resource) grq.uniqueResult();			
			
			session.delete(res);
			session.getTransaction().commit();
			//resursa a fost scoasa din BD cu succes , kill za file
			FileUpload.deleteFile(resourceName,getServletContext());
		} finally {
			session.close();
		}
	}

	@Override
	public List<Tema> getTeme(int materieid) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			CurCourse course = (CurCourse) session.get(CurCourse.class,materieid);
			List<Tema> ret = new ArrayList<Tema>();
			
			for(Teme dt :course.getTemes()){
				pcol.server.domain.Resource domRes = dt.getResource();
				Resource r = new Resource(domRes.getDescriere(),domRes.getNumefisier());
				ret.add(new Tema(materieid,dt.getName(), dt.getDeadline(),r,null));
			}
			
			session.getTransaction().commit();
			return ret;
		} finally {
			session.close();
		}
	}

	
	@Override
	public void addTema(Tema t) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			CurCourse curCourse = (CurCourse) session.get(CurCourse.class, t.materieid);
			Query grq = session
			.createQuery("from Resource as r where r.numefisier=:r")
			.setParameter("r", t.res.resourceName);
			
			pcol.server.domain.Resource domres  =(pcol.server.domain.Resource) grq.uniqueResult();
			
			Teme domTema = new Teme(domres, curCourse,t.description);
			domTema.setDeadline(t.deadline);
			
			session.persist(domTema);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Override
	public void removeTema(int temaid) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Teme domt = (Teme) session.get(Teme.class,temaid);
			String numeFisier = domt.getResource().getNumefisier();
			session.delete(domt);
			session.getTransaction().commit();
			FileUpload.deleteFile(numeFisier,getServletContext());
		} finally {
			session.close();
		}
	}

	@Override
	public List<Tema> getTemeStudent(String sid, final int materieid) throws AuthenticationException {
		return withUser(sid, new UserCall<List<Tema>>() {
			@Override
			public List<Tema> call(Logins user, Session session) {
				session.beginTransaction();
				if (user.getStudentis().size() != 1) {
					throw new RuntimeException("userul nu e student");
				}
				Studenti student = user.getStudentis().iterator().next();
				
				List<Tema> ret = getTemeStudent(student, materieid, session);
				session.getTransaction().commit();
				return ret;
			}
		});
	}
	public List<Tema> getTemeStudent(Studenti student, int materieid,Session session) {
		Query temeMaterie = session
		.createQuery("from Teme as t where t.curCourse.id=:courseId")
		.setParameter("courseId", materieid);
		
		Query grq = session
		.createQuery(
				"from TmStudent as ts "+
				"where ts.studenti.nrMatr=:nrmatr "+
				"and ts.teme.id = :tid ")
		.setParameter("nrmatr", materieid);
		
		List<Tema> ret = new ArrayList<Tema>();

		for(Teme tm:(List<Teme>)temeMaterie.list()){
			grq.setParameter("tid", tm.getId());
			TemaStudent ts = null;
			
			TmStudent tmst = (TmStudent) grq.uniqueResult();
			if(tmst!=null){
				ts = new TemaStudent(tmst.getStatus(), tmst.getTeme().getDeadline(),
						null,null);
			}
			pcol.server.domain.Resource domRes = tm.getResource();
			Resource r = new Resource(domRes.getDescriere(),domRes.getNumefisier());
			ret.add(new Tema(materieid,tm.getName(),tm.getDeadline(),r,ts));
		}
		
		return ret;
	}
}
