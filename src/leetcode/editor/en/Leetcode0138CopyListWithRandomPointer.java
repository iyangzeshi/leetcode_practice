//A linked list is given such that each node contains an additional random point
//er which could point to any node in the list or null. 
//
// Return a deep copy of the list. 
//
// The Linked List is represented in the input/output as a list of n nodes. Each
// node is represented as a pair of [val, random_index] where: 
//
// 
// val: an integer representing Node.val 
// random_index: the index of the node (range from 0 to n-1) where random pointe
//r points to, or null if it does not point to any node. 
// 
//
// 
// Example 1: 
//
// 
//Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// Example 2: 
//
// 
//Input: head = [[1,1],[2,1]]
//Output: [[1,1],[2,1]]
// 
//
// Example 3: 
//
// 
//
// 
//Input: head = [[3,null],[3,0],[3,null]]
//Output: [[3,null],[3,0],[3,null]]
// 
//
// Example 4: 
//
// 
//Input: head = []
//Output: []
//Explanation: Given linked list is empty (null pointer), so return null.
// 
//
// 
// Constraints: 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random is null or pointing to a node in the linked list. 
// Number of Nodes will not exceed 1000. 
// 
// Related Topics Hash Table Linked List 
// 👍 3343 👎 671

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 14:07:52
// Zeshi Yang
public class Leetcode0138CopyListWithRandomPointer{
    // Java: copy-list-with-random-pointer
    public static void main(String[] args) {
        Solution sol = new Leetcode0138CopyListWithRandomPointer().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Definition for a Node.
/*
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    
    public Node copyRandomList(Node head) {
        // corner case
        if (head == null) {
            return null;
        }
        
        HashMap<Node, Node> map = new HashMap<>(); // original node to copy node
        Node cur1 = head;
    
        Node prev2 = new Node(-1);
        Node cur2;
        
        while(cur1 != null) {
            int val = cur1.val;
            cur2 = map.computeIfAbsent(cur1, k -> new Node(val));
            prev2.next = cur2;
            
            if (cur1.random != null) {
                Node random1 = cur1.random;
                int randomVal = random1.val;
                cur2.random = map.computeIfAbsent(random1, k-> new Node(randomVal));
            }
            
            cur1 = cur1.next;
            prev2 = cur2;
        }
        return map.get(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/** 面试的时候用Solution 1或者Solution 2 **/

// Solution 1: recursion(DFS), T(n) = O(n), S(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 39.1 MB,击败了25.91% 的Java用户
class Solution1 {

    HashMap<Node, Node> map = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        // corner case
        if (head == null) {
            return null;
        }

        // general case
        if (map.containsKey(head)) {
            return map.get(head);
        }

        Node node = new Node(head.val);
        map.put(head, node);

        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
}

// Solution 2: iteration, 1 pass by hashMap, T(n) = O(n), S(n) = O(n)
// 2 ms,击败了9.04% 的Java用户, 39 MB,击败了34.51% 的Java用户
class Solution2 {

    public Node copyRandomList(Node head) {
        // corner case
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>(); // original node to copy node
        Node cur1 = head;
    
        Node prev2 = new Node(-1);
        Node cur2;

        while(cur1 != null) {
            int val = cur1.val;
            cur2 = map.computeIfAbsent(cur1, k -> new Node(val));
            prev2.next = cur2;
            
            if (cur1.random != null) {
                Node random1 = cur1.random;
                int randomVal = random1.val;
                cur2.random = map.computeIfAbsent(random1, k-> new Node(randomVal));
            }
            
            cur1 = cur1.next;
            prev2 = cur2;
        }
        return map.get(head);
    }
}

// Solution 3: iteration, 3 pass by insert them to original list and separate them
// 0 ms,击败了100.00% 的Java用户, 38.3 MB,击败了94.80% 的Java用户
class Solution3 {

    public Node copyRandomList(Node head) {
        // corner case
        if (head == null) {
            return null;
        }

        // general case
        // 1st pass to copy the nodes and connect them with next pointers
        Node head1 = head;
        Node cur1 = head1;
        Node cur2;
        while(cur1 != null) {
            cur2 = new Node(cur1.val);
            cur2.next = cur1.next;
            
            cur1.next = cur2;
            cur1 = cur2.next;
        }
        Node head2 = head1.next;

        // 2nd pass to connect them with random pointers
        cur1 = head1;
        cur2 = head2;
        while (cur1 != null) {
            cur2 = cur1.next;
            cur2.random = (cur1.random != null ? cur1.random.next : null);
            cur1 = cur1.next.next;
        }

        // 3rd pass to separate copied node and original nodes
        cur1 = head1;
        cur2 = head2;
        while (cur1 != null) {
            cur1.next = cur2.next;
            if (cur2.next != null && cur2.next.next != null) {
                cur2.next = cur2.next.next;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return head2;
    }
}


private class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
}