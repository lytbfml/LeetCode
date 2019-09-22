package algorithms.string;

/**
 * @author Yangxiao Wang on 7/30/2019
 */
public class String_Compression {
	
	public int compress(char[] cc) {
		int len = cc.length;
		char prev = cc[0];
		int tmp = 1, res = 0;
		for (int i = 1; i < len; i++) {
			if (cc[i] == prev) {
				tmp++;
			} else {
				cc[res++] = prev;
				if (tmp > 1) {
					String xx = Integer.toString(tmp);
					int n = xx.length();
					for (int k = 0; k < n; k++) {
						cc[res++] = xx.charAt(k);
					}
					tmp = 1;
				}
				prev = cc[i];
			}
		}
		cc[res++] = prev;
		if (tmp > 1) {
			String xx = Integer.toString(tmp);
			int n = xx.length();
			for (int k = 0; k < n; k++) {
				cc[res++] = xx.charAt(k);
			}
		}
		
		return res;
	}
	
	public int compress_improve(char[] cc) {
		int len = cc.length;
		int tmp = 1, res = 0;
		for (int i = 0; i < len; i++) {
			if (i == len - 1 || cc[i] != cc[i + 1]) {
				cc[res++] = cc[i];
				if (tmp > 1) {
					String xx = Integer.toString(tmp);
					int n = xx.length();
					for (int k = 0; k < n; k++) {
						cc[res++] = xx.charAt(k);
					}
				}
				tmp = 0;
			}
			tmp++;
		}
		
		return res;
	}
	
	class Solution {
		public int compress(char[] chars) {
			int anchor = 0, write = 0;
			for (int read = 0; read < chars.length; read++) {
				if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
					chars[write++] = chars[anchor];
					if (read > anchor) {
						for (char c : ("" + (read - anchor + 1)).toCharArray()) {
							chars[write++] = c;
						}
					}
					anchor = read + 1;
				}
			}
			return write;
		}
	}
}
