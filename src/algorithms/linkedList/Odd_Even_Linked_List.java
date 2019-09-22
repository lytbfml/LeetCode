package algorithms.linkedList;

/**
 * @author Yangxiao on 4/9/2019.
 */
public class Odd_Even_Linked_List {
	
	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode odd = head;
		ListNode firstEven = head.next;
		ListNode even = head.next;
		// even != null && even.next != null
		while (odd.next != null && even.next != null) {
			odd.next = odd.next.next;
			odd = odd.next;
			even.next = even.next.next;
			even = even.next;
		}
		odd.next = firstEven;
		return head;
	}
}
