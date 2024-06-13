//Given the root node of a binary search tree, return the sum of values of all n
//odes with a value in the range [low, high]. 
//
// 
// Example 1: 
//
// 
//Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
//Output: 32
// 
//
// Example 2: 
//
// 
//Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
//Output: 23
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 2 * 104]. 
// 1 <= Node.val <= 105 
// 1 <= low <= high <= 105 
// All Node.val are unique. 
// Related Topics Tree Depth-first Search Recursion 
// 👍 1742 👎 263

package leetcode.editor.en;

import java.util.Stack;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-11-15 16:28:43
// Jesse Yang
public class Leetcode0938RangeSumOfBst{
    // Java: range-sum-of-bst
    public static void main(String[] args) {
        Solution sol = new Leetcode0938RangeSumOfBst().new Solution();
        // TO TEST
        String serial = "10,5,15,3,7,null,18";
        int low = 7;
        int high = 15;
        TreeNode root = TreeGenerator.deserialize(serial);
        int res = sol.rangeSumBST(root, low, high);
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
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] sum = new int[1];
        dfs(root, low, high, sum);
        return sum[0];
    }
    
    public void dfs(TreeNode cur, int low, int high, int[] sum) {
        // base case
        if (cur == null) {
            return;
        }
        
        if (low <= cur.val && cur.val <= high) {
            sum[0] += cur.val;
        }
        if (cur.val > low) {
            dfs(cur.left, low, high, sum);
        }
        if (cur.val < high) {
            dfs(cur.right, low, high, sum);
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: recursion, pre order dfs
// T(n) = O(k), S(n) = O(k), k is number of nodes satisfying the requirement
// 0 ms,击败了100.00% 的Java用户, 47.5 MB,击败了26.96% 的Java用户
class Solution1 {
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] sum = new int[1];
        dfs(root, low, high, sum);
        return sum[0];
    }
    
    public void dfs(TreeNode cur, int low, int high, int[] sum) {
        // base case
        if (cur == null) {
            return;
        }
        
        if (low <= cur.val && cur.val <= high) {
            sum[0] += cur.val;
        }
        if (cur.val > low) {
            dfs(cur.left, low, high, sum);
        }
        if (cur.val < high) {
            dfs(cur.right, low, high, sum);
        }
    }
    
}

// Solution 2: stack, pre order dfs,
// T(n) = O(k), S(n) = O(k), k is number of nodes satisfying the requirement
// 2 ms,击败了20.13% 的Java用户, 47 MB,击败了66.29% 的Java用户
class Solution2 {
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (low <= node.val && node.val <= high) {
                    sum += node.val;
                }
                if (node.val > low) {
                    stack.push(node.left);
                }
                if (node.val < high) {
                    stack.push(node.right);
                }
            }
        }
        return sum;
    }
    
}
}