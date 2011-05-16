package pcol.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.client.materii.MateriiService;
import pcol.server.domain.CurCourse;
import pcol.server.domain.Logins;
import pcol.server.domain.OrgGroup;
import pcol.server.domain.Profesori;
import pcol.server.security.AuthRemoteServiceServlet;
import pcol.shared.AuthenticationException;
import pcol.shared.Course;
import pcol.shared.Group;
import pcol.shared.Resource;

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
					ret.add(new Course(cur.getId(), cur.getName(),cur.getAbbrev(),-1));
				}
				return ret;
			}
			});
	}
	
	@Override
	public List<Group> getGrupeForCourse(int courseid){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			
			Query grq = session
			.createQuery("select g from OrgGroup as g "+
						 "inner join g.orgSection.curiculs as c "+ 
						 "where c.curCourse.id = :id ")
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
			return new Course(cur.getId(), cur.getName(),cur.getAbbrev(),-1); 
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
	public void addMaterial(int courseid, String name, int resourceid) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			//TODO
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
			
			session.persist(course);
			session.getTransaction().commit();
			return ret;
		} finally {
			session.close();
		}
	}

}
