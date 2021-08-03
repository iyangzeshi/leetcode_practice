//Given an n-ary tree, return the preorder traversal of its nodes' values.
//
// Nary-Tree input serialization is represented in their level order traversal,
//each group of children is separated by the null value (See examples).
//
//
//
// Follow up:
//
// Recursive solution is trivial, could you do it iteratively?
//
//
// Example 1:
//
//
//
//
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [1,3,5,6,2,4]
//
//
// Example 2:
//
//
//
//
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null
//,12,null,13,null,null,14]
//Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
//
//
//
// Constraints:
//
//
// The height of the n-ary tree is less than or equal to 1000
// The total number of nodes is between [0, 10^4]
//
// Related Topics Tree
// 👍 692 👎 63

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2020-09-26 19:40:38
// Zeshi Yang
public class Leetcode0589NAryTreePreorderTraversal{
    // Java: n-ary-tree-preorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0589NAryTreePreorderTraversal().new Solution();
        // TO TEST
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        
        List<Node> children1 = new ArrayList<>();
        children1.add(node2);
        children1.add(node3);
        children1.add(node4);
    
        List<Node> children2 = new ArrayList<>();
        children2.add(node5);
        children2.add(node6);
        
        node1.children = children1;
        node2.children = children2;
        
        List<Integer> res = sol.preorder(node1);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
*/

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }
    
    private void preorder(Node root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        
        res.add(root.val);
        for (Node child: root.children) { preorder(child, res);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
private static class Node {
    
    public int val;
    public List<Node> children;
    
    public Node() {}
    
    public Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
    
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
}