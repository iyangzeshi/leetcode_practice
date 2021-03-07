//
//Given a binary tree, you need to compute the length of the diameter of the tre
//e. The diameter of a binary tree is the length of the longest path between any t
//wo nodes in a tree. This path may or may not pass through the root.
// 
//
// 
//Example: 
//Given a binary tree 
// 
//          1
//         / \
//        2   3
//       / \     
//      4   5    
// 
// 
// 
//Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
// 
//
// Note:
//The length of path between two nodes is represented by the number of edges bet
//ween them.
// Related Topics Tree 
// 👍 3399 👎 205

package leetcode.editor.en;

import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-09-07 18:00:18
// Zeshi Yang
public class Leetcode0543DiameterOfBinaryTree{
    // Java: diameter-of-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0543DiameterOfBinaryTree().new Solution();
        // TO TEST
        TreeNode root = TreeGenerator.deserialize("1,2,3,4,5");
        int res = sol.diameterOfBinaryTree(root);
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
    public int diameterOfBinaryTree(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        int[] max = {0};
        postOrder(root, max);
        return max[0];
    }

    // post order traversal, head recursion
    private int postOrder(TreeNode cur, int[] max) {
        if (cur == null) {
            return 0;
        }
        int left = postOrder(cur.left, max);
        int right = postOrder(cur.right, max);
        max[0] = Math.max(max[0], left + right);
        return Math.max(left, right) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}