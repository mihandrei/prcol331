package pcol.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import pcol.shared.User;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Tips {
	int nture = 0;
	int MAXTURE = 3;
	private Map<String,List> map;

	public Tips(User usr) {
		map = new HashMap<String, List>();
		map.put("orar",Arrays.asList(1,
				"tip: incearca linkurile 'curs' , 'seminar' , 'lab'",
				"tip: evitati sa uitati"));
		map.put("contract",Arrays.asList(1,
				"tip: puteti sa va inscrieti la cursuri din ani diferiti",
				"tip: se poate da click pe tot randul"));
		map.put("teme",Arrays.asList(1,
				"in constructie"	));
		map.put("inmatriculare",Arrays.asList(1,
				"in constructie"));

	}

	public String getTip(String category) {
		List tips = map.get(category);
		if(tips!=null){
			int curtip = (Integer) tips.get(0);
			if(curtip<tips.size()){
				tips.set(0, curtip+1);
				return (String) tips.get(curtip);
			}else if(nture<MAXTURE){
				tips.set(0,1);
				nture++;
			}
		}
		return null;
	}
	
}
