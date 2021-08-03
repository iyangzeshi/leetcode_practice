//Merge k sorted linked lists and return it as one sorted list. Analyze and desc
//ribe its complexity. 
//
// Example: 
//
// 
//Input:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//Output: 1->1->2->3->4->4->5->6
// 
// Related Topics Linked List Divide and Conquer Heap 
// 👍 4957 👎 296

package leetcode.editor.en;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import leetcode.editor.ListNode;

// 2020-08-04 11:12:27
// Zeshi Yang
public class Leetcode0023MergeKSortedLists{
    // Java: merge-k-sorted-lists
    public static void main(String[] args) {
        Solution sol = new Leetcode0023MergeKSortedLists().new Solution();
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
    
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        int delta = 1;
        while (delta < len) {
            for (int i = 0; i + delta < len; i += 2 * delta) {
                lists[i] = merge2Lists(lists[i], lists[i + delta]);
            }
            delta *= 2;
        }
        return lists[0];
    }
    
    // no dummy node
    private ListNode merge2Lists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        
        ListNode head = null;
        ListNode cur = head;
        
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                if (head == null) {
                    head = h1;
                    cur = head;
                } else {
                    cur.next = h1;
                    cur = cur.next;
                }
                h1 = h1.next;
            } else {
                if (head == null) {
                    head = h2;
                    cur = head;
                } else {
                    cur.next = h2;
                    cur = cur.next;
                }
                h2 = h2.next;
            }
        }
        // post-processing
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }
        return head;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/** 面试的时候用Solution 1，如果需要O(1)空间复杂度的时候，再用Solution 2_3*/
// Solution 1: minheap
// T(n, k) = O(n * log(k)), S(n, k) = O(k)
// k: number of LinkedLists, n: number of nodes in final List
// 4 ms,击败了78.69% 的Java用户, 41.2 MB,击败了17.04% 的Java用户
class Solution1 {
    
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // general case
        ListNode head = null;
        ListNode cur = null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length,
                Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode temp = minHeap.poll();
            if (head == null) {
                head = temp;
                cur = temp;
            } else {
                cur.next = temp;
                cur = cur.next;
            }
            if (temp.next != null) {
                minHeap.offer(temp.next);
            }
        }
        return head;
    }
    
}

// Solution 2: repeat binary merge

// Solution 2_1: Queue, merge every 2 neighbors repeatedly
// T(n, k) = O(n * log(k)), S(n, k) = O(k)
// k: number of LinkedLists, n: number of nodes in final List
// 3 ms,击败了81.17% 的Java用户, 41.2 MB,击败了17.04% 的Java用户
class Solution2_1 {
    
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // general case
        Queue<ListNode> queue = new LinkedList<>();
        for (ListNode node : lists) {
            queue.offer(node);
        }
        while (queue.size() != 1) {
            ListNode node1 = queue.poll();
            ListNode node2 = queue.poll();
            ListNode node = merge2Lists(node1, node2);
            queue.offer(node);
        }
        return queue.poll();
    }
    
    // using dummy node
    private ListNode merge2Lists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }
        return dummy.next;
    }
    
}

// Solution 2_2: divide and conquer
// T(n, k) = O(n * log(k)), S(n, k) = O(log(k))
// k: number of LinkedLists, n: number of nodes in final List
// 1 ms,击败了100.00% 的Java用户, 41 MB,击败了30.93% 的Java用户
class Solution2_2 {
    
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return divideAndConquer(lists, 0, lists.length - 1);
    }
    
    private ListNode divideAndConquer(ListNode[] lists, int start, int end) {
        if (start >= end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = divideAndConquer(lists, start, mid);
        ListNode right = divideAndConquer(lists, mid + 1, end);
        return merge2Lists(left, right);
    }
    
    // using dummy node
    private ListNode merge2Lists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }
        return dummy.next;
    }
}

// Solution 2_3: in-place operation divide and conquer, merge every 2 neighbors repeatedly
// T(n, k) = O(n * log(k)), S(n, k) = O(1)
// k: number of LinkedLists, n: number of nodes in final List
// 1 ms,击败了100.00% 的Java用户, 40.8 MB,击败了39.00% 的Java用户
class Solution2_3 {
    
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        int delta = 1;
        while (delta < len) {
            for (int i = 0; i + delta < len; i += 2 * delta) {
                lists[i] = merge2Lists(lists[i], lists[i + delta]);
            }
            delta *= 2;
        }
        return lists[0];
    }
    
    // no dummy node
    private ListNode merge2Lists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        
        ListNode head = null;
        ListNode cur = head;
        
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                if (head == null) {
                    head = h1;
                    cur = head;
                } else {
                    cur.next = h1;
                    cur = cur.next;
                }
                h1 = h1.next;
            } else {
                if (head == null) {
                    head = h2;
                    cur = head;
                } else {
                    cur.next = h2;
                    cur = cur.next;
                }
                h2 = h2.next;
            }
        }
        // post-processing
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }
        return head;
    }
    
}
}