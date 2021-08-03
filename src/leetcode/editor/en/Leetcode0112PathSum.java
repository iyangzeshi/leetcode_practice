//Given a binary tree and a sum, determine if the tree has a root-to-leaf path s
//uch that adding up all the values along the path equals the given sum. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given the below binary tree and sum = 22, 
//
// 
//      5
//     / \
//    4   8
//   /   / \
//  11  13  4
// /  \      \
//7    2      1
// 
//
// return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22. 
//
// Related Topics Tree Depth-first Search 
// 👍 1950 👎 498

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-23 11:19:52
// Zeshi Yang
public class Leetcode0112PathSum{
    // Java: path-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0112PathSum().new Solution();
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode cur, int remain) {
        if (cur == null) {
            return false;
        }
        //return dfs(cur.left, remain - cur.val) || dfs(cur.right, remain - cur.val);
        // must be root-to-leaf: means leaf must be a node without any children
        if (cur.left == null && cur.right == null) {
            return remain == cur.val;
        }
        // both are not null
        return dfs(cur.left, remain - cur.val) || dfs(cur.right, remain - cur.val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public boolean hasPathSum(TreeNode root, int sum) {
        // base case
        if (root == null) {
            return false;
        }
        // general case
        if(root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
class Solution2 {
    public boolean hasPathSum(TreeNode root, int sum) {
        // corner case
        if (root == null) {
            return false;
        }

        // general case
        return hashPathSum(root, 0, sum);
    }

    private boolean hashPathSum(TreeNode root, int curSum, int sum) {
        // base case
        if (root == null) {
            return false;
        }
        // general case
        curSum += root.val;
        if (root.left == null && root.right == null) {
            if (curSum == sum) {
                return true;
            }
        }
        return hashPathSum(root.left, curSum, sum) || hashPathSum(root.right, curSum, sum);
    }
}
}