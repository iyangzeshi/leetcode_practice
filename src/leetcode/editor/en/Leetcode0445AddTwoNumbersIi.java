//You are given two non-empty linked lists representing two non-negative integer
//s. The most significant digit comes first and each of their nodes contain a sing
//le digit. Add the two numbers and return it as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself. 
//
// Follow up: 
//What if you cannot modify the input lists? In other words, reversing the lists
// is not allowed.
// 
//
// 
//Example:
// 
//Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 8 -> 0 -> 7
// 
// Related Topics Linked List 
// 👍 1623 👎 162

package leetcode.editor.en;

import java.util.Stack;
import leetcode.editor.ListNode;

// 2020-09-14 15:28:31
// Zeshi Yang
public class Leetcode0445AddTwoNumbersIi{
    // Java: add-two-numbers-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0445AddTwoNumbersIi().new Solution();
        // TO TEST
        ListNode l1 = buildList(new int[]{7, 2, 4, 3});
        ListNode l2 = buildList(new int[]   {5, 6, 4});
        ListNode head = sol.addTwoNumbers(l1, l2);
        printListNode(head);
    }
    
    private static void printListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (head != null) {
            sb.append(head.val);
            sb.append(',');
            head = head.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        System.out.println(sb);
    }
    
    private static ListNode buildList(int[] nums) {
        ListNode following = null;
        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            ListNode cur = new ListNode(nums[i]);
            cur.next = following;
            following = cur;
        }
        return following;
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
// Solution 2: Stack, T(n) = O(n), S(n) = O(n)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null) {
            stack1.push(p1);
            p1 = p1.next;
        }
        while (p2 != null) {
            stack2.push(p2);
            p2 = p2.next;
        }
        ListNode dummy = new ListNode();
        int carry = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry == 1) {
            int sum = 0;
            if (!stack1.isEmpty()) {
                sum += stack1.pop().val;
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop().val;
            }
            sum += carry;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: head recursion with new class ListNodeWithCarry, T(n) = O(n), S(n) = O(n)
class Solution1 {
    class ListNodeWithCarry{
        ListNode listNode;
        int carry;
        
        public ListNodeWithCarry(int val, int carry) {
            listNode = new ListNode(val);
            this.carry = carry;
        }
        
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int diff = 0; // len of l1 - l2
        ListNode p1 = l1, p2 = l2;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                p1 = p1.next;
                diff++;
            }
            if (p2 != null) {
                p2 = p2.next;
                diff--;
            }
        }
        ListNodeWithCarry cur;
        if (diff < 0) {
            cur = addTwoNumbers(l2, l1, -diff);
        } else {
            cur = addTwoNumbers(l1, l2, diff);
        }
        ListNode head;
        if (cur.carry == 1) {
            head = new ListNode(1);
            head.next = cur.listNode;
        } else { // carry == 0
            head = cur.listNode;
        }
        return head;
    }
    
    // recursion
    // p1.length >= p2.length. diff >= 0
    private ListNodeWithCarry addTwoNumbers(ListNode p1, ListNode p2, int diff) {
        // base case
        if (p1.next == null && p2.next == null) {
            int value = p1.val + p2.val;
            int carry = value / 10;
            value %= 10;
            return new ListNodeWithCarry(value, carry);
        }
        ListNodeWithCarry following;
        if (diff > 0) { // diff > 0
            following = addTwoNumbers(p1.next, p2, diff - 1);
        } else { // diff == 0
            following = addTwoNumbers(p1.next, p2.next, diff);
        }
        ListNode followingNode = following.listNode;
        int carry = following.carry;
        int value = p1.val + carry;
        if (diff == 0) {
            value += p2.val;
        }
        int newCarry = value / 10;
        value %= 10;
        ListNodeWithCarry cur = new ListNodeWithCarry(value, newCarry);
        cur.listNode.next = followingNode;
        return cur;
    }
}

// Solution 2: Stack, T(n) = O(n), S(n) = O(n)
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null) {
            stack1.push(p1);
            p1 = p1.next;
        }
        while (p2 != null) {
            stack2.push(p2);
            p2 = p2.next;
        }
        ListNode dummy = new ListNode(); // the node before the current sum node
        int carry = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry == 1) {
            int sum = 0;
            if (!stack1.isEmpty()) {
                sum += stack1.pop().val;
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop().val;
            }
            sum += carry;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }
    
}

}