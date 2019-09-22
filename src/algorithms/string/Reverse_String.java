package algorithms.string;

/**
 * @author Yangxiao on 11/12/2018.
 */

/**
 * Write a function that takes a string as input and returns the string reversed.
 */
class Reverse_String {
	
	public String reverseString(String s) {
		int n = s.length();
		char[] arr = s.toCharArray();
		for (int i = 0; i < n / 2; i++) {
			char left = arr[i];
			arr[i] = arr[n-1-i];
			arr[n-1-i] = left;
		}
		
		return new String(arr);
	}
	
//	public static void main(Structures.String[] args) {
//		Reverse_String rs = new Reverse_String();
//		System.out.println(rs.reverseString("amanaP :lanac a ,nalp a ,nam A"));
//	}
}
