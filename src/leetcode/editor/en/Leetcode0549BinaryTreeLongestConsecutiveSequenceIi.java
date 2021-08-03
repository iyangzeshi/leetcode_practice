//Given a binary tree, you need to find the length of Longest Consecutive Path i
//n Binary Tree. 
//
// Especially, this path can be either increasing or decreasing. For example, [1
//,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not v
//alid. On the other hand, the path can be in the child-Parent-child order, where 
//not necessarily be parent-child order. 
//
// Example 1: 
//
// 
//Input:
//        1
//       / \
//      2   3
//Output: 2
//Explanation: The longest consecutive path is [1, 2] or [2, 1].
// 
//
// 
//
// Example 2: 
//
// 
//Input:
//        2
//       / \
//      1   3
//Output: 3
//Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
// 
//
// 
//
// Note: All the values of tree nodes are in the range of [-1e7, 1e7]. 
// Related Topics Tree 
// 👍 586 👎 44

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-12 13:30:50
public class Leetcode0549BinaryTreeLongestConsecutiveSequenceIi{
    // Java: binary-tree-longest-consecutive-sequence-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0549BinaryTreeLongestConsecutiveSequenceIi().new Solution();
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
    public int longestConsecutive(TreeNode root) {
        if (root == null ) {
            return 0;
        }
        int[] count = new int[1];
        dfs(root, count);
        return count[0];
    }
    // 返回int[]{以root为start的递增max长度, 以root为start的递减max长度}
    private int[] dfs(TreeNode root, int[] count) {
        // base case
        if (root == null) {
            return new int[2];
        }

        int[] left = dfs(root.left, count);
        int[] right = dfs(root.right, count);
        int[] res = new int[] {1, 1};

        if (root.left != null) {
            if (root.val == root.left.val - 1) {
                res[0] = left[0] + 1;
            }
            if (root.val == root.left.val + 1) {
                res[1] = left[1] + 1;
            }
        }

        if (root.right != null) {
            if (root.val == root.right.val - 1) {
                res[0] = Math.max(res[0], right[0] + 1);
            }
            if (root.val == root.right.val + 1) {
                res[1] = Math.max(res[1], right[1] + 1);
            }
        }

        count[0] = Math.max(count[0], res[0] + res[1] - 1);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}