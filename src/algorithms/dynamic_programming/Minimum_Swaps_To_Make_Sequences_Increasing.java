package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 10/1/2019.
 */
public class Minimum_Swaps_To_Make_Sequences_Increasing {
	
	public static void main(String[] args) {
		Minimum_Swaps_To_Make_Sequences_Increasing cs = new Minimum_Swaps_To_Make_Sequences_Increasing();
		System.out.println(cs.minSwap(new int[]{1, 3, 5, 4}, new int[]{1, 2, 3, 7}));
		System.out.println(cs.minSwap(new int[]{0, 4, 4, 5, 9}, new int[]{0, 1, 6, 8, 10}));
		System.out.println(cs.minSwap(new int[]{3, 3, 8, 9, 10}, new int[]{1, 7, 4, 6, 8}));
		System.out.println(cs.minSwap(new int[]{0, 7, 8, 10, 10, 11, 12, 13, 19, 18},
				new int[]{4, 4, 5, 7, 11, 14, 15, 16, 17, 20}));
		System.out.println(cs.minSwap(new int[]{1, 8, 4, 6, 7, 8, 16, 18, 19, 29, 25, 26, 35, 31, 38, 35, 37, 41, 39,
		                                        43, 48, 49, 46, 47, 50,
		                                        52, 54, 55, 63, 58, 67, 63, 69, 71, 74, 73, 76, 77, 80, 78, 79, 80, 86,
		                                        87, 90, 93, 95, 96,
		                                        97, 100, 101, 105, 106, 108, 108, 109, 116, 111, 119, 121, 122, 124,
		                                        130, 125, 126,
		                                        127, 128, 140, 144, 135, 148, 151, 143, 154, 155, 157, 160, 156, 158,
		                                        159, 168, 170,
		                                        163, 173, 175, 176, 177, 180, 177, 182, 183, 184, 185, 190, 191, 194,
		                                        194, 195, 196,
		                                        200},
				new int[]{7, 3, 12, 14, 19, 22, 24, 26, 27, 21, 30, 31, 28, 36,
				          33, 39, 40, 38, 42, 43, 44, 45, 50, 55, 56, 57, 58, 61,
				          56, 64, 60, 68, 70, 71, 72, 75, 74, 75, 77, 81, 82, 83,
				          81, 84, 91, 93, 95, 96, 99, 100, 102, 103, 104, 107,
				          113, 114, 110, 118, 112, 113, 114, 115, 124, 132, 133,
				          134, 135, 131, 134, 145, 137, 140, 152, 144, 150, 151,
				          154, 163, 165, 166, 160, 161, 172, 164, 168, 171, 172,
				          173, 181, 183, 184, 187, 189, 191, 192, 193, 195, 198,
				          199, 197}));
	}
	
	
	public int minSwap(int[] A, int[] B) {
		int swapRecord = 1, fixRecord = 0;
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
				// In this case, the ith manipulation should be same as the i-1th manipulation
				// fixRecord = fixRecord;
				swapRecord++;
			} else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
				// In this case, the ith manipulation should be the opposite of the i-1th manipulation
				int temp = swapRecord;
				swapRecord = fixRecord + 1;
				fixRecord = temp;
			} else {
				// Either swap or fix is OK. Let's keep the minimum one
				int min = Math.min(swapRecord, fixRecord);
				swapRecord = min + 1;
				fixRecord = min;
			}
		}
		return Math.min(swapRecord, fixRecord);
	}
	
	
	public int minSwap2(int[] A, int[] B) {
		int N = A.length;
		int[] swap = new int[1000];
		int[] not_swap = new int[1000];
		swap[0] = 1;
		for (int i = 1; i < N; ++i) {
			not_swap[i] = swap[i] = N;
			if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
				not_swap[i] = not_swap[i - 1];
				swap[i] = swap[i - 1] + 1;
			}
			if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
				not_swap[i] = Math.min(not_swap[i], swap[i - 1]);
				swap[i] = Math.min(swap[i], not_swap[i - 1] + 1);
			}
		}
		return Math.min(swap[N - 1], not_swap[N - 1]);
	}
}
