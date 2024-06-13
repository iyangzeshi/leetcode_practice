//Given a binary tree, return the preorder traversal of its nodes' values. 
//
// Example: 
//
// 
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,2,3]
// 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Stack Tree 
// 👍 1556 👎 58

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-23 00:58:32
// Jesse Yang
public class Leetcode0144BinaryTreePreorderTraversal {

	// Java: binary-tree-preorder-traversal
	public static void main(String[] args) {
		Solution sol = new Leetcode0144BinaryTreePreorderTraversal().new Solution();
		// TO TEST
        String str = "10,6,12,4,8,11,13,3,5,7";
        TreeNode root = TreeGenerator.deserialize(str);
        TreeDrawer.draw(root);
        List<Integer> res = sol.preorderTraversal(root);
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
class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // corner case
        if (root == null) {
            return result;
        }

        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: recursion
class Solution1 {

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        // corner case
        if (root == null) {
            return res;
        }

        // general case
        preorderTraversal(root, res);
        return res;
    }

    private void preorderTraversal(TreeNode root, LinkedList<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        // general case
        res.add(root.val);
        preorderTraversal(root.left, res);
        preorderTraversal(root.right, res);
    }
}

// Solution 2: iteration according to the definition of preOrder。 S(n) = O(2h)
/*
其实就是按recursion的写法，按照他的思路写stack，每次遇到点，先把这个点加到result里面。
recursion里面是先处理left，再处理right，按照stack后入先出的特点，先压right，再压left
 */
class Solution2 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;

    }
}

// Solution 3_1: 思路同上,就是先preprocessing. S(n) = O(h)
/*
好记一点,什么都不管,先一直往左走,到底的时候往右走。
入栈的时候，加到result里面
 */
class Solution3_1 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }

        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            res.add(cur.val);
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            cur = cur.right;
            while (cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
}

// Solution 3_2: according to preOrder template. S(n) = O(h)
/*
实际上也很好记，就是入栈的时候，把元素的值加进去，然后让root一直往左走，
走到null的时候，pop一下再往右走，如此循环往复
 */
class Solution3_2 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // corner case
        if (root == null) {
            return result;
        }

        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return result;
    }

}

}