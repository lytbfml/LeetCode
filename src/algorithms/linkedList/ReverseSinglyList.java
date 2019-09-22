package algorithms.linkedList;

/**
 * @author Yangxiao on 10/29/2018.
 */

class ReverseSinglyList {
	
	ListNode reverse(ListNode head) {
		if (head.next == null || head == null) {
			return head;
		}
		
		ListNode newHead = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	ListNode reverse2(ListNode node) {
		ListNode prev = null;
		ListNode current = node;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}
	
	ListNode reverse2_Change(ListNode head) {
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
