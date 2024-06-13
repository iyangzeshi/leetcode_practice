package leetcode.editor;

/**
 * @Program: leetcode_practice
 * @ClassName: ListNode
 * @Description:
 * @Author: Jesse Yang
 * @Date: 2020-07-09 20:53
 */
public class ListNode {

	public int val;
	public ListNode prev;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
	
	public ListNode(int val, ListNode prev, ListNode next) {
		this.val = val;
		this.prev = prev;
		this.next = next;
	}
	
	@Override
	public String toString() {
		return String.valueOf(val);
	}
	
}