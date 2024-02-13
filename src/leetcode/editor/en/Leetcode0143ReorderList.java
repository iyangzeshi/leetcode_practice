//Given a singly linked list L: L0→L1→…→Ln-1→Ln, 
//reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// Example 1: 
//
// 
//Given 1->2->3->4, reorder it to 1->4->2->3. 
//
// Example 2: 
//
// 
//Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
// 
// Related Topics Linked List 
// 👍 1991 👎 118

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:35:48
// Zeshi Yang
public class Leetcode0143ReorderList{
    // Java: reorder-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0143ReorderList().new Solution();
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
/*
思路：
找到原来list的中点，把前面一半和后面一半断开，后面一半reverse，再和前面一半交替merge就得到结果了

推理思路：
从       1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
变成     1 -> 6 -> 2 -> 5 -> 3 - > 4 -> null

如果有两个List
list 1: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null 和
list 2: 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> null

那只要把两个List 交替merge一下就行了，而且只要保留merge结果的前面一半
我们发现list 2其实就是list 1 的reverse
而且结果里面其实只用了list1和list2的前面一半，

而且其实只要把list 1 的前面一半和 list2的前面那一半merge一下，return出来就行了

所以只要找到原来list的中点，把前面一半和后面一半断开，后面一半reverse，再和前面一半交替merge就得到结果了
 */
class Solution {
    
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        /*
        fast.next == null means fast is the last element
        fast.next.next == null means fast is the last but 1 element
         */
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode nextHead = slow.next;
        slow.next = null;
        ListNode head1 = head;
        ListNode head2 = reverse(nextHead);
        merge(head1, head2);
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }
    
    // head1.length = head2.length or head2.length + 1
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = head1;
        ListNode cur = head1;
        head1 = head1.next;
        // 每次执行完一次循环，结果变成[0, head1), 和[0, head2), 每次先把head1和head2加进去，再更新head1，head2.
        while (head1 != null && head2 != null) {
            // connect head1 then update head1
            cur.next = head2;
            head2 = head2.next;
            cur  = cur.next;
            
            // connect head2 then update head2
            cur.next = head1;
            head1 = head1.next;
            cur = cur.next;
        }
        if (head2 != null) {
            cur.next = head2;
        }
        return head;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
}