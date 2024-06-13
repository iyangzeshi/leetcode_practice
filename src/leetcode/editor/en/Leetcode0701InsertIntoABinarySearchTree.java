//Given the root node of a binary search tree (BST) and a value to be inserted i
//nto the tree, insert the value into the BST. Return the root node of the BST aft
//er the insertion. It is guaranteed that the new value does not exist in the orig
//inal BST. 
//
// Note that there may exist multiple valid ways for the insertion, as long as t
//he tree remains a BST after insertion. You can return any of them. 
//
// For example, 
//
// 
//Given the tree:
//        4
//       / \
//      2   7
//     / \
//    1   3
//And the value to insert: 5
// 
//
// You can return this binary search tree: 
//
// 
//         4
//       /   \
//      2     7
//     / \   /
//    1   3 5
// 
//
// This tree is also valid: 
//
// 
//         5
//       /   \
//      2     7
//     / \   
//    1   3
//         \
//          4
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the given tree will be between 0 and 10^4. 
// Each node will have a unique integer value from 0 to -10^8, inclusive. 
// -10^8 <= val <= 10^8 
// It's guaranteed that val does not exist in the original BST. 
// 
// Related Topics Tree 
// 👍 874 👎 77

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 14:17:31
// Jesse Yang
public class Leetcode0701InsertIntoABinarySearchTree{
    // Java: insert-into-a-binary-search-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0701InsertIntoABinarySearchTree().new Solution();
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
// T(n) = O(h), S(n) = O(h), log(n) <= h <= n
class Solution {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // corner case
        if (root == null) {
            return new TreeNode(val);
        }

        // general case
        TreeNode prev = root;
        TreeNode cur = root;
        while(cur != null) {
            prev = cur;
            if (val > cur.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (val < prev.val) {
            prev.left = new TreeNode(val);
        } else {
            prev.right = new TreeNode(val);
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: iteration
class Solution1 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // corner case
        if (root == null) {
            return new TreeNode(val);
        }

        // general case
        TreeNode prev = root;
        TreeNode cur = root;
        while(cur != null) {
            prev = cur;
            if (val > cur.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (val < prev.val) {
            prev.left = new TreeNode(val);
        } else {
            prev.right = new TreeNode(val);
        }
        return root;
    }
}

// Solution 2: recursion
class Solution2 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // corner case
        if (root == null) {
            return new TreeNode(val);
        }

        // base case
        if (val < root.val && root.left == null) {
            root.left = new TreeNode(val);
        } else if (val > root.val && root.right == null){
            root.right = new TreeNode(val);
        }

        // general case
        if (val < root.val) {
            insertIntoBST(root.left, val);
        } else {
            insertIntoBST(root.right, val);
        }
        return root;
    }
}
}