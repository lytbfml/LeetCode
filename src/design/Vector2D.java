package design;

import java.util.Iterator;
import java.util.List;

public class Vector2D {
	
	private Iterator<List<Integer>> i;
	private Iterator<Integer> j;
	
	public Vector2D(List<List<Integer>> vec2d) {
		i = vec2d.iterator();
	}
	
	public int next() {
		hasNext();
		return j.next();
	}
	
	public boolean hasNext() {
		while ((j == null || !j.hasNext()) && i.hasNext())
			j = i.next().iterator();
		return j != null && j.hasNext();
	}
	
	class Vector2DArray {
		int row, col;
		
		int[][] v;
		
		public Vector2DArray(int[][] v) {
			row = 0;
			col = 0;
			this.v = v;
		}
		
		public int next() {
			if (hasNext()) {
				int res = v[row][col];
				col++;
				return res;
			}
			return -1;
		}
		
		public boolean hasNext() {
			while (row < v.length) {
				if (col < v[row].length)
					return true;
				else {
					row++;
					col = 0;
				}
			}
			return false;
		}
	}
	
	class Vector2D_Smart {
		
		int i;
		int j;
		int[][] v;
		
		public Vector2D_Smart(int[][] v) {
			i = 0;
			j = 0;
			this.v = v;
		}
		
		public int next() {
			hasNext();
			int res = v[i][j++];
			if (j == v[i].length) {
				i++;
				j = 0;
			}
			return res;
		}
		
		public boolean hasNext() {
			// in the case of [[],[],[]], we will return false
			//         while (i < v.length) {
			//             if (j < v[i].length) return true;
			//             i++;
			//         }
			//
			//         return false;
			if (i >= v.length)
				return false;
			
			if (j == v[i].length) {
				i++;
				j = 0;
				return hasNext();
			}
			
			return true;
		}
	}
}


