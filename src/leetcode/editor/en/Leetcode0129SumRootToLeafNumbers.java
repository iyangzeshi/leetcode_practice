//You are given the root of a binary tree containing digits from 0 to 9 only. 
//
// Each root-to-leaf path in the tree represents a number. 
//
// 
// For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123. 
// 
//
// Return the total sum of all root-to-leaf numbers. 
//
// A leaf node is a node with no children. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3]
//Output: 25
//Explanation:
//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//Therefore, sum = 12 + 13 = 25.
// 
//
// Example 2: 
//
// 
//Input: root = [4,9,0,5,1]
//Output: 1026
//Explanation:
//The root-to-leaf path 4->9->5 represents the number 495.
//The root-to-leaf path 4->9->1 represents the number 491.
//The root-to-leaf path 4->0 represents the number 40.
//Therefore, sum = 495 + 491 + 40 = 1026.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 1000]. 
// 0 <= Node.val <= 9 
// The depth of the tree will not exceed 10. 
// 
// Related Topics Tree Depth-first Search 
// 👍 2082 👎 54

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2021-02-21 14:28:19
// Jesse Yang
public class Leetcode0129SumRootToLeafNumbers{
    // Java: sum-root-to-leaf-numbers
    public static void main(String[] args) {
        Solution sol = new Leetcode0129SumRootToLeafNumbers().new Solution();
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
// recursion, pre order dfs, T(n) = O(n), S(n) = O(h), lgn <= h <= n
// 0 ms,击败了100.00% 的Java用户, 36.1 MB,击败了98.97% 的Java用户
class Solution {
    
    public int sumNumbers(TreeNode root) {
        int[] sum = {0};
        dfs(root, 0, sum);
        return sum[0];
    }
    
    // pre order dfs
    private void dfs(TreeNode node, int path, int[] sum) {
        // base case
        if (node == null) {
            return;
        }
        path = path * 10 + node.val;
        if (node.left == null && node.right == null) {
            sum[0] += path;
        }
        
        dfs(node.left, path, sum);
        dfs(node.right, path, sum);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: recursion, pre order dfs, T(n) = O(n), S(n) = O(h), lgn <= h <= n
// 0 ms,击败了100.00% 的Java用户, 36.1 MB,击败了98.97% 的Java用户
class Solution1 {
    
    public int sumNumbers(TreeNode root) {
        int[] sum = {0};
        dfs(root, 0, sum);
        return sum[0];
    }
    
    // pre order dfs
    private void dfs(TreeNode node, int path, int[] sum) {
        // base case
        if (node == null) {
            return;
        }
        path = path * 10 + node.val;
        if (node.left == null && node.right == null) {
            sum[0] += path;
        }
        
        dfs(node.left, path, sum);
        dfs(node.right, path, sum);
    }
    
}

}