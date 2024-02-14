//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order and each of their nodes contain a sing
//le digit. Add the two numbers and return it as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself. 
//
// Example: 
//
// 
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.
// 
// Related Topics Linked List Math 
// 👍 9202 👎 2312

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-09-14 12:35:38
// Zeshi Yang
public class Leetcode0002AddTwoNumbers{
    // Java: add-two-numbers
    public static void main(String[] args) {
        Solution sol = new Leetcode0002AddTwoNumbers().new Solution();
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
// T(n) = O(n), S(n) = O(1)
/*
idea: set a carry to check whether sum >= 10
do while loop to do the calculation until finishing iterating all the nodes and carry = 0
 */
class Solution {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry == 1) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int value = (num1 + num2 + carry) % 10;
            cur.next = new ListNode(value);
            carry = (num1 + num2 + carry) / 10;
            cur = cur.next;
            
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)



}