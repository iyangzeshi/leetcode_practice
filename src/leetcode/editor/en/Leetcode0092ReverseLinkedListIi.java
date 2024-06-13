//Reverse a linked list from position m to n. Do it in one-pass. 
//
// Note: 1 ≤ m ≤ n ≤ length of list. 
//
// Example: 
//
// 
//Input: 1->2->3->4->5->NULL, m = 2, n = 4
//Output: 1->4->3->2->5->NULL
// 
// Related Topics Linked List 
// 👍 2470 👎 145

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:30:12
// Jesse Yang
public class Leetcode0092ReverseLinkedListIi{
    // Java: reverse-linked-list-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0092ReverseLinkedListIi().new Solution();
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
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode prev = null;
        // find mth ListNode
        for (int i = 0; i < m - 1; i++) {
            prev = cur;
            cur = cur.next;
        }
        if (prev != null) {
            prev.next = reverseByN(cur, n - m + 1);
            return head;
        } else {
            return reverseByN(head, n - m + 1);
        }
    }
    
    /*
    reverse n node from ListNode head,
    n is the length of ListNodes need reverse (including start end end)
     */
    private ListNode reverseByN(ListNode head, int n) {
        if (head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = null;
        ListNode prev = null;
        for (int i = 0; i < n; i++) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head.next = next;
        
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: find the mth ListNode, and reverse following n-m nodes, T(n) = O(n), S(n) = O(1)
// Solution 1_1: without dummy node
// 0 ms,击败了100.00% 的Java用户, 36.6 MB,击败了48.56% 的Java用户
class Solution1_1 {
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode prev = null;
        // find mth ListNode
        for (int i = 0; i < m - 1; i++) {
            prev = cur;
            cur = cur.next;
        }
        if (prev != null) {
            prev.next = reverseByN(cur, n - m + 1);
            return head;
        } else {
            return reverseByN(head, n - m + 1);
        }
    }
    
    /*
    reverse n node from ListNode head,
    n is the length of ListNodes need reverse (including start end end)
     */
    private ListNode reverseByN(ListNode head, int n) {
        if (head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = null;
        ListNode prev = null;
        for (int i = 0; i < n; i++) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head.next = next;
        
        return prev;
    }
}

// SOlution 1_2 with dummy node
// 0 ms,击败了100.00% 的Java用户, 36.5 MB,击败了80.17% 的Java用户
class Solution1_2 {
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        ListNode cur = head;
        // find mth ListNode
        for (int i = 0; i < m - 1; i++) {
            prev = cur;
            cur = cur.next;
        }
        prev.next = reverseByN(cur, n - m + 1);
        return dummy.next;
    }
    
    /*
    reverse n node from ListNode head,
    n is the length of ListNodes need reverse (including start end end)
     */
    private ListNode reverseByN(ListNode head, int n) {
        if (head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = null;
        ListNode prev = null;
        for (int i = 0; i < n; i++) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head.next = next;
        
        return prev;
    }
}
}