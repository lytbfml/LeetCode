package others;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-08-03
 */
public class SnapshotArray {
	
	Map<Integer, TreeMap<Integer, Integer>> map;
	int snap;
	
	public SnapshotArray(int length) {
		map = new HashMap<>(length);
		snap = 0;
	}
	
	public void set(int index, int val) {
		map.putIfAbsent(index, new TreeMap<>());
		map.get(index).put(snap, val);
	}
	
	public int snap() {
		return snap++;
	}
	
	public int get(int index, int snap_id) {
		if (map.containsKey(index)) {
			if (map.get(index).containsKey(snap_id)) return map.get(index).get(snap_id);
			Map.Entry<Integer, Integer> ee = map.get(index).floorEntry(snap_id);
			if (ee != null) {
				return ee.getValue();
			}
			
		}
		return 0;
	}
	
	class SnapshotArray_ {
		
		List<TreeMap<Integer, Integer>> arr;
		int currId = 0;
		
		public SnapshotArray_(int length) {
			arr = new ArrayList<>();
			
			for (int i = 0; i < length; i++) {
				arr.add(i, new TreeMap<Integer, Integer>());
				arr.get(i).put(0, 0);
			}
		}
		
		public void set(int index, int val) {
			arr.get(index).put(currId, val);
		}
		
		public int snap() {
			return currId++;
		}
		
		public int get(int index, int snap_id) {
			return arr.get(index).floorEntry(snap_id).getValue();
		}
	}
	
	class SnapshotArray_fast {
		int[][] total;
		int idx = 0;
		
		public SnapshotArray_fast(int length) {
			total = new int[1000][length];
			for (int[] t : total) Arrays.fill(t, 0);
		}
		
		public void set(int index, int val) {
			total[idx][index] = val;
		}
		
		public int snap() {
			System.arraycopy(total[idx], 0, total[idx + 1], 0, total[idx].length);
			return idx++;
		}
		
		public int get(int index, int snap_id) {
			return total[snap_id][index];
		}
	}
}
