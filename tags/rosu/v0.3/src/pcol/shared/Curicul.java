package pcol.shared;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Curicul implements IsSerializable{
	public Map<Integer, List<CourseGroup>> cursuriPeAn = new HashMap<Integer, List<CourseGroup>>();

}
