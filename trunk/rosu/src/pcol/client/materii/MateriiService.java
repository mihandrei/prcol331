package pcol.client.materii;

import java.util.List;

import pcol.shared.AuthenticationException;
import pcol.shared.Course;
import pcol.shared.Group;
import pcol.shared.Resource;
import pcol.shared.Tema;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/materii")
public interface MateriiService extends RemoteService{
	List<Course> getMateriiForProf(String sid) throws AuthenticationException;
	List<Group> getGrupeForCourse(int courseid);
	
	Course getCourse(int courseid);
	String getCourseDescription(int courseid);
	void updateCourseDescription(int courseid, String desc);
	
	void addMaterial(int courseid, Resource res);
	List<Resource> getMateriale(int courseid);
	void removeMaterialByName(int materieid, String resourceName);

	List<Tema> getTeme(int materieid);
	List<Tema> getTemeStudent(String sid, int materieid) throws AuthenticationException;
	void addTema(Tema t);
	void removeTema(int temaid);
}
