//Invert a binary tree. 
//
// Example: 
//
// Input: 
//
// 
//     4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// Output: 
//
// 
//     4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// Trivia: 
//This problem was inspired by this original tweet by Max Howell: 
//
// Google: 90% of our engineers use the software you wrote (Homebrew), but you c
//an’t invert a binary tree on a whiteboard so f*** off. 
// Related Topics Tree 
// 👍 3483 👎 56

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-08-04 11:57:20
// Zeshi Yang
public class Leetcode0226InvertBinaryTree{
    // Java: invert-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0226InvertBinaryTree().new Solution();
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
    public TreeNode invertTree(TreeNode root) {
        // corner case
        if (root == null) {
            return root;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: bottom up reverse, post order dfs, T(n) = O(n), S(n) = O(lg(h)), h is depth of tree
// 0 ms,击败了100.00% 的Java用户, 36.2 MB,击败了80.16% 的Java用户
class Solution1 {
    
    public TreeNode invertTree(TreeNode root) {
        // corner case
        if (root == null) {
            return root;
        }
        
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
    
}

// Solution 2: top down reverse, pre order dfs, T(n) = O(n), S(n) = O(lg(h)), h is depth of tree
// 0 ms,击败了100.00% 的Java用户, 36.7 MB,击败了21.36% 的Java用户
class Solution2 {
    
    public TreeNode invertTree(TreeNode root) {
        // corner case
        if (root == null) {
            return root;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    
}

}