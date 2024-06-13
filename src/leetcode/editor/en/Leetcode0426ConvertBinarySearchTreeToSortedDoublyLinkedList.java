//Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
// 
//
// You can think of the left and right pointers as synonymous to the predecessor
// and successor pointers in a doubly-linked list. For a circular doubly linked li
//st, the predecessor of the first element is the last element, and the successor 
//of the last element is the first element. 
//
// We want to do the transformation in place. After the transformation, the left
// pointer of the tree node should point to its predecessor, and the right pointer
// should point to its successor. You should return the pointer to the smallest el
//ement of the linked list. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [4,2,5,1,3]
//
//
//Output: [1,2,3,4,5]
//
//Explanation: The figure below shows the transformed BST. The solid line indica
//tes the successor relationship, while the dashed line means the predecessor rela
//tionship.
//
// 
//
// Example 2: 
//
// 
//Input: root = [2,1,3]
//Output: [1,2,3]
// 
//
// Example 3: 
//
// 
//Input: root = []
//Output: []
//Explanation: Input is an empty tree. Output is also an empty Linked List.
// 
//
// Example 4: 
//
// 
//Input: root = [1]
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// -1000 <= Node.val <= 1000 
// Node.left.val < Node.val < Node.right.val 
// All values of Node.val are unique. 
// 0 <= Number of Nodes <= 2000 
// 
// Related Topics Linked List Divide and Conquer Tree 
// 👍 915 👎 92

package leetcode.editor.en;

// 2020-07-26 13:09:29
// Jesse Yang
public class Leetcode0426ConvertBinarySearchTreeToSortedDoublyLinkedList{
    // Java: convert-binary-search-tree-to-sorted-doubly-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0426ConvertBinarySearchTreeToSortedDoublyLinkedList().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        Node leftHead = treeToDoublyList(root.left);
        Node rightHead = treeToDoublyList(root.right);

        root.left = root;
        root.right = root;

        return connect(connect(leftHead, root), rightHead);
    }
    //list1 and list2 must be circular doubly linked list
    private Node connect(Node list1, Node list2) {
        if (list2 == null)
            return list1;
        if (list1 == null)
            return list2;

        Node tail1 = list1.left;
        Node tail2 = list2.left;

        list1.left = tail2;
        tail2.right = list1;
        tail1.right = list2;
        list2.left = tail1;

        return list1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: similar to Solution 2,创建treeNode，连起来就好了


// Solution 2: 注意BST的性质，所以只要inorder遍历的时候改变指向位置就行了
class Solution2 {
    
    Node prev = null;
    Node head = null;
    
    public Node treeToDoublyList(Node root) {
        // corner case
        if (root == null) {
            return null;
        }
        
        inorder(root);
        
        prev.right = head;
        head.left = prev;
        
        return head;
    }
    
    private void inorder(Node root) {
        /*
         先找出base case，然后顺序是
         1. inorder left
         2. 连接node
         3. inorder right
         */
        // base case
        if (root == null) {
            return;
        }
        
        inorder(root.left);
        
        //do sth
        if (prev == null) {
            head = root;
        } else {
            prev.right = root;
        }
        root.left = prev;
        prev = root;
        
        inorder(root.right);
    }
}

// Solution 3: divide and conquer
class Solution3 {
    
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node leftHead = treeToDoublyList(root.left);
        Node rightHead = treeToDoublyList(root.right);
        
        root.left = root;
        root.right = root;
        
        return connect(connect(leftHead, root), rightHead);
    }
    
    //list1 and list2 must be circular doubly linked list
    private Node connect(Node list1, Node list2) {
        if (list2 == null) {
            return list1;
        }
        if (list1 == null) {
            return list2;
        }
        
        Node tail1 = list1.left;
        Node tail2 = list2.left;
        
        list1.left = tail2;
        tail2.right = list1;
        tail1.right = list2;
        list2.left = tail1;
        
        return list1;
    }
    
}

private class Node {
    
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

}