//Given a binary tree, count the number of uni-value subtrees. 
//
// A Uni-value subtree means all nodes of the subtree have the same value. 
//
// Example : 
//
// 
//Input:  root = [5,1,5,5,5,null,5]
//
//              5
//             / \
//            1   5
//           / \   \
//          5   5   5
//
//Output: 4
// 
// Related Topics Tree 
// 👍 491 👎 128

package leetcode.editor.en;

import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-12 02:29:48
public class Leetcode0250CountUnivalueSubtrees{
    // Java: count-univalue-subtrees
    public static void main(String[] args) {
        Solution sol = new Leetcode0250CountUnivalueSubtrees().new Solution();
        // TO TEST
        String str = "5,1,5,5,5,null,5";
        TreeNode root = TreeGenerator.deserialize(str);
        int res = sol.countUnivalSubtrees(root);
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
// T(n) = O(n), S(n) = O(n)
class Solution {

    public int countUnivalSubtrees(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        int[] count = new int[1];
        isUnivalue(root, root.val, count);
        return count[0];
    }
	
    // isUnivalue method is used to record the number of UniTrees whose val is value
    private boolean isUnivalue(TreeNode root, int value, int[] count) {
        if (root == null) {
            return true;
        }

        boolean left = isUnivalue(root.left, root.val, count);
        boolean right = isUnivalue(root.right, root.val, count);
        boolean condition = left && right;
        if (!condition) {
            return false;
        }

        count[0]++;
        return root.val == value;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}