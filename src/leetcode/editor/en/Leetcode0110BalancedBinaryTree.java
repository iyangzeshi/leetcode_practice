//Given a binary tree, determine if it is height-balanced. 
//
// For this problem, a height-balanced binary tree is defined as: 
//
// 
// a binary tree in which the left and right subtrees of every node differ in he
//ight by no more than 1. 
// 
//
// 
//
// Example 1: 
//
// Given the following tree [3,9,20,null,null,15,7]: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// Return true. 
// 
//Example 2: 
//
// Given the following tree [1,2,2,3,3,null,null,4,4]: 
//
// 
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// Return false. 
// Related Topics Tree Depth-first Search 
// 👍 2314 👎 172

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 11:47:11
// Jesse Yang
public class Leetcode0110BalancedBinaryTree{
    // Java: balanced-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0110BalancedBinaryTree().new Solution();
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
/*
bottom up
找到左右子树的高度，check difference是否满足要求，
如果满足要求，返回当前Node的height，如果不满足要求，就返回-1
对所有的子树做相同的operation
 */
// T(n) = O(n), S(n) = O(h), h is height of the tree
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int result =  getHeight(root);
        return result != -1;
    
    }
    
    /*
   如果这个点开始的树是平衡树，就返回高度
   如果这个点开始的树不是平衡树，就返回-1
   */
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
找到左右子树的高度，check difference是否满足要求，对所有的子树做相同的operation
 */
// T(n) = O(n^2), S(n) = O(h)
class Solution1_1 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        //先检查corner case，会快一点
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }
}

/*
找到左右子树的高度，check difference是否满足要求，对所有的子树做相同的operation
 */
// T(n) = O(n^2), S(n) = O(h)
class Solution1_2 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        //先检查corner case，会快一点
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);

    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -2 || right == -2 || Math.abs(left - right) > 1) {
            return -2;
            //这里不能设置成-1，否则想linkedlist的tree会多call很多次，一个treeNode，左边是0，右边是-1
        }
        return Math.max(left, right) + 1;
    }
}

/*
bottom up
找到左右子树的高度，check difference是否满足要求，
如果满足要求，返回当前Node的height，如果不满足要求，就返回-1
对所有的子树做相同的operation
 */
// T(n) = O(n), S(n) = O(h), h is height of the tree
class Solution2 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int result =  getHeight(root);
        return result != -1;
    
    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
}