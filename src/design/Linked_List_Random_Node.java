package design;

import algorithms.linkedList.ListNode;

import java.util.Random;

/**
 * @author Yangxiao Wang on 2019-06-27
 */
public class Linked_List_Random_Node {
	
	/**
	 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
		
		ListNode head;
		Random random;
		
		/**
		 * @param head The linked list's head. Note that the head is guaranteed to be not null, so it contains at least
		 *             one node.
		 */
		public Solution(ListNode head) {
			this.head = head;
			random = new Random();
		}
		
		/**
		 * Returns a random node's value.
		 */
		public int getRandom() {
			ListNode cur = head;
			int val = cur.val;
			for (int i = 1; cur.next != null; i++) {
				cur = cur.next;
				val = (random.nextInt(i + 1) == i) ? cur.val : val;
			}
			
			return val;
		}
	}
	
	/**
	 * Your Solution object will be instantiated and called as such:
	 * Solution obj = new Solution(head);
	 * int param_1 = obj.getRandom();
	 */
}
