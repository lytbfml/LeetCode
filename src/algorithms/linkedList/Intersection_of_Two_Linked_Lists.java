package algorithms.linkedList;

/**
 * @author Yangxiao on 4/15/2019.
 */
public class Intersection_of_Two_Linked_Lists {
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		ListNode a = headA;
		ListNode b = headB;
		int lena = 0;
		int lenb = 0;
		while (a != null) {
			a = a.next;
			lena++;
		}
		
		while (b != null) {
			b = b.next;
			lenb++;
		}
		
		int diff = 0;
		
		if (lena < lenb) {
			diff = lenb - lena;
			a = headB;
			b = headA;
		} else {
			diff = lena - lenb;
			a = headA;
			b = headB;
		}
		
		for (int i = 0; i < diff; i++) {
			a = a.next;
		}
		
		while (a != null && b != null) {
			if (a == b) {
				return a;
			}
			a = a.next;
			b = b.next;
		}
		
		return null;
	}
	
	public ListNode getIntersectionNode_SMART(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		ListNode a = headA;
		ListNode b = headB;
		if (a != b) {
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}
		return a;
	}
}
