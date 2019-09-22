package algorithms.array;

import java.util.*;

/**
 * @author Yangxiao Wang on 8/26/2019
 */
public class Invalid_Transactions {
	
	
	public List<String> invalidTransactions(String[] trans) {
		int n = trans.length;
		Set<String> res = new HashSet<>();
		Map<String, Map<Integer, Map<String, String>>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String[] mm = trans[i].split(",");
			int curTime = Integer.parseInt(mm[1]);
			if (Integer.parseInt(mm[2]) > 1000) {
				res.add(trans[i]);
			}
			
			if (map.containsKey(mm[0])) {
				for (Map.Entry<Integer, Map<String, String>> e : map.get(mm[0]).entrySet()) {
					int lastTime = e.getKey();
					if (Math.abs(lastTime - curTime) <= 60 && (e.getValue().size() > 1 ||
							!e.getValue().containsKey(mm[3]))) {
						res.add(trans[i]);
						for (Map.Entry<String, String> es : e.getValue().entrySet()) {
							if (!es.getKey().equals(mm[3])) {
								res.add(es.getValue());
							}
						}
					}
				}
			}
			map.putIfAbsent(mm[0], new TreeMap<>());
			map.get(mm[0]).putIfAbsent(curTime, new HashMap<>());
			map.get(mm[0]).get(curTime).put(mm[3], trans[i]);
		}
		
		return new ArrayList<>(res);
	}
	
	public List<String> invalidTransactions2(String[] transactions) {
		List<String> ans = new ArrayList<>();
		int n = transactions.length;
		if (n == 0) return ans;
		String[] name = new String[n];
		int[] time = new int[n];
		int[] money = new int[n];
		String[] city = new String[n];
		boolean[] add = new boolean[n];
		for (int i = 0; i < n; i++) {
			String[] items = transactions[i].split(",");
			name[i] = items[0];
			time[i] = Integer.parseInt(items[1]);
			money[i] = Integer.parseInt(items[2]);
			city[i] = items[3];
		}
		
		for (int i = 0; i < n; i++) {
			if (money[i] > 1000)
				add[i] = true;
			for (int j = i + 1; j < n; j++) {
				if (name[i].equals(name[j]) && Math.abs(time[i] - time[j]) <= 60 && !city[i].equals(city[j])) {
					add[i] = true;
					add[j] = true;
				}
			}
		}
		for (int i = 0; i < n; i++) if (add[i]) ans.add(transactions[i]);
		return ans;
	}
}
