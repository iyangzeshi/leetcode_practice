//Sort a linked list in O(n log n) time using constant space complexity. 
//
// Example 1: 
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
// Related Topics Linked List Sort 
// 👍 2838 👎 133

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:36:04
// Zeshi Yang
public class Leetcode0148SortList{
    // Java: sort-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0148SortList().new Solution();
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
    public ListNode sortList(ListNode head) {
        //base cases
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = findMid(head);
        ListNode nextHead = prev.next;
        prev.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(nextHead);
        return merge(head1, head2);
        
    }
    
    /*
    to find the the Node before the mid one
    * ex 1→ 2, return 1
    * ex 1→ 2→ 3, return 1
    * ex 1→ 2→ 3→ 4, return 2
    */
    private ListNode findMid(ListNode node) {
        ListNode pre = node;
        ListNode slow = node;
        ListNode fast = node;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return (fast == null ? pre: slow);
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        // edge case
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = (head1 != null ? head1: head2);
        ListNode result = dummy.next;
        dummy.next = null;
        return result;
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)
/* 面试的时候，用Solution 1 */

// Solution 1: top down then bottom up merge sort
// T(n) = O(nlg(n)), O(lg(n))
// :6 ms,击败了85.28% 的Java用户, 48.3 MB,击败了19.62% 的Java用户
class Solution1 {
    public ListNode sortList(ListNode head) {
        //base cases
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = findMid(head);
        ListNode nextHead = prev.next;
        prev.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(nextHead);
        return merge(head1, head2);
        
    }
    
    /*
    to find the the Node before the mid one
    * ex 1→ 2, return 1
    * ex 1→ 2→ 3, return 1
    * ex 1→ 2→ 3→ 4, return 2
    */
    private ListNode findMid(ListNode node) {
        ListNode pre = node;
        ListNode slow = node;
        ListNode fast = node;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return (fast == null ? pre: slow);
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        // edge case
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = (head1 != null ? head1: head2);
        ListNode result = dummy.next;
        dummy.next = null;
        return result;
    }
    
}

// Solution 2: split down to the 1 size and then bottom up merge sort
// T(n) = O(nlg(n)), O(lg(n))
// 14 ms,击败了11.10% 的Java用户, 53.5 MB,击败了5.59% 的Java用户
class Solution2 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 获取链表长度
        int len = listNodeLength(head);
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode[] tail = new ListNode[1];
        
        // 循环 log n 次
        for (int size = 1; size < len; size <<= 1) {
            ListNode prev = dummyHead;
            ListNode cur = dummyHead.next;
            // 循环 n 次
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, size);
                cur = split(right, size);
                //after merge, tail is the end of the merged List
                prev.next = mergeTwoLists(left, right, tail);
                prev = tail[0];
            }
        }
        head = dummyHead.next;
        dummyHead.next = null;
        return head;
    }
    
    // 根据步长分隔链表
    private ListNode split(ListNode head, int step) {
        // corner case
        if (head == null) {
            return null;
        }
        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null;
        return right;
    }
    
    // 获取链表的长度
    private int listNodeLength(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
    
    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode node1, ListNode node2, ListNode[] tail) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        cur.next = (node1 != null ? node1 : node2);
        while (cur.next != null) { // cur will go to the end of the merged List
            cur = cur.next;
        }
        tail[0] = cur; // tail[0] is the end of the merged List
        cur = dummyHead.next;
        dummyHead.next = null;
        return cur;
    }
}

}