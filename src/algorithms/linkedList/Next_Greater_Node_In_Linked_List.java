package algorithms.linkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Yangxiao Wang on 5/4/2019
 */
public class Next_Greater_Node_In_Linked_List {
	
	public int[] nextLargerNodes(ListNode head) {
		List<Integer> res = new LinkedList<>();
		ListNode cur = head;
		while (cur != null) {
			ListNode tmp = cur.next;
			while (tmp != null && tmp.val <= cur.val) {
				tmp = tmp.next;
			}
			res.add(tmp == null ? 0 : tmp.val);
			cur = cur.next;
		}
		
		int n = res.size();
		int[] re = new int[n];
		int i = 0;
		for (int x : res) {
			re[i++] = x;
		}
		return re;
	}
	
	public int[] nextLargerNodes_Sol1(ListNode head) {
		if (head == null)
			return new int[0];
		ArrayList<Integer> A = new ArrayList<>();
		for (ListNode node = head; node != null; node = node.next)
			A.add(node.val);
		int[] res = new int[A.size()];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < A.size(); ++i) {
			while (!stack.isEmpty() && A.get(stack.peek()) < A.get(i))
				res[stack.pop()] = A.get(i);
			stack.push(i);
		}
		return res;
	}
	
	class Solution {
		int[] NLNres;
		
		public int[] nextLargerNodes(ListNode head) {
			if (head == null)
				return new int[0];
			NLNhelper(head, 0);
			return NLNres;
		}
		
		private ListNode NLNhelper(ListNode head, int length) {
			if (head == null) {
				NLNres = new int[length];
				return null;
			}
			ListNode next = NLNhelper(head.next, length + 1);
			while (next != null && next.val <= head.val)
				next = next.next;
			if (next != null)
				NLNres[length] = next.val;
			head.next = next;
			return head;
		}
	}
}
