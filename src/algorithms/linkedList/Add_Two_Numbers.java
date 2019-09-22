package algorithms.linkedList;

/**
 * @author Yangxiao on 4/9/2019.
 */
public class Add_Two_Numbers {
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int sum = 0;
		ListNode re = new ListNode(0);
		ListNode cur = re;
		while (l1 != null || l2 != null || sum > 0) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			sum = sum / 10;
		}
		return re.next;
	}
}
