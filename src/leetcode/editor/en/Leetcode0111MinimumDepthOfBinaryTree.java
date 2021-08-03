//Given a binary tree, find its minimum depth. 
//
// The minimum depth is the number of nodes along the shortest path from the roo
//t node down to the nearest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given binary tree [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// return its minimum depth = 2. 
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 1488 👎 697

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-08-04 11:33:47
// Zeshi Yang
public class Leetcode0111MinimumDepthOfBinaryTree{
    // Java: minimum-depth-of-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0111MinimumDepthOfBinaryTree().new Solution();
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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);

        if (leftHeight == 0 || rightHeight == 0) {
            return leftHeight + rightHeight + 1;
        }
        else {
            return Math.min(leftHeight, rightHeight) + 1;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}