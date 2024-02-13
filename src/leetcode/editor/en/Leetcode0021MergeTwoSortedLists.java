//Merge two sorted linked lists and return it as a new sorted list. The new list
// should be made by splicing together the nodes of the first two lists. 
//
// Example: 
//
// 
//Input: 1->2->4, 1->3->4
//Output: 1->1->2->3->4->4
// 
// Related Topics Linked List 
// 👍 4505 👎 606

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:12:23
// Zeshi Yang
public class Leetcode0021MergeTwoSortedLists{
    // Java: merge-two-sorted-lists
    public static void main(String[] args) {
        Solution sol = new Leetcode0021MergeTwoSortedLists().new Solution();
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
// T(n) = O(n), S(n) = O(1), 2 pointers go further next by next
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // S1: iteration
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2: l1;
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}