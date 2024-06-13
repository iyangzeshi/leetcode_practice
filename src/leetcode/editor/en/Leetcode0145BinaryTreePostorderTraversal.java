//Given a binary tree, return the postorder traversal of its nodes' values. 
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
//Output: [3,2,1]
// 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Stack Tree 
// 👍 1861 👎 94

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-08-04 11:35:56
// Jesse Yang
public class Leetcode0145BinaryTreePostorderTraversal{
    // Java: binary-tree-postorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0145BinaryTreePostorderTraversal().new Solution();
		// TO TEST
        String str = "10,6,12,4,8,11,13,3,5,7";
        TreeNode root = TreeGenerator.deserialize(str);
        TreeDrawer.draw(root);
        List<Integer> res = sol.postorderTraversal(root);
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
    
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return output;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            // 先一直往下走，能往左边走往左边走，否则往右边走
            TreeNode top = stack.peek();
            TreeNode left = top.left;
            TreeNode right = top.right;
            if (left != null) { // 有left child,可能也有right child
                stack.push(left);
            } else if (right != null) { // 只有right child
                int cur = stack.pop().val;
                output.add(cur);
                stack.push(right);
            } else { // 左右子树都没有
                // 把路径里面遍历过的没有right child的ancestor给pop()出来
                TreeNode parent = stack.pop();
                output.add(parent.val);
                while (!stack.isEmpty() && parent.right == null) {
                    parent = stack.pop();
                    output.add(parent.val);
                }
                if (parent.right != null) {
                    stack.push(parent.right);
                }
            }
        }
        return output;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// 推荐答案Solution 1, 3_1, 3_2
// Solution 1: recursion
class Solution1 {
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }
    
    private void postorderTraversal(TreeNode root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        // general case
        postorderTraversal(root.left, res);
        postorderTraversal(root.right, res);
        res.add(root.val);
    }
    
}
// Solution 2: while loop.
// Solution 2_1: O(h)老刘给的模板，先走右边再左边,最后得到res再reverse,不推荐
class Solution2_1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null){
                stack.push(root);
                res.add(root.val);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
        Collections.reverse(res);
        return res;
    }
}

// Solution 2_2: O(2h) leetcode的答案，res用LinkedList，弹栈的时候addFirst()，然后顺序把左右child加进去
class Solution2_2 {
    
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
    
        if (root == null) {
            return output;
        }
        
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            output.addFirst(root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        return output;
    }
}

// Solution 3_1: Stack 先一直往左走走到底，不能走的时候pop并加入他的右邻居
/*
Stack 先一直往左走走到底，不能走的时候pop，并加入result
    如果自己是父节点的left，就stack push父节点的right
    如果本身就是父节点的right，什么都不做
 */
class Solution3_1 {
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        
        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) { // 一直往下走,优先走左边
            stack.push(cur);
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            res.add(cur.val);
            if (!stack.isEmpty() && cur != stack.peek().right) {
                cur = stack.peek().right;
                while (cur != null) { // 一直往下走,优先走左边
                    stack.push(cur);
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur = cur.right;
                    }
                }
            }
        }
        return res;
    }
    
}

// Solution 3_2: O(h)算法哥的模板,大概思路同上,弹栈的时候,把val加到res里面, 但是keep一个prev来确定这是往下走还是往上走
/*
    如果是往下走, prev来确定这个是parent的左子树还是右子树
        如果是左子树,就继续往下走,优先走左边
        如果是右子树,也继续往下走,优先走左边
    如果是往上走,确定prev是cur的左子树还是右子树
        如果是左子树,
            如果右子树不为空,走右子树(之后优先走左边)
            如果柚子树为空,弹栈+ 更新res
        如果是右子树,
            说明右子树走完了,弹栈+ 更新res
 */
class Solution3_2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (prev == null || prev.left == cur || prev.right == cur) { // 往下走
                if (cur.left != null) { // 左边不为空,走左边
                    stack.push(cur.left);
                } else if (cur.right != null) { // 左边为空,而且右边不为空,走右边
                    stack.push(cur.right);
                } else { // 左边和右边都是空,弹栈 + 更新res
                    res.add(stack.pop().val);
                }
            } else if (prev == cur.left){ // 往上走,且prev是cur的左子树
                if (cur.right != null) { // 右边不为空,走右边
                    stack.push(cur.right);
                } else { // 右边为空,弹栈 + 更新res
                    res.add(stack.pop().val);
                }
            } else { // prev == cur.right // 往上走,且prev是cur的右子树,说明下面走完了,更新res
                res.add(stack.pop().val);
            }
            prev = cur;
        }

        return res;
    }

}

// Solution 4: O(2h) 往下走的时候，先压right，再压left
class Solution4 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        // general case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.push(cur);
        while (cur != null) {
            TreeNode next = null;
            if (cur.right != null) {
                stack.push(cur.right);
                next = cur.right;
            }
            if (cur.left != null) {
                stack.push(cur.left);
                next = cur.left;
            }
            cur = next;
        }
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            res.add(temp.val);

            /* cur is temp 's parent or right neighbor
                if cur is temp's parent, shall do nothing
                if cur is temp's right neighbor, do following
             */
            if (stack.isEmpty()) {
                break;
            }
            cur = stack.peek();
            if (cur.left == temp || cur.right == temp) {
                continue;
            }
            while (cur != null) {
                TreeNode next = null;
                if (cur.right != null) {
                    stack.push(cur.right);
                    next = cur.right;
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                    next = cur.left;
                }
                cur = next;
            }
        }
        return res;
    }

}

}