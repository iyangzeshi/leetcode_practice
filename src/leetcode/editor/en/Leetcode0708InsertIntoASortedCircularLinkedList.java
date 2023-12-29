//Given a Circular Linked List node, which is sorted in non-descending order, 
//write a function to insert a value insertVal into the list such that it remains a 
//sorted circular list. The given node can be a reference to any single node in 
//the list and may not necessarily be the smallest value in the circular list. 
//
// If there are multiple suitable places for insertion, you may choose any 
//place to insert the new value. After the insertion, the circular list should remain 
//sorted. 
//
// If the list is empty (i.e., the given node is null), you should create a new 
//single circular list and return the reference to that single node. Otherwise, 
//you should return the originally given node. 
//
// 
// Example 1: 
//
// 
// 
//Input: head = [3,4,1], insertVal = 2
//Output: [3,4,1,2]
//Explanation: In the figure above, there is a sorted circular list of three 
//elements. You are given a reference to the node with value 3, and we need to 
//insert 2 into the list. The new node should be inserted between node 1 and node 3. 
//After the insertion, the list should look like this, and we should still return 
//node 3.
// 
//
//
//
//
// Example 2: 
//
// 
//Input: head = [], insertVal = 1
//Output: [1]
//Explanation: The list is empty (given head is null). We create a new single 
//circular list and return the reference to that single node.
// 
//
// Example 3: 
//
// 
//Input: head = [1], insertVal = 0
//Output: [1,0]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 5 * 10⁴]. 
// -10⁶ <= Node.val, insertVal <= 10⁶ 
// 
//
// Related Topics Linked List 👍 1143 👎 738


package leetcode.editor.en;

// 2023-12-28 17:28:00
// Jesse Yang

public class Leetcode0708InsertIntoASortedCircularLinkedList{
    // Java: insert-into-a-sorted-circular-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0708InsertIntoASortedCircularLinkedList().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
class Node {
    
    public int val;
    public Node next;
    
    public Node() {
    }
    
    public Node(int _val) {
        val = _val;
    }
    
    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
    
}
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/
// 2 pointers: T(n) = O(n), S(n) = O(n)
/*
3 case for the inserted pointer
1. it is inserted in the increasing part, which means the previous <= inserted one <= next
2.1 it is smallest, inserted before the smallest of the increasing part
2.2 it is largest, inserted after the largest of the increasing part
3. all elements are equal, insert it anywhere

we do not know the location in the linked list, whether it is the head of end or the middle part
 */
class Solution {
    public Node insert(Node head, int insertVal) {
        // corner case
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        } else if (head.next == head) {
            insertNode(insertVal, head, head);
            return head;
        }
        
        //edge case, all duplicates
        Node cur = head.next;
        boolean allEqual = true;
        while (cur != head) {
            if (cur.val != head.val) { // not all node are equal
                allEqual = false;
                break;
            }
            cur = cur.next;
        }
        if (allEqual) {
            insertNode(insertVal, cur, cur.next);
            return head;
        }
        
        // general case, not all nodes equal, size of LinkedList >= 2
        Node prev = head;
        cur = head.next;
        Node next = cur.next;
        do {
            if (cur.val <= insertVal && insertVal <= next.val) {
                insertNode(insertVal, cur, next);
                return head;
            } else if (prev.val <= cur.val && cur.val > next.val) {
                if (insertVal > cur.val || insertVal < next.val) {
                    insertNode(insertVal, cur, next);
                    return head;
                }
            }
            prev = cur;
            cur = next;
            next = next.next;
        } while (prev != head);
        
        // insertNode(insertVal, cur, next);
        return head;
    }
    
    private void insertNode(int insertVal, Node cur, Node next) {
        Node node = new Node(insertVal);
        cur.next = node;
        node.next = next;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}