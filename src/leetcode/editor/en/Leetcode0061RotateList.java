//Given a linked list, rotate the list to the right by k places, where k is non-
//negative. 
//
// Example 1: 
//
// 
//Input: 1->2->3->4->5->NULL, k = 2
//Output: 4->5->1->2->3->NULL
//Explanation:
//rotate 1 steps to the right: 5->1->2->3->4->NULL
//rotate 2 steps to the right: 4->5->1->2->3->NULL
// 
//
// Example 2: 
//
// 
//Input: 0->1->2->NULL, k = 4
//Output: 2->0->1->NULL
//Explanation:
//rotate 1 steps to the right: 2->0->1->NULL
//rotate 2 steps to the right: 1->2->0->NULL
//rotate 3 steps to the right: 0->1->2->NULL
//rotate 4 steps to the right: 2->0->1->NULL 
// Related Topics Linked List Two Pointers 
// 👍 1448 👎 1045

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-09-14 16:51:49
// Jesse Yang
public class Leetcode0061RotateList{
    // Java: rotate-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0061RotateList().new Solution();
        // TO TEST
        ListNode head = buildList(new int[] {1,2, 3, 4, 5});
        int k = 2;
        ListNode cur = sol.rotateRight(head, k);
        printListNode(cur);
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
// 0 ms
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        if (k % len == 0) { // 向右移动len的整数倍个数，相当于没有移动
            return head;
        }
        tail.next = head;
        k = len - k % len; // 向左边移动多少个单位
        ListNode newTail = head;
        for (int i = 0; i < k - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}