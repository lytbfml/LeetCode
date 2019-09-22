package algorithms.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao on 3/25/2019.
 * <p>
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 */
public class Roman_to_Integer {
	
	public static void main(String[] args) {
		Roman_to_Integer cs = new Roman_to_Integer();
		System.out.println(cs.romanToInt("VI"));
	}
	
	public int romanToInt(String s) {
		if (s == null) {
			return 0;
		}
		int n = s.length();
		
		int[] mem = new int[100];
		mem['I'] = 1;
		mem['V'] = 5;
		mem['X'] = 10;
		mem['L'] = 50;
		mem['C'] = 100;
		mem['D'] = 500;
		mem['M'] = 1000;
		int[] good = new int[1000];
		good['I'] = 4;
		good['V'] = 4000;
		good['X'] = 40;
		good['L'] = 4000;
		good['C'] = 400;
		good['D'] = 4000;
		good['M'] = 4000;
		
		if (n == 1) {
			return mem[s.charAt(0)];
		}
		
		int res = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (res > good[s.charAt(i)]) {
				res -= mem[s.charAt(i)];
			} else {
				res += mem[s.charAt(i)];
			}
		}
		
		return res;
	}
	
	public int romanToInt_Sol(String s) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		char[] sc = s.toCharArray();
		int total = map.get(sc[0]);
		int pre = map.get(sc[0]);
		for (int i = 1; i < sc.length; i++) {
			int cur = map.get(sc[i]);
			if (cur <= pre) {total += cur;} else {total = total + cur - 2 * pre;}
			pre = cur;
		}
		return total;
	}
}
