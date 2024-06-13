//Given two non-empty binary trees s and t, check whether tree t has exactly the
// same structure and node values with a subtree of s. A subtree of s is a tree co
//nsists of a node in s and all of this node's descendants. The tree s could also 
//be considered as a subtree of itself. 
//
// Example 1: 
//Given tree s: 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
// 
//Given tree t:
//
// 
//   4 
//  / \
// 1   2
// 
//Return true, because t has the same structure and node values with a subtree o
//f s.
//
// 
//
// Example 2: 
//Given tree s: 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
//    /
//   0
// 
//Given tree t:
//
// 
//   4
//  / \
// 1   2
// 
//Return false.
//
// 
// Related Topics Tree 
// 👍 2408 👎 116

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-08-04 12:36:05
// Jesse Yang
public class Leetcode0572SubtreeOfAnotherTree{
    // Java: subtree-of-another-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0572SubtreeOfAnotherTree().new Solution();
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
// T(m, n) = O(m * n), S(m,n) = O(m + n), m and n is size of the tree s and t respectively
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // if (t == null) {
        //     return true;
        // }
        if (s == null) {
            return false;
        }
        if (isSame(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSame(left.left, right.left) && isSame(left.right, right.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}