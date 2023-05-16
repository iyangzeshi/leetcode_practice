//Given a linked list, return the node where the cycle begins. If there is no cy
//cle, return null. 
//
// To represent a cycle in the given linked list, we use an integer pos which re
//presents the position (0-indexed) in the linked list where tail connects to. If 
//pos is -1, then there is no cycle in the linked list. 
//
// Note: Do not modify the linked list. 
//
// 
//
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
// 
//
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
// 
//
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
// 
//
// 
//
// 
//
// Follow-up: 
//Can you solve it without using extra space? 
// Related Topics Linked List Two Pointers 
// 👍 2838 👎 224

package leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;
import leetcode.editor.ListNode;

// 2020-08-04 11:35:47
// Zeshi Yang
public class Leetcode0142LinkedListCycleIi{
    // Java: linked-list-cycle-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0142LinkedListCycleIi().new Solution();
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
public class Solution {
    
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        slow = slow.next;
        fast = fast.next.next;
        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        return findCycleStart(slow, head);
    }
    
    private ListNode findCycleStart(ListNode slow, ListNode head) {
        while (head != slow) {
            slow = slow.next;
            head = head.next;
        }
        return slow;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: HashSet, T(n) = O(n), S(n) = Θ(n)
// 4 ms,击败了13.83% 的Java用户, 39.9 MB,击败了20.82% 的Java用户
public class Solution1 {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) {
                return head;
            }
            visited.add(head);
            head = head.next;
        }
        return null;
    }
}

// Solution 2: slow and fast pointer, T(n) = O(n), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户，39 MB,击败了67.12% 的Java用户
/*
从起点开始slow每次走一步，fast每次走两步，他们一定会
设起点到cycle的起点长度为A
cycle的周长为B
cycle的起点到相遇点长度为x
则 2*(A + x) = A + B + x,
推出 A + x = B

 */
public class Solution2 {
    
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        slow = slow.next;
        fast = fast.next.next;
        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        return findCycleStart(slow, head);
    }
    
    private ListNode findCycleStart(ListNode slow, ListNode head) {
        while (head != slow) {
            slow = slow.next;
            head = head.next;
        }
        return slow;
    }
    
}

// follow up: find the length of the cycle
class FindCycleLength {
    
    public int cycleLength(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return 0;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return getLength(slow);
            }
        }
        return 0;
    }
    
    private int getLength(ListNode slow) {
        ListNode cur = slow.next;
        int len = 1;
        while (cur != slow) {
            cur = cur.next;
            len++;
        }
        return len;
    }
    
}

}