//Given a non-empty, singly linked list with head node head, return a middle nod
//e of linked list. 
//
// If there are two middle nodes, return the second middle node. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: [1,2,3,4,5]
//Output: Node 3 from this list (Serialization: [3,4,5])
//The returned node has value 3.  (The judge's serialization of this node is [3,
//4,5]).
//Note that we returned a ListNode object res, such that:
//res.val = 3, res.next.val = 4, res.next.next.val = 5, and res.next.next.next =
// NULL.
// 
//
// 
// Example 2: 
//
// 
//Input: [1,2,3,4,5,6]
//Output: Node 4 from this list (Serialization: [4,5,6])
//Since the list has two middle nodes with values 3 and 4, we return the second 
//one.
// 
//
// 
//
// Note: 
//
// 
// The number of nodes in the given list will be between 1 and 100. 
// 
// 
// 
// Related Topics Linked List 
// 👍 1845 👎 67

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-12-13 17:18:06
// Zeshi Yang
public class Leetcode0876MiddleOfTheLinkedList{
    // Java: middle-of-the-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0876MiddleOfTheLinkedList().new Solution();
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
    public ListNode middleNode(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}