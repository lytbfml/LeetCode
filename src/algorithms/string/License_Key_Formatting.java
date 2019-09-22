package algorithms.string;

/**
 * @author Yangxiao Wang on 2019-07-22
 */
public class License_Key_Formatting {
	
	public static void main(String[] args) {
		String s = "sda";
		System.out.println(s.substring(0, 1));
	}
	
	public String licenseKeyFormatting(String S, int K) {
		S = S.toUpperCase();
		S = S.replaceAll("-", "");
		int len = S.length();
		int remin = len % K;
		int size = len / K;
		StringBuilder sb = new StringBuilder();
		sb.append(S.substring(0, remin));
		int index = remin;
		for (int i = 0; i < size; i++) {
			if (i != 0) sb.append("-");
			if (i == 0 && remin != 0) sb.append("-");
			sb.append(S, index, index + K);
			index += K;
		}
		return sb.toString();
	}
	
	public String licenseKeyFormatting_clean(String s, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--)
			if (s.charAt(i) != '-')
				sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
		return sb.reverse().toString().toUpperCase();
	}
	
	class Solution {
		public String licenseKeyFormatting_fast(String s, int k) {
			int len = s.length();
			char[] ch = s.toCharArray();
			for (char c : ch)
				if (c == '-') len--;
			if (len == 0) return "";
			
			int head = len % k;
			if (head == 0) head = k;
			
			int cnt = 0;
			StringBuilder sb = new StringBuilder();
			for (char c : ch) {
				if (c == '-') continue;
				if (head == 0 && cnt % k == 0) sb.append('-');
				
				if (c <= 'Z') sb.append(c);
				else sb.append((char) (c - 32));
				
				if (head > 0) head--;
				else cnt++;
			}
			if (sb.charAt(sb.length() - 1) == '-') sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
}
