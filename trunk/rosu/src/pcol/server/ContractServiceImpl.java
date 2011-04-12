package pcol.server;

import java.util.List;

import pcol.client.contract.ContractService;
import pcol.shared.Course;
import pcol.shared.CourseGroup;
import pcol.shared.Curicul;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ContractServiceImpl extends RemoteServiceServlet implements
		ContractService {

	@Override
	public Curicul getCuricula(String nrmatricol) {		
//		throw new RuntimeException("nu mai lucru aza");
		
		return Curicul.mock1();
	}

	@Override
	public void submitContract(Curicul c) {
		System.out.println("contract update request recv");
		
		for(List<CourseGroup> cgs :c.cursuriPeSemestru.values()){
			for(CourseGroup cg : cgs){
				for(Course curs:cg.courses){
					System.out.println(curs.name+ " " + curs.inscris);
				}
			}
		}

	}

}
