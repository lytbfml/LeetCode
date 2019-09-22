package algorithms.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-23
 */
public class Subdomain_Visit_Count {
	
	public List<String> subdomainVisits(String[] cpdomains) {
		List<String> res = new ArrayList<>();
		if (cpdomains == null || cpdomains.length == 0) return res;
		int n = cpdomains.length;
		Map<String, Integer> mm = new HashMap<>();
		for (String s : cpdomains) {
			getDoms(s, mm);
		}
		for (Map.Entry<String, Integer> ee : mm.entrySet()) {
			res.add(ee.getValue() + " " + ee.getKey());
		}
		
		return res;
	}
	
	private void getDoms(String s, Map<String, Integer> map) {
		int space = s.indexOf(" ");
		int times = Integer.parseInt(s.substring(0, space));
		
		String dd = s.substring(space + 1);
		int startInd = 0;
		int ind = 0;
		while (ind >= 0) {
			map.put(dd, map.getOrDefault(dd, 0) + times);
			ind = dd.indexOf(".", startInd);
			dd = dd.substring(ind + 1);
		}
	}
}
