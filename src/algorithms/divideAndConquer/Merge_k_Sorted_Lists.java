package algorithms.divideAndConquer;

import algorithms.linkedList.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Yangxiao Wang on 2019-07-10
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class Merge_k_Sorted_Lists {
	
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		int n = lists.length;
		int min = -1;
		for (int i = 0; i < n; i++) {
			if (lists[i] == null) continue;
			if (min == -1) min = i;
			else min = lists[i].val < lists[min].val ? i : min;
		}
		if (min == -1) return null;
		ListNode root = lists[min];
		lists[min] = lists[min].next;
		ListNode tmp = root;
		while (true) {
			min = -1;
			for (int i = 0; i < n; i++) {
				if (lists[i] == null) continue;
				if (min == -1) min = i;
				else min = lists[i].val < lists[min].val ? i : min;
			}
			if (min == -1) break;
			tmp.next = lists[min];
			tmp = tmp.next;
			lists[min] = lists[min].next;
		}
		
		return root;
	}
	
	public ListNode mergeKLists_heap(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		
		ListNode root = new ListNode(0);
		ListNode tmp = root;
		
		PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		
		for (ListNode node : lists) if (node != null) q.add(node);
		while (!q.isEmpty()) {
			tmp.next = q.poll();
			tmp = tmp.next;
			if (tmp.next != null) q.add(tmp.next);
		}
		
		return root.next;
	}
	
	public ListNode mergeKLists_merge(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		return helper(lists, 0, lists.length - 1);
	}
	
	private ListNode helper(ListNode[] lists, int i, int j) {
		if (i >= j) return lists[i];
		int mid = i + (j - i) / 2;
		ListNode left = helper(lists, i, mid);
		ListNode right = helper(lists, mid + 1, j);
		return mergeTwoList(left, right);
	}
	
	private ListNode mergeTwoList(ListNode list1, ListNode list2) {
		ListNode root = new ListNode(0);
		ListNode tmp = root;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				tmp.next = list1;
				list1 = list1.next;
			} else {
				tmp.next = list2;
				list2 = list2.next;
			}
			
			tmp = tmp.next;
		}
		
		if (list1 != null) tmp.next = list1;
		if (list2 != null) tmp.next = list2;
		return root.next;
	}
}
