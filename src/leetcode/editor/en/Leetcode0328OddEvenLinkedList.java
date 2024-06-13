//Given a singly linked list, group all odd nodes together followed by the even 
//nodes. Please note here we are talking about the node number and not the value i
//n the nodes. 
//
// You should try to do it in place. The program should run in O(1) space comple
//xity and O(nodes) time complexity. 
//
// Example 1: 
//
// 
//Input: 1->2->3->4->5->NULL
//Output: 1->3->5->2->4->NULL
// 
//
// Example 2: 
//
// 
//Input: 2->1->3->5->6->4->7->NULL
//Output: 2->3->6->7->1->5->4->NULL
// 
//
// 
// Constraints: 
//
// 
// The relative order inside both the even and odd groups should remain as it wa
//s in the input. 
// The first node is considered odd, the second node even and so on ... 
// The length of the linked list is between [0, 10^4]. 
// 
// Related Topics Linked List 
// 👍 2003 👎 303

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 12:09:47
// Jesse Yang
public class Leetcode0328OddEvenLinkedList{
    // Java: odd-even-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0328OddEvenLinkedList().new Solution();
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode head1 = head;
        ListNode head2 = head.next;
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while (cur2 != null && cur2.next != null) {
            cur1.next = cur1.next.next;
            cur2.next = cur2.next.next;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        cur1.next = head2;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}