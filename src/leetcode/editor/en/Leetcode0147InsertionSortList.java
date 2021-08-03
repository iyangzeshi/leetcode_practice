//Sort a linked list using insertion sort. 
//
// 
// 
//
// 
//A graphical example of insertion sort. The partial sorted list (black) initial
//ly contains only the first element in the list. 
//With each iteration one element (red) is removed from the input data and inser
//ted in-place into the sorted list 
// 
//
// 
// 
//
// Algorithm of Insertion Sort: 
//
// 
// Insertion sort iterates, consuming one input element each repetition, and gro
//wing a sorted output list. 
// At each iteration, insertion sort removes one element from the input data, fi
//nds the location it belongs within the sorted list, and inserts it there. 
// It repeats until no input elements remain. 
// 
//
// 
//Example 1: 
//
// 
//Input: 4->2->1->3
//Output: 1->2->3->4
// 
//
// Example 2: 
//
// 
//Input: -1->5->3->4->0
//Output: -1->0->3->4->5
// 
// Related Topics Linked List Sort 
// 👍 626 👎 586

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-07-26 11:55:08
// Zeshi Yang
public class Leetcode0147InsertionSortList{
    // Java: insertion-sort-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0147InsertionSortList().new Solution();
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
    
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        
        dummy.next = head;
        ListNode cur = head;
        
        while (cur.next != null) {
            if (cur.val > cur.next.val) {
                // insert cur.next to the list
                ListNode insertNode = takeNode(cur, cur.next);
                insert(dummy, insertNode);
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    
    private ListNode takeNode(ListNode a, ListNode b) {
        a.next = b.next;
        b.next = null;
        return b;
    }
    
    private void insert(ListNode dummy, ListNode node) {
        while (dummy.next.val <= node.val) {
            dummy = dummy.next;
        }
        node.next = dummy.next;
        dummy.next = node;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}