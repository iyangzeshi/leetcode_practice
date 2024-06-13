//Given a sorted linked list, delete all nodes that have duplicate numbers, leav
//ing only distinct numbers from the original list. 
//
// Return the linked list sorted as well. 
//
// Example 1: 
//
// 
//Input: 1->2->3->3->4->4->5
//Output: 1->2->5
// 
//
// Example 2: 
//
// 
//Input: 1->1->1->2->3
//Output: 2->3
// 
// Related Topics Linked List 
// 👍 2212 👎 116

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-12-16 15:29:14
// Jesse Yang
public class Leetcode0082RemoveDuplicatesFromSortedListIi{
    // Java: remove-duplicates-from-sorted-list-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0082RemoveDuplicatesFromSortedListIi().new Solution();
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
    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // 从头开始到pre都没有duplicates, pre.next到后面可能会有重复
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) { // 说明这里没有重复
                pre = cur;
            } else { // 先cur.next加进去，如果cur.next有重复，后面也会去重的
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n), S(n) = O(1)
/*
设置2个指针，pre和cur
pre表示没有重复的值的最后一个
每次遇到一个新的值，就把这个新的值接到pre.next， 然后检测这个值有没有重复，
    重复的话，就删掉
    不重复的话，就更新pre，并且继续接
 */
class Solution1 {
    
    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // 从头开始到pre都没有duplicates, pre.next到后面可能会有重复
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) { // 说明这里cur的值没有重复
                pre = cur;
            } else { // 先把cur.next加进去pre.next，如果cur.next有重复，后面也会去重的
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
// Solution 2: T(n) = O(n), S(n) = O(1)
/*
 每次遇到一个新的值，先检测这个值有没有重复，没有重复的话，加进去；
 如果有重复的话，就一直走到下一个新的值
*/
class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // 从头开始到pre都没有duplicates, pre.next到后面可能会有重复
        ListNode cur = head; // while循环结束一轮的时候，cur都是新的值的开头或者null
        ListNode following; // cur.next
    
        while (cur != null) {
            following = cur.next;
            if (following == null || following.val != cur.val) {
                pre.next = cur;
                cur = cur.next;
                pre = pre.next;
            } else { // following要么是null，要么和cur的val一样
                while (following != null && following.val == cur.val) {
                    following = following.next;
                }
                // following要么是一个新的值，要么是null
                cur = following;
                if (cur == null) {
                    pre.next = cur;
                }
            }
        }
        return dummy.next;
    }
}
}