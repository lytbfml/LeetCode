package algorithms.math;

/**
 * @author Yangxiao Wang on 6/29/2019
 */
public class distributeCandies {
	
	public int[] distributeCandies(int candies, int num_people) {
		
		int[] res = new int[num_people];
		int rem = candies;
		int give = 0;
		while(rem > 0) {
			for (int i = 0; i < num_people; i++) {
				give++;
				
				if (give >= rem) {
					res[i] += rem;
					rem-= give;
					break;
				} else {
					res[i] += give;
					rem-= give;
				}
				
			}
		}
		
		return res;
	}
}
