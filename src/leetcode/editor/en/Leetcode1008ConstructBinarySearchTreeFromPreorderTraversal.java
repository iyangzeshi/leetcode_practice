//Return the root node of a binary search tree that matches the given preorder t
//raversal. 
//
// (Recall that a binary search tree is a binary tree where for every node, any 
//descendant of node.left has a value < node.val, and any descendant of node.right
// has a value > node.val. Also recall that a preorder traversal displays the valu
//e of the node first, then traverses node.left, then traverses node.right.) 
//
// It's guaranteed that for the given test cases there is always possible to fin
//d a binary search tree with the given requirements. 
//
// Example 1: 
//
// 
//Input: [8,5,1,7,10,12]
//Output: [8,5,10,1,7,null,12]
//
// 
//
// 
// Constraints: 
//
// 
// 1 <= preorder.length <= 100 
// 1 <= preorder[i] <= 10^8 
// The values of preorder are distinct. 
// 
// Related Topics Tree 
// 👍 1264 👎 38

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 13:16:06
// Zeshi Yang
public class Leetcode1008ConstructBinarySearchTreeFromPreorderTraversal{
    // Java: construct-binary-search-tree-from-preorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode1008ConstructBinarySearchTreeFromPreorderTraversal().new Solution();
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
    int index = 0;
    int[] preorder;
    int n;
    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        n = preorder.length;
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode helper(int lower, int upper) {
        // if all elements from preorder are used, then the tree is constructed
        if (index == n) {
            return null;
        }

        int val = preorder[index];

        // if current lement could not be placed here to meet BST requirements
        if (val < lower || val > upper) {
            return null;
        }

        // place the current element and recursively construct subtrees
        index++;// 这个一定得在调用helper函数前面
        TreeNode root = new TreeNode(val);
        root.left = helper(lower, val);
        root.right = helper(val, upper);


        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}