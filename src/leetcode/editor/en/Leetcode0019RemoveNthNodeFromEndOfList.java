//Given a linked list, remove the n-th node from the end of list and return its 
//head. 
//
// Example: 
//
// 
//Given linked list: 1->2->3->4->5, and n = 2.
//
//After removing the second node from the end, the linked list becomes 1->2->3->
//5.
// 
//
// Note: 
//
// Given n will always be valid. 
//
// Follow up: 
//
// Could you do this in one pass? 
// Related Topics Linked List Two Pointers 
// 👍 3450 👎 233

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-07-26 14:00:03
// Zeshi Yang
public class Leetcode0019RemoveNthNodeFromEndOfList {
    
    // Java: remove-nth-node-from-end-of-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0019RemoveNthNodeFromEndOfList().new Solution();
        // TO TEST
        ListNode listNode = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        ListNode res = sol.removeNthFromEnd(listNode, 2);
        System.out.println(res.val);
    }
//leetcode submit region begin(Prohibit modification and deletion)
    
    /**
     * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode()
     * {} ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val;
     * this.next = next; } }
     */
// Solution 2: T(n) = O(n), S(n) = O(1)
/*
dummy node
let the fast pointers to go n steps from beginning
let slow = head
let slow and fast pointers to go together 1 step by step until fast reach to the last node(not null)
let slow points to slow.next.next
 */
class Solution {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        // make fast n steps ahead of slow
        for (int i = 0; i < n && fast.next != null; i++) {
            fast = fast.next;
        }
        
        while (fast.next != null) { // make fast the last node
            slow = slow.next;
            fast = fast.next;
        }
        
        slow.next = slow.next.next;
        return dummy.next;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n), S(n) = O(1)
/*
let the fast pointers to go n steps from beginning
let slow = head
let slow and fast pointers to go together 1 step by step until fast reach to the last node(not null)
let slow points to slow.next.next
 */
class Solution1 {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head; // the Node before the node being cut
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // corner case
        if (fast == null) {
            return head.next;
        }
        
        // general case
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode remove = slow.next;
        slow.next = remove.next;
        remove.next = null;
        return head;
    }
    
}
    
// Solution 2: T(n) = O(n), S(n) = O(1)
/*
dummy node
let the fast pointers to go n steps from beginning
let slow = head
let slow and fast pointers to go together 1 step by step until fast reach to the last node(not null)
let slow points to slow.next.next
 */
class Solution2 {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n && fast != null; i++) {
            fast = fast.next;
        }
        
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }
    
}
    
}