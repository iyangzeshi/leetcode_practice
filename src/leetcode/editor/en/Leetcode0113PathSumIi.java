//Given a binary tree and a sum, find all root-to-leaf paths where each path's s
//um equals the given sum. 
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
// /  \    / \
//7    2  5   1
// 
//
// Return: 
//
// 
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics Tree Depth-first Search 
// 👍 1802 👎 66

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.TreeNode;

// 2020-07-26 13:09:14
// Zeshi Yang
public class Leetcode0113PathSumIi{
    // Java: path-sum-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0113PathSumIi().new Solution();
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
// T(n) = O(n^2), S(n) = O(n).
/*
每条路径都有可能是结果，如果每条路径都加进去的话，就要是O(n^2)
 */
class Solution {
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        // corner case
        if (root == null) {
            return result;
        }
        
        pathSum(root, sum, new ArrayList<>(), result);
        
        return result;
    }
    
    private void pathSum(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result) {
        
        // base case
        if (root == null) {
            return;
        }
        
        list.add(root.val);
        
        // root.left == null && root.right == null, because the path sum must reach to the leaf
        if (root.val == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        // DFS moving deeper
        pathSum(root.right, sum - root.val, list, result);
        pathSum(root.left, sum - root.val, list, result);
        
        list.remove(list.size() - 1); // backtracking, important to understand
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}