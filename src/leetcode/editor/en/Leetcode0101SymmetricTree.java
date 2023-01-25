//Given a binary tree, check whether it is a mirror of itself (ie, symmetric aro
//und its center). 
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric: 
//
// 
//    1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// But the following [1,2,2,null,3,null,3] is not: 
//
// 
//    1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// Follow up: Solve it both recursively and iteratively. 
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 4312 👎 105

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-08-04 11:32:22
// Zeshi Yang
public class Leetcode0101SymmetricTree{
    // Java: symmetric-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0101SymmetricTree().new Solution();
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
// DFS, T(n) = O(n), S(n) = O(n)
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    
    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return isMirror(left.left, right.right) && isMirror(left.right, right.left);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}