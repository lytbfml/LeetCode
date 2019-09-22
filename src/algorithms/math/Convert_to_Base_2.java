package algorithms.math;

/**
 * @author Yangxiao Wang on 5/4/2019
 */
public class Convert_to_Base_2 {
	
	public static void main(String[] args) {
		Convert_to_Base_2 cs = new Convert_to_Base_2();
		cs.baseNeg2(6);
		//11010
		cs.baseNeg2(10);
	}
	
	public String baseNeg2(int N) {
		StringBuilder sb = new StringBuilder();
		while (N != 0) {
			sb.insert(0, N & 1);
			N = N >> 1;
		}
		return sb.toString() == "" ? "0" : sb.toString();
	}
	
	public String baseNeg2_Sol1(int N) {
		int num = 1;
		while (num < N)
			num = (num << 2) + 1;
		return Integer.toBinaryString(num ^ (num - N));
	}
}
