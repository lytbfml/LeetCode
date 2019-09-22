package algorithms.linkedList;

/**
 * @author Yangxiao on 12/17/2018.
 * <p>
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note: Given n will always be valid.
 * <p>
 * Could you do this in one pass?
 */
public class Remove_Nth_Node_From_End_of_List {
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		int re = remove(head, n);
		return (re == 2 && n == 1) ? null : head;
	}
	
	public int remove(ListNode temp, int n) {
		int t = 1;
		if (temp.next != null) {
			t = remove(temp.next, n);
		}
		
		if (t == n && n != 1) {
			temp.val = temp.next.val;
			temp.next = temp.next.next;
		} else if (t == 2 && n == 1) {
			temp.next = null;
		}
		
		return t + 1;
	}
	
	public ListNode removeNthFromEnd_Sol1(ListNode head, int n) {
		ListNode ptr2 = head;
		while (ptr2 != null && n-- > 0) {
			ptr2 = ptr2.next;
		}
		if (ptr2 == null) { return head.next; }
		
		ListNode ptr1 = head;
		while (ptr2.next != null) {
			ptr2 = ptr2.next;
			ptr1 = ptr1.next;
		}
		ptr1.next = ptr1.next.next;
		
		return head;
	}
}
