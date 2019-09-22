package algorithms.math;

/**
 * wc144
 * @author Yangxiao Wang on 2019-07-06
 */
public class Corporate_Flight_Bookings {
	
	// O(n^2)
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int len = bookings.length;
		int[] res = new int[n];
		for (int i = 0; i < len; i++) {
			for (int j = bookings[i][0]; j <= bookings[i][1]; j++) {
				res[j - 1] += bookings[i][2];
			}
		}
		
		return res;
	}
	
	// O(n) fast
	public int[] corpFlightBookings_Sol1(int[][] bookings, int n) {
		int[] res = new int[n];
		for (int[] b : bookings) {
			res[b[0] - 1] += b[2];
			if (b[1] < n) res[b[1]] -= b[2];
		}
		for (int i = 1; i < n; ++i)
			res[i] += res[i - 1];
		return res;
	}
}
