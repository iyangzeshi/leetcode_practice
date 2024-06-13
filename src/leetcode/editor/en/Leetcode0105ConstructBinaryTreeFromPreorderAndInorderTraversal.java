//Given preorder and inorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//preorder = [3,9,20,15,7]
//inorder = [9,3,15,20,7] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics Array Tree Depth-first Search 
// 👍 3464 👎 95

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import leetcode.editor.TreeNode;

// 2020-07-26 13:14:34
// Jesse Yang
public class Leetcode0105ConstructBinaryTreeFromPreorderAndInorderTraversal{
    // Java: construct-binary-tree-from-preorder-and-inorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
// Solution: divide&conquer, T(n) = O(n), S(n) = O(n)
/*
知道了preorder和inorder里面的一段可以组成一个tree
step 0:condition, assuming we know the preorder[preLeft, preRight] and inorder[inLeft, inRight]
step 1: find the root
step 2: find the root's index in inorder array
step 3: calculate the left and right subtree's size
step 4: calculate the left and right subtree's border in inorder and postorder array
 */
class Solution {
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
    
        // key - inorder number, value - inorder number's index
        Map<Integer, Integer> inNumToIdx = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int num = inorder[i];
            inNumToIdx.put(num, i);
        }
        return buildTree(preorder, 0, len - 1, 0, len - 1, inNumToIdx);
    }
    
    /*
     preorder[preLeft, preRight]表示当前subTree在preOrder的范围，inclusive
     inorder[inLeft, inRight]表示当前subTree在inOrder遍历中的范围，inclusive
     
     有了当前的tree的范围preorder[preLeft, preRight] 和 inorder[inLeft, inRight]后，
     先取root = preOrder[preLeft]，找出它在inOrder中的索引 inRootIdx;
     
     重点： 然后怎么确定left subtree和right subtree在preorder和 inorder里面的位置
     preLeft, preRight, inLeft, inRight
     
     左子树：
     leftSubtree's size: leftSize = inRootIdx - inLeft;
     所以left subtree
     在preOrder序列中的范围是[preLeft + 1, preLeft + leftSize]，
     在InOrder序列中的范围是[inLeft, inRootIdx - 1]
     
     右子树：
     rightSubtree's size: rightSize = inRight - inRootIdx
     所以right subtree
     在preOrder序列中给你的范围是[preLeft + leftSize + 1, preRight],
     // 也可以是[preRight - rightSize + 1, preRight]
     在inOrder序列中的范围是[inRootIdx + 1, inRight]
     */
    private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
            int inLeft, int inRight, Map<Integer, Integer> map) {
        // base case
        if (inLeft > inRight) {
            return null;
        }
        
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int inRootIdx = map.get(rootVal); // index of rootVal in inOrder[] array
        int leftSize = inRootIdx - inLeft;
        int rightSize = inRight - inRootIdx;
        
        root.left = buildTree(preorder, preLeft + 1, preLeft + leftSize,
                inLeft, inRootIdx - 1, map);
        root.right = buildTree(preorder, preRight - rightSize + 1, preRight,
                inRootIdx + 1, inRight, map);
        return root;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)


}