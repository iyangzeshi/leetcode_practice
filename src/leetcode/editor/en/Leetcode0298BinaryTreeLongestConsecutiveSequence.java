//Given a binary tree, find the length of the longest consecutive sequence path.
// 
//
// The path refers to any sequence of nodes from some starting node to any node 
//in the tree along the parent-child connections. The longest consecutive path nee
//d to be from parent to child (cannot be the reverse). 
//
// Example 1: 
//
// 
//Input:
//
//   1
//    \
//     3
//    / \
//   2   4
//        \
//         5
//
//Output: 3
//
//Explanation: Longest consecutive sequence path is 3-4-5, so return 3. 
//
// Example 2: 
//
// 
//Input:
//
//   2
//    \
//     3
//    / 
//   2    
//  / 
// 1
//
//Output: 2 
//
//Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
// Related Topics Tree 
// 👍 508 👎 123

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-12 13:12:11
public class Leetcode0298BinaryTreeLongestConsecutiveSequence{
    // Java: binary-tree-longest-consecutive-sequence
    public static void main(String[] args) {
        Solution sol = new Leetcode0298BinaryTreeLongestConsecutiveSequence().new Solution();
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
        if (root == null) {
            return 0;
        }
        int[] count = new int[1];
        dfs(root, count);
        return count[0];
    }

    private int dfs(TreeNode root, int[] count) {
        if (root == null) {
            return 0;
        }

        int leftLen = dfs(root.left, count);
        int rightLen = dfs(root.right, count);

        int curLen = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            curLen = leftLen + 1;
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            curLen = Math.max(curLen, rightLen + 1);
        }
        count[0] = Math.max(count[0], curLen);
        return curLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}