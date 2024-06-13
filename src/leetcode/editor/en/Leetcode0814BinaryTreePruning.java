//Given the root of a binary tree, return the same tree where every subtree (of 
//the given tree) not containing a 1 has been removed. 
//
// A subtree of a node node is node plus every node that is a descendant of 
//node. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,null,0,0,1]
//Output: [1,null,0,null,1]
//Explanation: 
//Only the red nodes satisfy the property "every subtree not containing a 1".
//The diagram on the right represents the answer.
// 
//
// Example 2: 
// 
// 
//Input: root = [1,0,1,0,0,0,1]
//Output: [1,null,1,null,1]
// 
//
// Example 3: 
// 
// 
//Input: root = [1,1,0,1,1,0,1,0]
//Output: [1,1,0,1,1,null,1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 200]. 
// Node.val is either 0 or 1. 
// 
//
// Related Topics Tree Depth-First Search Binary Tree 👍 4452 👎 111

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2024-02-21 22:37:08
// Jesse Yang
public class Leetcode0814BinaryTreePruning{
    // Java: binary-tree-pruning
    public static void main(String[] args) {
        Solution sol = new Leetcode0814BinaryTreePruning().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    /*
    if this tree has 1, return root;
    else return null;
     */
    public TreeNode pruneTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }
        
        // general case
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left != null || root.right != null || root.val == 1 ) {
            return root;
        } else {
            return null;
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

    /*
面试用Solution 1
 */
// Solution 1: T(n) = O(n), S(n) = O(n)
    
    
    //leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n), S(n) = O(n)
class Solution1 {
    
    /*
    if this tree has 1, return root;
    else return null;
     */
    public TreeNode pruneTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }
        
        // general case
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left != null || root.right != null || root.val == 1 ) {
            return root;
        } else {
            return null;
        }
    }
    
}

// Solution 2: T(n) = O(n), S(n) = O(n)
class Solution2 {
    
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }
    
    /*
    return this root subtree's contains 1
     */
    public boolean containsOne(TreeNode node) {
        // base case
        if (node == null) {
            return false;
        }
        
        // general case
        boolean leftContainsOne = containsOne(node.left);
        if (!leftContainsOne) {
            node.left = null;
        }
        
        boolean rightContainsOne = containsOne(node.right);
        if (!rightContainsOne) {
            node.right = null;
        }
        
        return node.val == 1 || leftContainsOne || rightContainsOne;
    }
    
}
}