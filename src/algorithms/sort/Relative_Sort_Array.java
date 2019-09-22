package algorithms.sort;

import java.util.*;

/**
 * @author Yangxiao Wang on 7/13/2019
 */
public class Relative_Sort_Array {
	
	
	public static void main(String[] args) {
		Relative_Sort_Array cs = new Relative_Sort_Array();
		cs.relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6});
	}
	
	public int[] relativeSortArray(int[] a, int[] b) {
		Set<Integer> set = new HashSet<>();
		for (int x : b) set.add(x);
		List<Integer> nonLis = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			if (!set.contains(a[i])) {
				nonLis.add(a[i]);
			} else {
				map.put(a[i], map.getOrDefault(a[i], 0) + 1);
			}
		}
		
		Collections.sort(nonLis);
		int cnt = 0;
		for (int i = 0; i < b.length; i++) {
			int numi = map.get(b[i]);
			for (int j = 0; j < numi; j++) {
				a[cnt] = b[i];
				cnt++;
			}
		}
		int j = 0;
		for (int i = cnt; i < a.length; i++) {
			a[i] = nonLis.get(j++);
		}
		return a;
	}
	
	public int[] relativeSortArray_sol(int[] arr1, int[] arr2) {
		int k = 0;
		int[] cnt = new int[1001], ans = new int[arr1.length];
		for (int i : arr1) // Count each number in arr1;
			++cnt[i];
		for (int i : arr2) // Sort the numbers in both arrays.
			while (cnt[i]-- > 0)
				ans[k++] = i;
		for (int i = 0; i < 1001; ++i) // Sort the numbers only in arr1.
			while (cnt[i]-- > 0)
				ans[k++] = i;
		return ans;
	}
}
