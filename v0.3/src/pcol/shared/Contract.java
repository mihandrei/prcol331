package pcol.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Contract implements IsSerializable{
	 public  int version;
	 public  List<Integer> selectedcourses;
}
