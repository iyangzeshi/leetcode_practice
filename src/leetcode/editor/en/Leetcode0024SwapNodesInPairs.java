//Given a linked list, swap every two adjacent nodes and return its head. 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// 
//
// Example: 
//
// 
//Given 1->2->3->4, you should return the list as 2->1->4->3.
// 
// Related Topics Linked List 
// 👍 2389 👎 172

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:12:29
// Zeshi Yang
public class Leetcode0024SwapNodesInPairs{
    // Java: swap-nodes-in-pairs
    public static void main(String[] args) {
        Solution sol = new Leetcode0024SwapNodesInPairs().new Solution();
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
/*
iteration
dummy  ->   1  ->   2   ->  3  ->   4 ->    null
prev   ->  cur1 ->  cur2 -> next

dummy  ->   2  ->   1   ->  3  ->   4 ->    null
                    prev-> cur1 -> cur2 ->  next
 */
class Solution {
    
    public ListNode swapPairs(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode cur1 = head;
        
        while (cur1 != null && cur1.next != null) {
            ListNode cur2 = cur1.next;
            ListNode next = cur2.next;
            
            // swap
            prev.next = cur2;
            cur2.next = cur1;
            cur1.next = next;
            
            // move forward the points
            prev = prev.next.next;
            cur1 = prev.next;
        }
        head = dummy.next;
        dummy.next = null;
        return head;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}