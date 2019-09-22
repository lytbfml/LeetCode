package algorithms.linkedList;

/**
 * @author Yangxiao on 12/17/2018.
 * <p>
 * Merge two sorted linked lists and return it as a new list. The new list should be made by
 * splicing together the nodes of the first two lists.
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class Merge_Two_Sorted_Lists {
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		} else if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		}
		ListNode preHead = new ListNode(-1);
		
		ListNode next = preHead;
		
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				next.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				next.next = new ListNode(l2.val);
				l2 = l2.next;
			}
			next = next.next;
		}
		
		next.next = l1 == null?  l2 : l1;
		
		return preHead.next;
	}
	
	public ListNode mergeTwoLists_Sol1(ListNode l1, ListNode l2) {
		if (l1 == null) { return l2; }
		if (l2 == null) { return l1; }
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists_Sol1(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists_Sol1(l1, l2.next);
			return l2;
		}
	}
	
	public ListNode mergeTwoLists_Sol2(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) { return l2 == null ? l1 : l2; }
		ListNode first = (l2.val <= l1.val) ? l2 : l1;
		//System.out.println(l2.val+"    "+l1.val);
		ListNode second = first == l1 ? l2 : l1;
		first.next = mergeTwoLists_Sol2(first.next, second);
		return first;
	}
	
	public ListNode mergeTwoLists_Sol3(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) { return null; }
		if (l1 != null && l2 == null) { return l1; }
		if (l1 == null && l2 != null) { return l2; }
		ListNode res = new ListNode(0);
		ListNode run = res;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				run.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				run.next = new ListNode(l2.val);
				l2 = l2.next;
			}
			run = run.next;
		}
		if (l1 != null && l2 == null) { run.next = l1; } else { run.next = l2; }
		return res.next;
	}
	
	public ListNode mergeTwoLists_Sol4(ListNode l1, ListNode l2) {
		// maintain an unchanging reference to node ahead of the return node.
		ListNode prehead = new ListNode(-1);
		
		ListNode prev = prehead;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}
		
		// exactly one of l1 and l2 can be non-null at this point, so connect
		// the non-null list to the end of the merged list.
		prev.next = l1 == null ? l2 : l1;
		
		return prehead.next;
	}
}

