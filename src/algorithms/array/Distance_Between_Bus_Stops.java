package algorithms.array;

/**
 * @author Yangxiao on 9/7/2019.
 */
public class Distance_Between_Bus_Stops {
	
	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int sum = start + destination, total = 0, forwardDistance = 0;
		start = Math.min(start, destination);
		destination = sum - start;
		for (int d : distance)
			total += d;
		for (int i = start; i < destination; ++i)
			forwardDistance += distance[i];
		return Math.min(forwardDistance, total - forwardDistance);
	}
}
