//A full binary tree is a binary tree where each node has exactly 0 or 2 childre
//n. 
//
// Return a list of all possible full binary trees with size nodes. Each element of
// the answer is the root node of one possible tree. 
//
// Each node of each tree in the answer must have node.val = 0. 
//
// You may return the final list of trees in any order. 
//
// 
//
// Example 1: 
//
// 
//Input: 7
//Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,
//0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
//Explanation:
//
// 
//
// 
//
// Note: 
//
// 
// 1 <= size <= 20
// 
// Related Topics Tree Recursion 
// 👍 1130 👎 94

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import leetcode.editor.TreeNode;

// 2020-11-14 12:37:55
// Zeshi Yang
public class Leetcode0894AllPossibleFullBinaryTrees{
    // Java: all-possible-full-binary-trees
    public static void main(String[] args) {
        Solution sol = new Leetcode0894AllPossibleFullBinaryTrees().new Solution();
        // TO TEST
        int n = 7;
        List<TreeNode> res = sol.allPossibleFBT(n);
        System.out.println(res.size());
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
/*
Tree里这种生成tree的问题，只要focus在确定root就行了，然后recursion的生成
 */
class Solution {
    public List<TreeNode> allPossibleFBT(int n) {
        return dfs(n);
    }
    
    private List<TreeNode> dfs(int n) {
        List<TreeNode> res = new ArrayList<>();
        // base case
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        for (int i = 1; i < n - 1; i++) {
            List<TreeNode> leftTree = dfs(i);
            List<TreeNode> rightTree = dfs(n - 1 - i);
            for (TreeNode left: leftTree) {
                for (TreeNode right: rightTree) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}