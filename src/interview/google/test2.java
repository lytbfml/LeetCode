package interview.google;

import java.util.Arrays;

public class test2 {
	
	public static void main(String[] args) {
		// Prints "Hello, World" to the terminal window.
		int[] stores = new int[]{1, 5, 20, 11, 16};
		int[] houses = new int[]{5, 10, 17};
		int[] result = stores(stores, houses);
        /*for(int i = 0; i < result.length; i++) {
        	System.out.print(result[i] + " ");
        }

        System.out.println();*/
		int[] a = new int[]{1, 2, 2, 3, 3};
		System.out.println("result is " + findTotalTeams(a, 6));
		//Arrays.sort(stores);
		//System.out.println(findClosest(stores, 7));
	}
	
	
	public static int[] parent(int[] A, int D) {
		int[] result = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int count = 1;
			int temp = A[1];
			while (count < D) {
				
				if (temp == -1) {
					result[i] = -1;
					break;
				} else {
					temp = A[temp];
					count++;
				}
				
			}
			result[i] = temp;
			count = 0;
		}
		return result;
	}
	
	public static int[] stores(int[] stores, int[] houses) {
		Arrays.sort(stores);
		Arrays.sort(houses);
		
		
		int[] result = new int[houses.length];
		for (int i = 0; i < houses.length; i++) {
			result[i] = getClosest(stores, houses[i]);
			//System.out.println("Cloest index to " + houses[i] + " is " + closestIndex);
			//result = stores[closestIndex];
		}
		return result;
	}
	
	public static int closestNumber(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}
		
		int index = firstIndex(A, target);
		if (index == 0) {
			return 0;
		}
		if (index == A.length) {
			return A.length - 1;
		}
		
		if (target - A[index - 1] < A[index] - target) {
			return index - 1;
		}
		return index;
	}
	
	private static int firstIndex(int[] A, int target) {
		int start = 0, end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < target) {
				start = mid;
			} else if (A[mid] > target) {
				end = mid;
			} else {
				end = mid;
			}
		}
		
		if (A[start] >= target) {
			return start;
		}
		if (A[end] >= target) {
			return end;
		}
		return A.length;
	}
	
	public static int getClosest(int a[], int target) {
		int n = a.length;
		
		int start = 0, end = n, mid = 0;
		while (start < end) {
			mid = (start + end) / 2;
			
			if (a[mid] == target) { return a[mid]; }
			if (target < a[mid]) {
				
				if (mid > 0 && target > a[mid - 1]) {
					return calculateClosest(a[mid - 1],
					                        a[mid], target);
				}
				
				end = mid;
			}
			
			// If target is greater than mid
			else {
				if (mid < n - 1 && target < a[mid + 1]) {
					return calculateClosest(a[mid],
					                        a[mid + 1], target);
				}
				start = mid + 1; // update i
			}
		}
		
		// Only single element left after search
		return a[mid];
	}
	
	public static int calculateClosest(int val1, int val2,
	                                   int target) {
		if (target - val1 > val2 - target) { return val2; } else { return val1; }
	}
	
	public static int findTotalTeams(int[] num, int k) {
		Arrays.sort(num);
		//List<List<Integer>> res = new Structures.LinkedList<>();
		int count = 0;
		for (int i = 0; i < num.length - 2; i++) {
			
			int lo = i + 1, hi = num.length - 1, sum = k - num[i];
			if (i == 0) {
				System.out.println(lo + " " + hi + " " + sum);
			}
			
			while (lo < hi) {
				if (num[lo] + num[hi] == sum) {
					
					if (num[lo] == num[hi]) {
						count = count + (hi - lo + 1) * (hi - lo) / 2;
						
						//continue;
						
					} else if (num[lo] == num[lo + 1] || num[hi] == num[hi - 1]) {
						int countlow = 1;
						int counthi = 1;
						while (lo < hi && num[lo] == num[lo + 1]) {
							countlow++;
							lo++;
						}
						while (lo < hi && num[hi] == num[hi - 1]) {
							counthi++;
							hi--;
						}
						System.out.println("or in here i = " + i + "low = " + lo + " hi = " + hi);
						count = count + countlow * counthi;
						
						//break;
					} else {
						System.out.println("was I eve here");
						count = count + 1;
						
						
					}
					
					lo++; hi--;
					//
				} else if (num[lo] + num[hi] < sum) { lo++; } else { hi--; }
			}
			
		}
		return count;
	}
	
}