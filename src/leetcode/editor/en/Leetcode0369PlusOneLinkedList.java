//Given a non-negative integer represented as non-empty a singly linked list of 
//digits, plus one to the integer. 
//
// You may assume the integer do not contain any leading zero, except the number
// 0 itself. 
//
// The digits are stored such that the most significant digit is at the head of 
//the list. 
//
// 
// Example : 
//
// 
//Input: [1,2,3]
//Output: [1,2,4]
// 
// 
// Related Topics Linked List 
// 👍 456 👎 30

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 12:11:20
// Zeshi Yang
public class Leetcode0369PlusOneLinkedList{
    // Java: plus-one-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0369PlusOneLinkedList().new Solution();
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
    public ListNode plusOne(ListNode head) {
        // corner case
        if (head == null) {
            return null;
        }

        // general case
        ListNode dummy = new ListNode(0);

        dummy.next = head;
        ListNode reverseHead = reverseList(head);
        addOne(reverseHead);

        return reverseList(reverseHead);
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode post;

        while (cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }

    private void addOne(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        int carry = 1;
        while (cur != null) {
            int sum = cur.val + carry;
            carry = sum >= 10 ? 1 : 0;
            if (sum < 10) {
                cur.val = sum;
                break;
            } else {
                cur.val = 0;
                pre = cur;
                cur = cur.next;
            }
        }
        if (carry == 1) {
            pre.next = new ListNode(1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}