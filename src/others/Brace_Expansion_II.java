package others;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-08-27
 */
public class Brace_Expansion_II {
	
	public static void main(String[] args) {
		Brace_Expansion_II cs = new Brace_Expansion_II();
		System.out.println(cs.braceExpansionII("{a,b}{c,d,e}"));
		Integer s = 2;
		System.out.println(s);
	}
	
	// "{a,b}{c,{d,e}}"
	// "{{a,z},a{b,c},{ab,z}}"
	// "a{a,b}"
	// "{a,b}{c,{d,e}}"
	int idx = 0;
	
	public List<String> braceExpansionII(String exp) {
		Set<String> cL = commaList(exp);
		List<String> res = new ArrayList<>(cL);
		Collections.sort(res);
		return res;
	}
	
	private Set<String> commaList(String exp) {
		Set<String> ret;
		int n = exp.length();
		ret = braceList(exp);
		while (idx < n) {
			if (exp.charAt(idx) != ',') {
				break;
			}
			idx++;
			ret.addAll(braceList(exp));
		}
		return ret;
	}
	
	public Set<String> braceList(String exp) {
		Set<String> ret = new HashSet<>();
		int n = exp.length();
		while (idx < n) {
			char cc = exp.charAt(idx);
			if (cc == '{') {
				idx++;
				Set<String> tmp = commaList(exp);
				idx++;
				ret = merge(ret, tmp);
			} else if (cc == '}' || cc == ',') {
				break;
			} else {
				Set<String> tmp = new HashSet<>();
				StringBuilder sb = new StringBuilder();
				while (idx < n) {
					char dd = exp.charAt(idx);
					if (dd < 'a' || dd > 'z') {
						break;
					}
					sb.append(dd);
					idx++;
				}
				tmp.add(sb.toString());
				ret = merge(ret, tmp);
			}
		}
		
		return ret;
	}
	
	private Set<String> merge(Set<String> s1, Set<String> s2) {
		if (s1.size() == 0) return s2;
		if (s2.size() == 0) return s1;
		Set<String> ret = new HashSet<>();
		for (String s : s1) {
			for (String ss : s2) {
				ret.add(s + ss);
			}
		}
		return ret;
	}
	
	class SolutionStackMaybe {
		
		public List<String> braceExpansionII(String expression) {
			String s = expression;
			char preSign = ',';
			Stack<List<String>> stack = new Stack<>();// Save List<String>
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				// case 1. {...} recursive, stack.operate(resList) by preSign
				if (c == '{') {
					int j = i, p = 1;
					while (s.charAt(j) != '}' || p != 0) {
						j++;
						if (s.charAt(j) == '{') p++;
						if (s.charAt(j) == '}') p--;
					}
					List<String> slist = braceExpansionII(s.substring(i + 1, j));
					if (preSign == '*') {
						stack.push(merge(stack.pop(), slist));
					} else stack.push(slist);
					i = j;
					//default preSign is *
					preSign = '*';
				}
				// case 2 letter operate by preSign
				else if (Character.isLetter(c)) {
					List<String> slist = new ArrayList<>();
					slist.add("" + c);
					if (preSign == '*') {
						stack.push(merge(stack.pop(), slist));
					} else stack.push(slist);
					// //default preSign is *
					preSign = '*';
				}
				// case 3. if  == ", ", preSign is  plus, (default preSign is *);
				if (c == ',' || i == s.length() - 1) {
					preSign = ',';
				}
			}
			// output stack to one dimesion list;
			List<String> res = new ArrayList<>();
			while (!stack.isEmpty()) {
				for (String l : stack.pop())
					if (!res.contains(l)) res.add(l);
			}
			// sort by lexi-order
			Collections.sort(res);
			return res;
		}
		
		// multiply operation of 2 List<letter>
		public List<String> merge(List<String> list1, List<String> list2) {
			List<String> res = new ArrayList<>();
			for (String l1 : list1) {
				for (String l2 : list2) {
					res.add(l1 + l2);
				}
			}
			return res;
		}
	}
}
