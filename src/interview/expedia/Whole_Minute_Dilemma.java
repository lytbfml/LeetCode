package interview.expedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 11/1/2018.
 */

class Whole_Minute_Dilemma {
	
	public static long playlist(List<Integer> songs) {
		// Write your code here
		int size = songs.size();
		long count = 0;
		
		for (int i = 0; i < size - 1; i++) {
			int cur = songs.get(i);
			boolean isModby = (cur % 60 == 0);
			for (int j = i + 1; j < size; j++) {
				int search = songs.get(j);
				if ((cur + search) % 60 == 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Whole_Minute_Dilemma s = new Whole_Minute_Dilemma();
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(10, 50, 90, 30, 90, 80, 100, 1000));
		long n = s.playlist(arr);
		System.out.println(n);
	}
}
