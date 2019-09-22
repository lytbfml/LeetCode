package algorithms.string;

/**
 * @author Yangxiao on 11/14/2018.
 */

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 */
class Count_and_Say {
	
	public String countAndSay(int n) {
		int x = 1;
		int re = 0;
		for (int i = 0; i < n; i++) {
			String t = Integer.toString(x);
			int len = t.length();
			int prev = (int) (x / Math.pow(10, len - 1)) % 10;
			int temp = 1;
			for (int j = len - 2; j >= 0; j--) {
				int m = (int) (x / Math.pow(10, j)) % 10;
				System.out.print(m);
				if (m == prev) {
					temp++;
				} else {
					temp = temp * 10 + prev;
					temp = temp * 10 + 1;
					prev = m;
				}
				System.out.println(" " + temp);
			}
			temp = temp * 10 + prev;
			System.out.println("temp: " + temp);
			x = temp;
		}
		
		return "";
	}
	
	public String countAndSay_2(int n) {
		String intString = "1";
		for (int i = 0; i < n-1; i++) {
			int len = intString.length();
			char prev = intString.charAt(0);
			StringBuilder sb = new StringBuilder();
			int countCur = 1;
			for (int j = 1; j < len; j++) {
				char ch = intString.charAt(j);
				if (ch == prev) {
					countCur++;
				} else {
					sb.append(countCur).append(prev);
					countCur = 1;
					prev = ch;
				}
			}
			sb.append(countCur).append(prev);
			intString = sb.toString();
		}
		
		return intString;
	}
	
	public static void main(String[] args) {
		Count_and_Say cs = new Count_and_Say();
		String re = cs.countAndSay_2(4);
		System.out.println(re);
	}
}
