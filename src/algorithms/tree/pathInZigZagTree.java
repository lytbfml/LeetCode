package algorithms.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yangxiao Wang on 6/29/2019
 */
public class pathInZigZagTree {
	
	public List<Integer> pathInZigZagTree(int label) {
		int layer = 1;
		int temp = 1;
		int total = 1;
		while (total < label) {
			total = total + temp * 2;
			temp = temp * 2;
			layer++;
		}
		System.out.println(layer + ", " + total + ", ");
		temp = 1;
		total = 1;
		int[] tt = new int[layer];
		tt[0] = 1;
		for (int i = 1; i < layer; i++) {
			total = total + temp * 2;
			temp *= 2;
			tt[i] = total;
		}
		
		int curVal = label;
		List<Integer> res = new ArrayList<>();
		res.add(curVal);
		for (int i = layer-1; i > 0; i--) {
			if (i % 2 == 0) {
				int posCur = curVal - tt[i-1] - 1;
				int posUpper = posCur / 2;
				curVal = tt[i - 1] - posUpper;
			} else {
				int posCur = tt[i] - curVal;
				int posUpper = posCur / 2;
				if (i > 1) {
					curVal = tt[i - 2] + 1 + posUpper;
				} else {
					curVal = 1;
				}
			}
			res.add(curVal);
		}
		Collections.reverse(res);
		
		return res;
	}
	
}
