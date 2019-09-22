package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 4/6/2019.
 */
public class Camelcase_Matching {
	
	public static void main(String[] args) {
		Camelcase_Matching cs = new Camelcase_Matching();
		String[] ss = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
		System.out.println((cs.camelMatch(ss, "FB")));
	}
	
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> res = new ArrayList<>();
		StringBuilder patterns = new StringBuilder("[a-z]*");
		for (int i = 0; i < pattern.length(); i++) {
			patterns.append(pattern.charAt(i) + "").append("[a-z]*");
		}
		String pp = patterns.toString();
		for (String i : queries) {
			res.add(i.matches(pp));
		}
		return res;
	}
}
