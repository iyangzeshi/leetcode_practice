//Given a sorted linked list, delete all duplicates such that each element appea
//r only once. 
//
// Example 1: 
//
// 
//Input: 1->1->2
//Output: 1->2
// 
//
// Example 2: 
//
// 
//Input: 1->1->2->3->3
//Output: 1->2->3
// 
// Related Topics Linked List 
// 👍 1615 👎 114

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:29:08
// Zeshi Yang
public class Leetcode0083RemoveDuplicatesFromSortedList{
    // Java: remove-duplicates-from-sorted-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0083RemoveDuplicatesFromSortedList().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
  Definition for singly-linked list.
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        //corner case
        if (head == null || head.next == null) {
            return head;
        }
    
        ListNode slow = head;
        ListNode fast = head.next;
    
        while (fast != null) {
            if (slow.val == fast.val) {
                slow.next = fast.next;
                fast.next = null;
                fast = slow.next;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return head;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}