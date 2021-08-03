//Given a non-empty binary search tree and a target value, find the value in the
// BST that is closest to the target. 
//
// Note: 
//
// 
// Given target value is a floating point. 
// You are guaranteed to have only one unique value in the BST that is closest t
//o the target. 
// 
//
// Example: 
//
// 
//Input: root = [4,2,5,1,3], target = 3.714286
//
//    4
//   / \
//  2   5
// / \
//1   3
//
//Output: 4
// 
// Related Topics Binary Search Tree 
// 👍 717 👎 58

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-14 11:00:03
public class Leetcode0270ClosestBinarySearchTreeValue{
    // Java: closest-binary-search-tree-value
    public static void main(String[] args) {
        Solution sol = new Leetcode0270ClosestBinarySearchTreeValue().new Solution();
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
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        TreeNode cur = root;
        int result = root.val;

        while (cur != null) {
            if (cur.val == target) {
                return cur.val;
            }
            // 现在的数字，不一定比历史上的最好数字好
            if (Math.abs(cur.val - target) < Math.abs(result - target)) {
                result = cur.val;
            }
            else if (target < cur.val) { // 走左子树
                cur = cur.left;
            }
            else { // 走右子树
                cur = cur.right;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}