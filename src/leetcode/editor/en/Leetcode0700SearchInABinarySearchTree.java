//Given the root node of a binary search tree (BST) and a value. You need to fin
//d the node in the BST that the node's value equals the given value. Return the s
//ubtree rooted with that node. If such node doesn't exist, you should return NULL
//. 
//
// For example, 
//
// 
//Given the tree:
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//And the value to search: 2
// 
//
// You should return this subtree: 
//
// 
//      2     
//     / \   
//    1   3
// 
//
// In the example above, if we want to search the value 5, since there is no nod
//e with value 5, we should return NULL. 
//
// Note that an empty tree is represented by NULL, therefore you would see the e
//xpected output (serialized tree format) as [], not null. 
// Related Topics Tree 
// 👍 1145 👎 123

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-12-12 20:05:21
// Jesse Yang
public class Leetcode0700SearchInABinarySearchTree{
    // Java: search-in-a-binary-search-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0700SearchInABinarySearchTree().new Solution();
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
    
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        TreeNode res = root;
        while (res != null) {
            if (res.val == val) {
                return res;
            } else if (res.val < val) {
                res = res.right;
            } else {
                res = res.left;
            }
        }
        return null;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}