//Reverse a singly linked list. 
//
// Example: 
//
// 
//Input: 1->2->3->4->5->NULL
//Output: 5->4->3->2->1->NULL
// 
//
// Follow up: 
//
// A linked list can be reversed either iteratively or recursively. Could you im
//plement both? 
// Related Topics Linked List 
// 👍 4735 👎 91

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:55:15
// Jesse Yang
public class Leetcode0206ReverseLinkedList{
    // Java: reverse-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0206ReverseLinkedList().new Solution();
        // TO TEST
	    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
	    ListNode res = sol.reverseList(head);
		while(res != null) {
			System.out.println(res.toString());
		}
    
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// Solution 2_2: head recursion
class Solution {
	
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode tail = reverseList(head.next);
		head.next.next = head;
		head.next = null; // otherwise there will be a cycle between head and head.next
		return tail;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: iteration loop
class Solution1 {
    
    public ListNode reverseList(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    
}


// Solution 2_1: head recursion
class Solution2_1 {
    
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        head.next = null; // otherwise there will be a cycle between head and head.next
        return tail;
    }
    
}

// Solution 2_2: tail recursion
class Solution2_2 {
    
    public ListNode reverseList(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        return reverseList(head, next);
    }
    
    private ListNode reverseList(ListNode prev, ListNode cur) {
        ListNode next = cur.next;
        cur.next = prev;
        // base case
        if (next == null) {
            return cur;
        }
        return reverseList(cur, next);
    }
    
}
}