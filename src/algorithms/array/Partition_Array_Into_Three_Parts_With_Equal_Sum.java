package algorithms.array;

/**
 * @author Yangxiao on 3/23/2019.
 */
public class Partition_Array_Into_Three_Parts_With_Equal_Sum {
	
	public static void main(String[] args) {
		Partition_Array_Into_Three_Parts_With_Equal_Sum cs =
				new Partition_Array_Into_Three_Parts_With_Equal_Sum();
		int[] s = {18,12,-18,18,-19,-1,10,10};
		System.out.println(cs.canThreePartsEqualSum(s));
	}
	
	public boolean canThreePartsEqualSum_2(int[] A) {
		
		int sum = 0;
		for (int i: A){
			sum += i;
		}
		
		if(sum % 3 != 0) return false;
		int part = sum / 3;
		int n = A.length;
		int curPart = 0;
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			curPart += A[i];
			if (curPart == part) {
				count++;
				curPart = 0;
				if(count == 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canThreePartsEqualSum(int[] A) {
		if (A == null || A.length <= 2) {
			return false;
		}
		
		int all = addLeftandRight(A, 0, A.length);
		if (all % 3 != 0) {
			return false;
		}
		int sum = all / 3;
		int sum2 = sum * 2;
		int lSum = 0;
		int count = 0;
		for (int i = 0; i < A.length - 1; i++) {
			lSum += A[i];
			if (lSum == sum && count == 0) {
				count++;
			} else if (lSum == sum2 && count != 0) {
				count++;
				break;
			}
		}
		
		return count == 2;
	}
	
	public int addLeftandRight(int[] A, int i, int j) {
		int res = 0;
		for (int k = i; k < j; k++) {
			res += A[k];
		}
		return res;
	}
	
	
}
