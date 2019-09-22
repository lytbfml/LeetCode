package algorithms.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 8/27/2019
 */
public class Remove_Zero_Sum_Consecutive_Nodes_from_LinkedList {
	
	// wrong solution
	public ListNode removeZeroSumSublists(ListNode head) {
		ListNode dummy = new ListNode(0), cur = dummy;
		dummy.next = head;
		int prefix = 0;
		Map<Integer, ListNode> m = new HashMap<>();
		while (cur != null) {
			prefix += cur.val;
			m.putIfAbsent(prefix, cur);
			cur = m.get(prefix).next = cur.next;
		}
		return dummy.next;
	}
	
	
	public ListNode removeZeroSumSublists_2(ListNode head) {
		ListNode dummy = new ListNode(0), cur = dummy;
		dummy.next = head;
		int prefix = 0;
		Map<Integer, ListNode> m = new HashMap<>();
		while (cur != null) {
			prefix += cur.val;
			if (m.containsKey(prefix)) {
				cur = m.get(prefix).next;
				int p = prefix + cur.val;
				while (p != prefix) {
					m.remove(p);
					cur = cur.next;
					p += cur.val;
				}
				m.get(prefix).next = cur.next;
			} else {
				m.put(prefix, cur);
			}
			cur = cur.next;
		}
		return dummy.next;
	}
}
