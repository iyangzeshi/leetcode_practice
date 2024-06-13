//Given a binary tree, return the inorder traversal of its nodes' values. 
//
// Example: 
//
// 
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,3,2] 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Hash Table Stack Tree 
// 👍 3286 👎 138

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-08-04 11:30:17
// Jesse Yang
public class Leetcode0094BinaryTreeInorderTraversal{
    // Java: binary-tree-inorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0094BinaryTreeInorderTraversal().new Solution();
        // TO TEST
        String str = "10,6,12,4,8,11,13,3,5,7";
        TreeNode root = TreeGenerator.deserialize(str);
        TreeDrawer.draw(root);
        List<Integer> res = sol.inorderTraversal(root);
        System.out.println(res);
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
// Solution 2_1: stack, 原理同上，按照自己的想法，先一直往左走走到底，不能走的时候pop并加入他的右子树
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        while (root != null | !stack.isEmpty()) {
            if (root == null) {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: recursion
class Solution1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    private void inorderTraversal(TreeNode root, List<Integer> res) {
        // corner case
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, res);
        res.add(root.val);
        inorderTraversal(root.right, res);
    }
}

// Solution 2_1: stack, 原理同上，按照自己的想法，先一直往左走走到底，不能走的时候pop并加入他的右子树
class Solution2_1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
}

// Solution 2_2: stack, 上课的模板
/*
实际上也很好记，就是入栈的时候，把元素的值加进去，然后让root一直往左走，
走到null的时候，pop一下再往右走，如此循环往复.
出栈的时候，把node的val加到result里面
 */
class Solution2_2 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}


}