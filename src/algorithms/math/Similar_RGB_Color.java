package algorithms.math;

/**
 * @author Yangxiao on 10/1/2019.
 */
public class Similar_RGB_Color {
	public static void main(String[] args) {
		Similar_RGB_Color cs = new Similar_RGB_Color();
		System.out.println(cs.similarRGB("#09f166"));
	}
	
	public String similarRGB(String color) {
		int[] arr = {0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF};
		int[] cc = new int[3];
		cc[0] = Integer.parseInt(color.substring(1, 3), 16);
		cc[1] = Integer.parseInt(color.substring(3, 5), 16);
		cc[2] = Integer.parseInt(color.substring(5, 7), 16);
		StringBuilder sb = new StringBuilder();
		sb.append('#');
		for (int k = 0; k < 3; k++) {
			int a = cc[k], min = Integer.MAX_VALUE, idx = 0;
			for (int i = 0; i < arr.length; i++) {
				int diff = Math.abs(a - arr[i]);
				if (diff < min) {
					min = diff;
					idx = i;
				}
			}
			if (idx == 0) {
				sb.append("00");
			} else {
				sb.append(Integer.toHexString(arr[idx]));
			}
		}
		return sb.toString();
	}
	
	class Solution {
		public String similarRGB(String color) {
			return "#" + f(color.substring(1, 3)) + f(color.substring(3, 5)) + f(color.substring(5));
		}
		
		public String f(String comp) {
			int q = Integer.parseInt(comp, 16);
			q = q / 17 + (q % 17 > 8 ? 1 : 0);
			return String.format("%02x", 17 * q);
		}
	}
	
}
