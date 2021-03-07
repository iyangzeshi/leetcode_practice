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

import leetcode.editor.TreeNode;

import java.util.HashMap;

// 2020-07-26 13:14:34
// Zeshi Yang
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
// Solution 1.1: Solution: Divide and conquer
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //corner case
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = preorder.length;
        // 建立inorder中建立对应关系：值————索引
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, length - 1, inorder, 0, length - 1, map);
    }
    /**
     preStart[preStart, preEnd]表示当前subTree在preOrder的范围，inclusive
     inorder[inStart, inEnd]表示当前subTree在inOrder遍历中的范围，inclusive
     有了当前的tree的范围preStart[preStart, preEnd] 和 inorder[inStart, inEnd]后，
     先取root = preOrder[preStart]，找出它在inOrder中的索引 rootIndex;
     说明当前先root的leftSubtree有rootIndex - inStart个node,
     则left subtree 在preOrder序列中的范围是[preStart + 1, preStart + rootIndex - inStart]，在InOrder序列中的范围是[inStart, rootIndex - 1]
     right subtree在preOrder序列中给你的范围是[preStart + rootIndex - inStart + 1, preEnd]，在inOrder序列中的范围是[rootIndex + 1, inEnd]
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
        // base case
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int rootIndex = map.get(rootVal); // index of rootVal in inOrder[] array
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, preStart + 1, preStart + rootIndex - inStart,
                inorder, inStart, rootIndex - 1, map);
        root.right = buildTree(preorder, preStart + rootIndex - inStart + 1, preEnd,
                inorder, rootIndex + 1, inEnd, map);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1.1: Solution: Divide and conquer
class Solution1_1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //corner case
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = preorder.length;
        // 建立inorder中建立对应关系：值————索引
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, length - 1, inorder, 0, length - 1, map);
    }
    /**
     preStart[preStart, preEnd]表示当前subTree在preOrder的范围，inclusive
     inorder[inStart, inEnd]表示当前subTree在inOrder遍历中的范围，inclusive
     有了当前的tree的范围preStart[preStart, preEnd] 和 inorder[inStart, inEnd]后，
     先取root = preOrder[preStart]，找出它在inOrder中的索引 rootIndex;
     说明当前先root的leftSubtree有rootIndex - inStart个node,
     则left subtree 在preOrder序列中的范围是[preStart + 1, preStart + rootIndex - inStart]，在InOrder序列中的范围是[inStart, rootIndex - 1]
     right subtree在preOrder序列中给你的范围是[preStart + rootIndex - inStart + 1, preEnd]，在inOrder序列中的范围是[rootIndex + 1, inEnd]
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
        // base case
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int rootIndex = map.get(rootVal); // index of rootVal in inOrder[] array
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, preStart + 1, preStart + rootIndex - inStart,
                inorder, inStart, rootIndex - 1, map);
        root.right = buildTree(preorder, preStart + rootIndex - inStart + 1, preEnd,
                inorder, rootIndex + 1, inEnd, map);
        return root;
    }
}

// Solution 1.2: Solution: Divide and conquer
class Solution1_2 {
    // start from first preorder element
    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length);
    }

    /**
     in_left 和in_right表示当前的tree在inOrder中的范围是inOrder[in_left, in_right] inclusively
     */
    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right) {
            return null;
        }

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;

        // build left subtree
        root.left = helper(in_left, index);

        // build right subtree
        root.right = helper(index + 1, in_right);
        return root;
    }
}
}