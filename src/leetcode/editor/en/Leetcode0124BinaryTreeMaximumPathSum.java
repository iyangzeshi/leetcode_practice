//Given a non-empty binary tree, find the maximum path sum. 
//
// For this problem, a path is defined as any sequence of nodes from some starti
//ng node to any node in the tree along the parent-child connections. The path mus
//t contain at least one node and does not need to go through the root. 
//
// Example 1: 
//
// 
//Input: [1,2,3]
//
//       1
//      / \
//     2   3
//
//Output: 6
// 
//
// Example 2: 
//
// 
//Input: [-10,9,20,null,null,15,7]
//
//   -10
//   / \
//  9  20
//    /  \
//   15   7
//
//Output: 42
// 
// Related Topics Tree Depth-first Search 
// 👍 3807 👎 296

package leetcode.editor.en;

import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-26 13:02:53
// Zeshi Yang
public class Leetcode0124BinaryTreeMaximumPathSum{
    // Java: binary-tree-maximum-path-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0124BinaryTreeMaximumPathSum().new Solution();
        // TO TEST
        TreeNode root = TreeGenerator.deserialize("-10,9,20,null,null,15,7");
        TreeDrawer.draw(root);
        int res = sol.maxPathSum(root);
        System.out.println(res);
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
    
    public int maxPathSum(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, max);
        return max[0];
        
    }
    
    private int maxPathSum(TreeNode root, int[] max) {
        // base case
        if (root == null) {
            return 0;
        }
        int left = maxPathSum(root.left, max);
        int right = maxPathSum(root.right, max);
        
        int maxSum = Math.max(left, 0) + Math.max(right, 0) + root.val; // 经过这个node的最大maxPathSum
        
        max[0] = Math.max(maxSum, max[0]);
        //此处要注意的问题都是当左右两边有一边传上来的值是负数的时候
        return Math.max(left, Math.max(right, 0)) + root.val;// 这个node为起点的最大maxPathSum
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}