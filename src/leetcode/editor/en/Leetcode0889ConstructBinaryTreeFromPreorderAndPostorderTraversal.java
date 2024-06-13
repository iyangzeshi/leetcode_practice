//Return any binary tree that matches the given preorder and postorder traversal
//s. 
//
// Values in the traversals pre and post are distinct positive integers. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//Output: [1,2,3,4,5,6,7]
// 
//
// 
//
// Note: 
//
// 
// 1 <= pre.length == post.length <= 30 
// pre[] and post[] are both permutations of 1, 2, ..., pre.length. 
// It is guaranteed an answer exists. If there exists multiple answers, you can 
//return any of them. 
// 
// 
// Related Topics Tree 
// 👍 837 👎 50

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.TreeNode;

// 2020-07-26 13:16:21
// Jesse Yang
public class Leetcode0889ConstructBinaryTreeFromPreorderAndPostorderTraversal{
    // Java: construct-binary-tree-from-preorder-and-postorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0889ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        //corner case
        if (pre == null || post == null || pre.length == 0 || post.length == 0) {
            return null;
        }
        int length= pre.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        //在inorder中建立对应关系
        for (int i = 0; i < length; i++) {
            map.put(post[i], i);
        }
        return buildTree(pre, 0, length - 1, post, 0, length - 1, map);
    }

    private TreeNode buildTree(int[] pre, int preStart, int preEnd,
            int[] post, int postStart, int postEnd,
            HashMap<Integer, Integer> map) {
        // base case
        if (preStart > preEnd || postStart > postEnd) {
            return null; //要严格大于才行，不然只有一个node 的时候，就return null了
        }
        if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }

        TreeNode root = new TreeNode(pre[preStart]);

        int leftRootValue = pre[preStart + 1];
        int leftRootInPost = map.get(leftRootValue);

        root.left = buildTree(pre, preStart + 1, preStart + 1 + (leftRootInPost - postStart),
                post, postStart, leftRootInPost, map);
        root.right = buildTree(pre, preStart + 1 + (leftRootInPost - postStart) + 1, preEnd,
                post, leftRootInPost + 1, postEnd - 1, map);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1:  divide and conquer
class Solution1 {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        //corner case
        if (pre == null || post == null || pre.length == 0 || post.length == 0) {
            return null;
        }
        int length= pre.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        //在inorder中建立对应关系
        for (int i = 0; i < length; i++) {
            map.put(post[i], i);
        }
        return buildTree(pre, 0, length - 1, post, 0, length - 1, map);
    }

    private TreeNode buildTree(int[] pre, int preStart, int preEnd,
            int[] post, int postStart, int postEnd,
            HashMap<Integer, Integer> map) {
        // base case
        if (preStart > preEnd || postStart > postEnd) {
            return null; //要严格大于才行，不然只有一个node 的时候，就return null了
        }
        if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }

        TreeNode root = new TreeNode(pre[preStart]);

        int leftRootValue = pre[preStart + 1];
        int leftRootInPost = map.get(leftRootValue);

        root.left = buildTree(pre, preStart + 1, preStart + 1 + (leftRootInPost - postStart),
                post, postStart, leftRootInPost, map);
        root.right = buildTree(pre, preStart + 1 + (leftRootInPost - postStart) + 1, preEnd,
                post, leftRootInPost + 1, postEnd - 1, map);
        return root;
    }
}
// Solution 2: 目前看不太懂
class Solution2 {
    int preIndex = 0;
    int postIndex = 0;
    public TreeNode constructFromPrePost(int[]pre, int[]post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if(root.val != post[postIndex]){
            root.left = constructFromPrePost(pre, post);
        }
        if(root.val != post[postIndex]){
            root.right = constructFromPrePost(pre, post);
        }
        postIndex++;
        return root;
    }
}
}