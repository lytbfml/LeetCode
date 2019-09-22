package util;

import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-17
 */
public class Utilities {
	
	public static void print2DArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}
	
	public static void printCharIntMap(Map<Character, Integer> map) {
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}
	
	public static void printIntIntMap(Map<Integer, Integer> map) {
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}
}
