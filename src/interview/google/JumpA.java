package interview.google;

/**
 * @author Yangxiao on 12/7/2018.
 */

/**
 * 第一题 给一个全是int的array A，可以选择从array的任何一个index开始跳往后跳。记当前是第i次jump，如果i是奇数，
 * 需要跳到后面所有比当前数字大的数字中最小的一个的位置，如果i是偶数，就要跳到所有比当前数字小的中，数字最大的一个的位置。
 * 求输出整个array中能跳到最后一位的数字有几个。
 * 比如说[10,13,12,14,15], 只有14可以跳到15（第1次跳，是奇数，14后面只有15，且15>14，到达），以及15本身就在最后，所以输出2.
 * <p>
 * 给一个数组，判断有几个元素可以用 以下jump规则跳到最后一个元素。
 * 设当前元素为A：
 * 第 奇数 次jump:  可以跳到 i 之后比A大的最小的数，如果有重复的 跳到最靠近A的那个数 比如：[5, 4, 8, 7, 3, 7, 3], 5在第一次跳跃可以跳到 第4个元素 7
 * 而不是最后一个元素7.
 * 第 偶数 次jump：可以跳到 i 之后比A小的最大的数，如果有重复的 跳到最靠近A的那个数 比如：[5, 4, 8, 7, 3, 7, 3], 5第一次跳到7，第二次跳到3.
 * [hide = 150]
 * 思路就是用dp。建立even和odd的数组，来存第i个元素能不能以偶数/奇数跳跃开始 跳到最终点， 从最后一个元素往回去计算每个odd/even的值。 odd = 能奇数跳到 j &&
 * even[j]， even数组同理。用一个treemap存 A 之后的元素的（value， index）pair（这里如果有相同元素，即直接更新）， 然后找到从 i
 * 奇数/偶数跳到哪个位置就需要log(n)的时间。最后的结果就是odd数组中有多少个true。所以最后的时间复杂度是O(nlog(n))
 */
public class JumpA {
	
	
	public static void main(String[] args) {
		JumpA ja = new JumpA();
		int[] a = {5, 4, 8, 7, 3, 7, 3};
		System.out.println(ja.jj(a));
		
		
		int[] b = {10, 13, 12, 14, 15};
		System.out.println(ja.jj(b));
		
		int[] c = {10, 11, 14, 11, 10};
		System.out.println(ja.jj(c));
	}
	
	private int jj(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int n = A.length;
		int re = 1;
		for (int i = 0; i < n - 1; i++) {
			int even = i, odd = 0;
			while (true) {
				odd = doOdd(A, even, A[i]);
				if (odd == -1) {
					break;
				} else if (odd == n - 1) {
					re++;
					break;
				}
				even = doEven(A, odd, A[i]);
				if (even == -1) {
					break;
				} else if (even == n - 1) {
					re++;
					break;
				}
			}
		}
		
		return re;
	}
	
	private int doOdd(int[] a, int start, int num) {
		int n = a.length;
		int temp = Integer.MAX_VALUE;
		int ind = -1;
		for (int i = start + 1; i < n; i++) {
			if (a[i] > num && a[i] < temp) {
				temp = a[i];
				ind = i;
			}
		}
		return ind;
	}
	
	private int doEven(int[] a, int start, int num) {
		int n = a.length;
		int temp = Integer.MIN_VALUE;
		int ind = -1;
		for (int i = start + 1; i < n; i++) {
			if (a[i] < num && a[i] > temp) {
				temp = a[i];
				ind = i;
			}
		}
		return ind;
	}
}
