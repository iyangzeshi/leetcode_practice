//Given a linked list, determine if it has a cycle in it. 
//
// To represent a cycle in the given linked list, we use an integer pos which re
//presents the position (0-indexed) in the linked list where tail connects to. If 
//pos is -1, then there is no cycle in the linked list. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: true
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
// 
// 
//
// 
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: true
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
// 
// 
//
// 
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: false
//Explanation: There is no cycle in the linked list.
// 
// 
//
// 
//
// 
//
// Follow up: 
//
// Can you solve it using O(1) (i.e. constant) memory? 
// Related Topics Linked List Two Pointers 
// 👍 3019 👎 503

package leetcode.editor.en;

import leetcode.editor.ListNode;

import java.util.HashSet;
import java.util.Set;

// 2020-08-04 11:35:46
// Zeshi Yang
public class Leetcode0141LinkedListCycle{
    // Java: linked-list-cycle
    public static void main(String[] args) {
        Solution sol = new Leetcode0141LinkedListCycle().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// Solution 2: 2 pointers
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: HashSet
public class Solution1 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodeSeen = new HashSet<>();
        while (head != null) {
            if (nodeSeen.contains(head)) {
                return true;
            }
            else {
                nodeSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }
}

// Solution 2: 2 pointers
public class Solution2 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
}