package algorithms.string;

/**
 * @author Yangxiao Wang on 2019-07-23
 */
public class Integer_to_English_Words {
	
	String[] mapping = new String[]{
			"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve"
			, "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
	};
	
	String[] mappingTen = new String[]{
			"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
	};
	
	String[] tenTh = new String[]{
			"Hundred", "Thousand", "Million", "Billion"
	};
	
	public String numberToWords(int num) {
		if (num == 0) return "Zero";
		
		StringBuilder res = new StringBuilder();
		int cn = 0;
		while (num > 0) {
			int wo = num % 1000;
			String tail = cn == 0 ? "" : tenTh[cn];
			String nn = withinThousand(wo);
			if (nn.length() != 0) {
				res.insert(0, " " + nn + " " + tail);
			}
			num /= 1000;
			cn++;
		}
		return res.toString().trim();
	}
	
	private String withinThousand(int org) {
		if (org == 0) {
			return "";
		}
		if (org < 20) {
			return mapping[org];
		}
		
		StringBuilder res = new StringBuilder();
		
		int num = org;
		
		int firstD = num % 10;
		if (firstD != 0) res.append(" ").append(mapping[firstD]);
		
		num /= 10;
		int secD = num % 10;
		
		if (secD == 1) {
			if (firstD != 0) {
				res = new StringBuilder();
				res.append(" ").append(mapping[10 + firstD]);
			} else {
				res.append(" Ten");
			}
		} else if (secD > 1) {
			res.insert(0, " " + mappingTen[secD]);
		}
		
		if (org >= 100) {
			num /= 10;
			int third = num % 10;
			res.insert(0, " " + mapping[third] + " " + tenTh[0]);
		}
		
		return res.toString().trim();
	}
	
	public static void main(String[] args) {
		Integer_to_English_Words cs = new Integer_to_English_Words();
		System.out.println(cs.numberToWords(1000000000));
		System.out.println(Integer.MAX_VALUE);
	}
	
	static class Solution {
		private final static String[] LESS_THAN_20 = {
				"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
				"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
				"Nineteen", "Twenty"
		};
		private final static String[] TENS = {
				"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
				"Ninety"
		};
		private final static String[] SEPERATES = {
				"", "Thousand", "Million", "Billion"
		};
		
		public String numberToWords(int num) {
			StringBuilder sb = new StringBuilder();
			int seperate = 3;
			
			int t = num / (int) Math.pow(1000, seperate);
			int m = num % (int) Math.pow(1000, seperate);
			
			while (seperate >= 0 && (t != 0 || m != 0)) {
				int tmp = t;
				if (t > 0) {
					if (t >= 100) {
						sb.append(LESS_THAN_20[t / 100]);
						sb.append(" Hundred ");
						t %= 100;
					}
					if (t > 20) {
						sb.append(TENS[t / 10]);
						sb.append(" ");
						t %= 10;
					}
					if (t > 0) {
						sb.append(LESS_THAN_20[t]);
						sb.append(" ");
					}
				}
				if (seperate > 0) {
					if (tmp != 0) {
						sb.append(SEPERATES[seperate]);
						sb.append(" ");
					}
					t = m / (int) Math.pow(1000, seperate - 1);
					m = m % (int) Math.pow(1000, seperate - 1);
				}
				--seperate;
			}
			
			if (sb.length() == 0) {
				sb.append("Zero");
			}
			
			return sb.toString().trim();
		}
	}
	
	public class Solution_shortClean {
		private final String[] belowTen = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
		                                               "Eight", "Nine"};
		private final String[] belowTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
		                                                  "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
		private final String[] belowHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
		                                                   "Seventy", "Eighty", "Ninety"};
		
		public String numberToWords(int num) {
			if (num == 0) return "Zero";
			return helper(num);
		}
		
		private String helper(int num) {
			String result;
			if (num < 10) result = belowTen[num];
			else if (num < 20) result = belowTwenty[num - 10];
			else if (num < 100) result = belowHundred[num / 10] + " " + helper(num % 10);
			else if (num < 1000) result = helper(num / 100) + " Hundred " + helper(num % 100);
			else if (num < 1000000) result = helper(num / 1000) + " Thousand " + helper(num % 1000);
			else if (num < 1000000000) result = helper(num / 1000000) + " Million " + helper(num % 1000000);
			else result = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
			return result.trim();
		}
	}
}
