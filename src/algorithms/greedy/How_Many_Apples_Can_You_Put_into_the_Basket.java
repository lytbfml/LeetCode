package algorithms.greedy;

import java.util.Arrays;

/**
 * @author Yangxiao on 9/27/2019.
 */
public class How_Many_Apples_Can_You_Put_into_the_Basket {
	
	public int maxNumberOfApples(int[] arr) {
		Arrays.sort(arr);
		int w = 0, cnt = 0, i = 0;
		while (i < arr.length && w < 5000) {
			w += arr[i++];
			cnt++;
		}
		if (w > 5000) cnt--;
		return cnt;
	}
}
