package algorithms.math; /**
 * @author Yangxiao on 10/24/2018.
 */

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */

class Add_Two_Numbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int over = 0;
		ListNode root = new ListNode(0);
		ListNode cur = root;
		while (l1 != null || l2 != null) {
			int sum = 0;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			
			sum += over;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			over = sum < 10 ? 0 : 1;
		}
		
		if (over == 1) {
			cur.next = new ListNode(1);
		}
		
		return root.next;
	}
	
	public ListNode addTwoNumbers_Sol(ListNode l1, ListNode l2) {
		ListNode list = new ListNode(0);
		ListNode head = list;
		int sum = 0;
		while (l1 != null || l2 != null || sum != 0) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			list.next = new ListNode(sum % 10);
			sum = sum / 10;
			list = list.next;
		}
		
		return head.next;
	}
	
	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) { val = x; }
	 * }
	 */
	private class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) { val = x; }
	}
}
