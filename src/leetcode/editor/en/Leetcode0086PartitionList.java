//Given a linked list and a value x, partition it such that all nodes less than 
//x come before nodes greater than or equal to x. 
//
// You should preserve the original relative order of the nodes in each of the t
//wo partitions. 
//
// Example: 
//
// 
//Input: head = 1->4->3->2->5->2, x = 3
//Output: 1->2->2->4->3->5
// 
// Related Topics Linked List Two Pointers 
// 👍 1341 👎 303

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:28:32
// Zeshi Yang
public class Leetcode0086PartitionList{
    // Java: partition-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0086PartitionList().new Solution();
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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                cur1.next = cur;
                cur1 = cur1.next;
            } else { //cur.val >= x
                cur2.next = cur;
                cur2= cur2.next;
            }
            cur = cur.next;
        }
        cur2.next = null;
        cur1.next = dummy2.next;
        return dummy1.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}