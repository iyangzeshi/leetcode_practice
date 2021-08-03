//Remove all elements from a linked list of integers that have value val. 
//
// Example: 
//
// 
//Input:  1->2->6->3->4->5->6, val = 6
//Output: 1->2->3->4->5
// 
// Related Topics Linked List 
// 👍 1819 👎 94

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:55:11
// Zeshi Yang
public class Leetcode0203RemoveLinkedListElements{
    // Java: remove-linked-list-elements
    public static void main(String[] args) {
        Solution sol = new Leetcode0203RemoveLinkedListElements().new Solution();
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
    
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(val + 1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            } else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return dummy.next;
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}