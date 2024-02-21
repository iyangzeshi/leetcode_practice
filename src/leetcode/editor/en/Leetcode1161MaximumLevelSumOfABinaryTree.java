//Given the root of a binary tree, the level of its root is 1, the level of its 
//children is 2, and so on. 
//
// Return the smallest level x such that the sum of all the values of nodes at 
//level x is maximal. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,7,0,7,-8,null,null]
//Output: 2
//Explanation: 
//Level 1 sum = 1.
//Level 2 sum = 7 + 0 = 7.
//Level 3 sum = 7 + -8 = -1.
//So we return the level with the maximum sum which is level 2.
//
//
// Example 2: 
//
// 
//Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 34
//28 👎 98

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;
// .TreeOfNodeGenerator;

// 2024-02-17 21:16:39
// Zeshi(Jesse) Yang
public class Leetcode1161MaximumLevelSumOfABinaryTree{
    // Java: maximum-level-sum-of-a-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode1161MaximumLevelSumOfABinaryTree().new Solution();
        // TO TEST
        TreeNode root = TreeGenerator.deserialize("1,7,0,7,-8");
        int res = sol.maxLevelSum(root);
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
// BFS T(n) = O(n), S(n) = O(n）
/*
using BFS to traverse the tree level by level
update the levelSum and update the maxLevelSum and record the related the levels
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        
        // general case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxSum = root.val;
        int level = 1;
        int maxSumLevel = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            while (size-- > 0) { // poll i level, add i + 1 level
                TreeNode cur = queue.poll();
                levelSum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxSumLevel = level;
            }
            level++;
        }
        return maxSumLevel;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}