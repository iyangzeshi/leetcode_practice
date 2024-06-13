//Given inorder and postorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//inorder = [9,3,15,20,7]
//postorder = [9,15,7,20,3] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics Array Tree Depth-first Search 
// 👍 1910 👎 37

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import leetcode.editor.TreeNode;

// 2020-08-04 11:32:26
// Jesse Yang
public class Leetcode0106ConstructBinaryTreeFromInorderAndPostorderTraversal{
    // Java: construct-binary-tree-from-inorder-and-postorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
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

// divide&conquer, T(n) = O(n), S(n) = O(n)
/*
知道了inorder和postorder里面的一段可以组成一个tree
step 0: condition, assuming we know the inorder[inLeft, inRight] and postorder[postLeft, postRight]
step 1: find the root
step 2: find the root's index in inorder array
step 3: calculate the left and right subtree's size
step 4: calculate the left and right subtree's border in inorder and postorder array
 */
class Solution {
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        
        // key - inorder number, value - inorder number's index
        Map<Integer, Integer> inNumToIdx = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Integer num = inorder[i];
            inNumToIdx.put(num, i);
        }
        TreeNode root = buildTree(0, inorder.length - 1, inNumToIdx, 0, postorder.length - 1,
	        postorder
        );
        return root;
    }
    
    // pre order dfs
    /*
    确定了subTree的in order 的左右边界, post order的左右边界，把这个sub tree返回出来
    重点：
    知道了当前sub tree的in order和post order的左右边界
    怎么分别找左右子树的in order和post order的左右边界
    inLeft, inRight, postLeft, postRight
    
    rootValue = postOrder[postRight];
    在post order traversal中，当前subtree的root的index是：
    inRootIdx = map.get(rootValue);
    
    那么左子树的border在两个Traversal array中的位置是：
    inLeft = inLeft;
    inRight = inRootIdx - 1
    postLeft = postLeft;
    ∵ left sub tree's size: leftSize = inRootIdx - inLeft
    ∴ postRight = postLeft + (inRootIdx - inLeft) - 1;
    
    右子树的border在两个Traversal array中的位置是：
    inLeft = inRootIdx + 1;
    inRight = inRight;
    
    ∵ right sub tree's  size: rightSize = inRight - inRootIdx
    ∴ postLeft = postRight - (inRight - inRootIdx)
    // postLeft = postLeft + (inRootIdx - inLeft)也可以,左子树post order边界 + 1
    postRight = postRight - 1;
     */
	
	/**
	 *
	 * the subtree's border:
	 *      [inLeft, inRight] in in-order traversal
	 *      [postOrder, postRight] in post-order traversal
	 * @return: root of this subtree
	 */
    private TreeNode buildTree(int inLeft, int inRight, Map<Integer, Integer> map,
	    int postLeft, int postRight, int[] postorder) {
        // base case - failure case
        if (inLeft > inRight) {
            return null; //要严格大于才行，不然只有一个node 的时候，就return null了
        }
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        
        int inRootIdx = map.get(rootVal);
        int leftSize = inRootIdx - inLeft;
        int rightSize = inRight - inRootIdx;
        
        root.left = buildTree(inLeft, inRootIdx - 1, map,
	        postLeft, postLeft + leftSize - 1, postorder);
        root.right = buildTree(inRootIdx + 1, inRight, map,
	        postRight - rightSize, postRight - 1, postorder);
        return root;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}