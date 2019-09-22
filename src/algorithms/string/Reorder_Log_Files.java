package algorithms.string;

/**
 * @author Yangxiao on 11/10/2018.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each
 * log has at least one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are
 * ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The
 * digit-logs should be put in their original order.
 * <p>
 * Return the final order of the logs.
 */
class Reorder_Log_Files {
	
	public String[] reorderLogFiles(String[] logs) {
		int n = logs.length;
		
		ArrayList<Integer> numInd = new ArrayList<>();
		ArrayList<String> letters = new ArrayList<>();
		String[] result = new String[n];
		for (int i = 0; i < n; i++) {
			String temp = logs[i];
			if (isLetter(temp)) {
				letters.add(logs[i]);
			} else {
				numInd.add(i);
			}
		}
		int numDigits = numInd.size();
		int numLetter = n - numDigits;
		
		for (int i = 0; i < numDigits; i++) {
			result[i + numLetter] = logs[numInd.get(i)];
		}
		Collections.sort(letters, new StringLogCompare());
		for (int i = 0; i < numLetter; i++) {
			result[i] = letters.get(i);
		}
		return result;
	}
	
	public class StringLogCompare implements Comparator<String> {
		
		@Override
		public int compare(String o1, String o2) {
			int in1 = o1.indexOf(" ");
			int in2 = o2.indexOf(" ");
			String s1 = o1.substring(in1 + 1);
			String s2 = o2.substring(in2 + 1);
			int x = s1.compareTo(s2);
			if (x == 0) {
				x = o1.substring(0, in1).compareTo(o2.substring(0, in2));
			}
			return x;
		}
	}
	
	
	public boolean isLetter(String s) {
		char x = s.charAt(s.indexOf(" ") + 1);
		if (x >= '0' && x <= '9') {
			return false;
		}
		return true;
	}
	
	public String[] reorderLogFiles_Sol1(String[] logs) {
		if (logs == null || logs.length == 0)
			return logs;
		Arrays.sort(logs, new Comparator<String>() {
			public int compare(String a, String b) {
				int s1 = a.indexOf(" "); // first space index in string a
				int s2 = b.indexOf(" "); // first space index in string b
				String i1 = a.substring(0, s1); // identifier in string a
				String i2 = b.substring(0, s2); // identifier in string b
				String log1 = a.substring(s1 + 1); // log words in string a
				String log2 = b.substring(s2 + 1); // log words in string b
				if (isDigit(log1.charAt(0)) && isDigit(log2.charAt(0))) {
					return 0; // no change
				} else if (isDigit(log1.charAt(0))) {
					return 1; // Structures.String b comes first
				} else if (isDigit(log2.charAt(0))) {
					return -1; // Structures.String a comes first
				} else {
					int temp = log1.compareTo(log2);
					return temp == 0 ? i1.compareTo(i2) : temp;
					// compare log words if they are equal, then compare identifiers
				}
			}
			private boolean isDigit(char c) {
				return c >= '0' && c <= '9';
			}
		});
		return logs;
	}
	
	
	public String[] reorderLogFiles_Sol2(String[] logs) {
		Arrays.sort(logs, (log1, log2) -> {
			String[] split1 = log1.split(" ", 2);
			String[] split2 = log2.split(" ", 2);
			boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
			boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
			if (!isDigit1 && !isDigit2) {
				return (split1[1] + split1[0]).compareTo(split2[1] + split2[0]);
			}
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
		});
		return logs;
	}
	
	public static void main(String[] args) {
		Reorder_Log_Files rl = new Reorder_Log_Files();
		String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
		String[] x = rl.reorderLogFiles(logs);
		System.out.println(Arrays.toString(x));
	}
}
