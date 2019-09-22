package algorithms.math;

/**
 * @author Yangxiao Wang on 2019-07-01
 */
public class Excel_Sheet_Column_Number {
	
	public int titleToNumber(String s) {
		char[] cc = s.toCharArray();
		int num = 0;
		for (char c: cc) {
			num = num * 26 + c - 'A' + 1;
		}
		return num;
	}
}
