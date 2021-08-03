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
public class Leetcode0019RemoveNthNodeFromEndOfList{
    // Java: remove-nth-node-from-end-of-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0019RemoveNthNodeFromEndOfList().new Solution();
        // TO TEST
        
        System.out.println();
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
class Solution {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head; // the Node before the node being cut
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
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
//leetcode submit region end(Prohibit modification and deletion)

}