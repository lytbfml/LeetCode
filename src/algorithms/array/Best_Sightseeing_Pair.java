package algorithms.array;

/**
 * @author Yangxiao on 3/24/2019.
 */
public class Best_Sightseeing_Pair {
	
	public static void main(String[] args) {
		Best_Sightseeing_Pair cs = new Best_Sightseeing_Pair();
		int[] A = {8, 1, 5, 2, 6};
		System.out.println(cs.maxScoreSightseeingPair(A));
		System.out.println(cs.maxScoreSightseeingPair_bruteForce(A));
	}
	
	
	public int maxScoreSightseeingPair(int[] A) {
		int last = 0;
		int maxScore = 0;
		
		for (int i = 0; i < A.length; i++) {
			maxScore = Math.max(A[i] + last, maxScore);
			last = Math.max(A[i], last) - 1;
		}
		
		return maxScore;
	}
	
	public int maxScoreSightseeingPair_bruteForce(int[] A) {
		int score = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				score = Math.max(A[i] + A[j] + i - j, score);
			}
		}
		return score;
	}
}
