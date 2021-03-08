//Given a singly linked list L: L0→L1→…→Ln-1→Ln, 
//reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// Example 1: 
//
// 
//Given 1->2->3->4, reorder it to 1->4->2->3. 
//
// Example 2: 
//
// 
//Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
// 
// Related Topics Linked List 
// 👍 1991 👎 118

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:35:48
// Zeshi Yang
public class Leetcode0143ReorderList{
    // Java: reorder-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0143ReorderList().new Solution();
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
    
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = slow;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = pre;
        
        ListNode temp = slow.next;
        slow.next = null;
        ListNode head1 = head;
        ListNode head2 = reverse(temp);
        ListNode dummy = new ListNode(0);
        merge(head1, head2);
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode post = head;
        while (cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = head1;
        ListNode cur = head1;
        head1 = head1.next;
        cur.next = head2;
        cur = cur.next;
        
        head2 = head2.next;
        while (head1 != null && head2 != null) {
            cur.next = head1;
            head1 = head1.next;
            cur = cur.next;
            cur.next = head2;
            head2 = head2.next;
            cur = cur.next;
        }
        if (head1 == null) {
            cur.next = head2;
        } else {
            cur.next = head1;
        }
        return head;
        // dummy.next = null
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
}