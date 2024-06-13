//Given a binary tree, flatten it to a linked list in-place. 
//
// For example, given the following tree: 
//
// 
//    1
//   / \
//  2   5
// / \   \
//3   4   6
// 
//
// The flattened tree should look like: 
//
// 
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
// 
// Related Topics Tree Depth-first Search 
// 👍 2861 👎 337

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 13:12:32
// Jesse Yang
public class Leetcode0114FlattenBinaryTreeToLinkedList{
    // Java: flatten-binary-tree-to-linked-list
    public static void main(String[] args) {
        Solution sol = new Leetcode0114FlattenBinaryTreeToLinkedList().new Solution();
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
// Solution 2: divide and conquer
class Solution {
    /**
     Solution 2:
     divide and conquer 思想
     flatten(TreeNode root, TreeNode tail)函数，让root preOrder遍历之后的末尾接上tail，然后返回root
     */
    public void flatten(TreeNode root) {
        //corner case
        if(root == null) {
            return;
        }
        flatten(root, null);
    }

    private TreeNode flatten(TreeNode root, TreeNode tail){
        // corner case
        if(root == null) {
            return tail; // ??为什么？
        }

        TreeNode right = flatten(root.right, tail);
        TreeNode left = flatten(root.left, right);
        root.right = left;
        root.left = null;
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: preOrder思想反着来
class Solution1 {
    /**
     思路首先是preOrder，创建TreeNode连起来；
     但是题目要用in-place操作，想着能不能用preOrder逆着遍历操作，结构是
     preOrder(root.right);
     preOrder(root.left);
     // root
     // to do sth
     */
    public void flatten(TreeNode root) {
        //corner case
        if(root == null) {
            return;
        }
        TreeNode[] pre = new TreeNode[1];
        flatten(root, pre);
    }

    private void flatten(TreeNode root, TreeNode[] pre){
        // corner case
        if(root == null) {
            return;
        }

        flatten(root.right, pre);
        flatten(root.left, pre);
        root.right = pre[0];
        root.left = null;
        pre[0] = root;
    }

}

// Solution 2: divide and conquer
class Solution2 {
    /**
     Solution 2:
     divide and conquer 思想
     flatten(TreeNode root, TreeNode tail)函数，让root preOrder遍历之后的末尾接上tail，然后返回root
     */
    public void flatten(TreeNode root) {
        //corner case
        if(root == null) {
            return;
        }
        flatten(root, null);
    }

    private TreeNode flatten(TreeNode root, TreeNode tail){
        // corner case
        if(root == null) {
            return tail; // ??为什么？
        }

        TreeNode right = flatten(root.right, tail);
        TreeNode left = flatten(root.left, right);
        root.right = left;
        root.left = null;
        return root;
    }

}
}