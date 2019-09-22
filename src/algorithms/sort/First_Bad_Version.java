package algorithms.sort;

/**
 * @author Yangxiao on 3/23/2019.
 */
public class First_Bad_Version {
	
	boolean isBadVersion(int version) {
		return true;
	}
	
	public int firstBadVersion(int n) {
		int left = 1;
		int right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return left;
	}
	
	
	public int firstBadVersion_goodMemory(int n) {
		return finder(1, n);
	}
	
	public int finder(int start, int end){
		if(start >= end){
			return start;
		}
		else{
			int mid = (start + end)>>>1;
			if(isBadVersion(mid)){
				return finder(start, mid);
			}
			else{
				return finder(mid + 1, end);
			}
		}
	}
}
