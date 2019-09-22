package algorithms.hashtable;

/**
 * @author Yangxiao Wang on 7/27/2019
 */
public class Alphabet_Board_Path {
	
	public static void main(String[] args) {
		Alphabet_Board_Path cs = new Alphabet_Board_Path();
		System.out.println(cs.alphabetBoardPath("zdz"));
		
	}
	
	public String alphabetBoardPath(String target) {
		String[] board = {"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
		StringBuilder sb = new StringBuilder();
		int i = 0, j = 0;
		for (char c : target.toCharArray()) {
			int e = c - 'a';
			int r = e / 5;
			int col = e % 5;
			int toY = r - i;
			int toX = col - j;
			
			if (c == 'z') {
				if (toX > 0) {
					j += helper(toX, 'R', sb);
				} else {
					j -= helper(-toX, 'L', sb);
				}
				if (toY > 0) {
					i += helper(toY, 'D', sb);
				} else {
					i -= helper(-toY, 'U', sb);
				}
			} else {
				if (toY > 0) {
					i += helper(toY, 'D', sb);
				} else {
					i -= helper(-toY, 'U', sb);
				}
				if (toX > 0) {
					j += helper(toX, 'R', sb);
				} else {
					j -= helper(-toX, 'L', sb);
				}
			}
			sb.append("!");
		}
		return sb.toString();
	}
	
	private int helper(int to, char c, StringBuilder sb) {
		int res = 0;
		for (int i = 0; i < to; i++) {
			res++;
			sb.append(c);
		}
		return res;
	}
}
