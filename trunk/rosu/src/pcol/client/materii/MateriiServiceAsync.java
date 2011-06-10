package pcol.client.materii;

import java.util.List;

import pcol.shared.Course;
import pcol.shared.Group;
import pcol.shared.Resource;
import pcol.shared.Tema;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MateriiServiceAsync {

	void getMateriiForProf(String sid, AsyncCallback<List<Course>> callback);

	void getGrupeForCourse(int courseid, AsyncCallback<List<Group>> callback);


	void addMaterial(int courseid, Resource res, AsyncCallback<Void> callback);

	void getMateriale(int courseid, AsyncCallback<List<Resource>> callback);

	void updateCourseDescription(int courseid, String desc,
			AsyncCallback<Void> callback);

	void getCourse(int courseid, AsyncCallback<Course> callback);

	void getCourseDescription(int courseid, AsyncCallback<String> callback);

	void removeMaterialByName(int materieid, String resourceName,
			AsyncCallback<Void> callback);

	void getTeme(int materieid, AsyncCallback<List<Tema>> callback);

	void addTema(Tema t, AsyncCallback<Void> callback);

	void removeTema(int temaid, AsyncCallback<Void> callback);

	void getTemeStudent(String sid, int materieid,
			AsyncCallback<List<Tema>> callback);
	
	

}
