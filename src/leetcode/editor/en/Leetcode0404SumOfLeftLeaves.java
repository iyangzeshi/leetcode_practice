//Find the sum of all left leaves in a given binary tree. 
//
// Example:
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//There are two left leaves in the binary tree, with values 9 and 15 respectivel
//y. Return 24.
// 
// Related Topics Tree 
// 👍 1526 👎 150

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-11-15 13:24:14
// Jesse Yang
public class Leetcode0404SumOfLeftLeaves{
    // Java: sum-of-left-leaves
    public static void main(String[] args) {
        Solution sol = new Leetcode0404SumOfLeftLeaves().new Solution();
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
// DFS
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        
        return leftLeavesSum(root, false);
    }
    
    private int leftLeavesSum(TreeNode root, boolean isLeft) {
        // base case
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            if (isLeft) {
                return root.val;
            } else {
                return 0;
            }
        }
        
        int leftSum = leftLeavesSum(root.left, true);
        int rightSum = leftLeavesSum(root.right, false);
        return leftSum + rightSum;
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS
class Solution1 {
    public int sumOfLeftLeaves(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                queue.offer(node.left);
                if (left.left == null && left.right == null) {
                    sum += left.val;
                }
            }
            if (right != null) {
                queue.offer(node.right);
            }
        }
        return sum;
    }
}

// Solution 2: DFS
class Solution2 {
    public int sumOfLeftLeaves(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        
        return leftLeavesSum(root, false);
    }
    
    private int leftLeavesSum(TreeNode root, boolean isLeft) {
        // base case
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            if (isLeft) {
                return root.val;
            } else {
                return 0;
            }
        }
        
        int leftSum = leftLeavesSum(root.left, true);
        int rightSum = leftLeavesSum(root.right, false);
        return leftSum + rightSum;
        
    }
}
}