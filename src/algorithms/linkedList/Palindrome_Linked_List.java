package algorithms.linkedList;

/**
 * @author Yangxiao on 12/17/2018.
 */
public class Palindrome_Linked_List {
	
	public boolean isPalindrome(ListNode head) {
		ListNode hd = head;
		ListNode s = head;
		while (hd != null && hd.next != null) {
			hd = hd.next.next;
			s = s.next;
		}
		
		s = reverse2(s);
		
		while (s != null) {
			if (s.val != head.val) {
				return false;
			}
			s = s.next;
			head = head.next;
		}
		
		return true;
	}
	
	ListNode reverse2(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
}
