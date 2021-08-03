//Given a binary search tree and a node in it, find the in-order successor of th
//at node in the BST. 
//
// The successor of a node p is the node with the smallest key greater than p.va
//l. 
//
// 
//
// Example 1: 
//
// 
//Input: root = [2,1,3], p = 1
//Output: 2
//Explanation: 1's in-order successor node is 2. Note that both p and the return
// value is of TreeNode type.
// 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,null,1], p = 6
//Output: null
//Explanation: There is no in-order successor of the current node, so the answer
// is null.
// 
//
// 
//
// Note: 
//
// 
// If the given node has no in-order successor in the tree, return null. 
// It's guaranteed that the values of the tree are unique. 
// 
// Related Topics Tree 
// 👍 1129 👎 64

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-16 11:42:34
// Zeshi Yang
public class Leetcode0285InorderSuccessorInBst{
    // Java: inorder-successor-in-bst
    public static void main(String[] args) {
        Solution sol = new Leetcode0285InorderSuccessorInBst().new Solution();
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        // corner case
        if (target == null) {
            throw new IllegalArgumentException();
        }
        TreeNode res = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == target.val || cur.val < target.val) {
                cur = cur.right;
            } else {
            	res = cur;
            	cur = cur.left;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}