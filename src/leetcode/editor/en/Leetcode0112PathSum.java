//Given a binary tree and a sum, determine if the tree has a root-to-leaf path s
//uch that adding up all the values along the path equals the given sum. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given the below binary tree and sum = 22, 
//
// 
//      5
//     / \
//    4   8
//   /   / \
//  11  13  4
// /  \      \
//7    2      1
// 
//
// return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22. 
//
// Related Topics Tree Depth-first Search 
// 👍 1950 👎 498

package leetcode.editor.en;

import java.util.Stack;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-23 11:19:52
// Jesse Yang
public class Leetcode0112PathSum{
    // Java: path-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0112PathSum().new Solution();
        // TreeGenerator tg = new TreeGenerator();
        TreeNode root = TreeGenerator.deserialize("1,-2,-3,1,3,-2,null,-1");
        // TO TEST
        System.out.println(sol.hasPathSum(root,-4));
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
stack 来模拟递归
step 1: 往左边走，一直走到最左边
step 2: stack中pop出一个元素 cur
    step 2.1: 如果这个cur是top的left child，那么stack中push这个cur的right child,然后再一直push它的左子树
    step 2.2: 如果这个cur是top的right child，什么都不做
 */


class Solution {
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        
        nodeStack.push(root);
        sumStack.push(root.val);
        
        while (!nodeStack.isEmpty()) {
            TreeNode current = nodeStack.pop();
            int currentSum = sumStack.pop();
            
            if (current.left == null && current.right == null && currentSum == sum) {
                return true;
            }
            
            if (current.right != null) {
                nodeStack.push(current.right);
                sumStack.push(currentSum + current.right.val);
            }
            
            if (current.left != null) {
                nodeStack.push(current.left);
                sumStack.push(currentSum + current.left.val);
            }
        }
        
        return false;
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)
class Solution1_1 {
    public boolean hasPathSum(TreeNode root, int sum) {
        // base case
        if (root == null) {
            return false;
        }
        // general case
        if(root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
class Solution1_2 {
    public boolean hasPathSum(TreeNode root, int sum) {
        // corner case
        if (root == null) {
            return false;
        }

        // general case
        return hashPathSum(root, 0, sum);
    }

    private boolean hashPathSum(TreeNode root, int curSum, int sum) {
        // base case
        if (root == null) {
            return false;
        }
        // general case
        curSum += root.val;
        if (root.left == null && root.right == null) {
            if (curSum == sum) {
                return true;
            }
        }
        return hashPathSum(root.left, curSum, sum) || hashPathSum(root.right, curSum, sum);
    }
}

// Solution 2: 用stack来模拟DFS
class Solution2 {
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        // edge case
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int pathSum = 0;
        pathSum += root.val;
        while (root.left != null || root.right != null) {
            if (root.left != null) {
                stack.push(root.left);
                root = root.left;
                pathSum += root.val;
            } else {
                stack.push(root.right);
                root = root.right;
                pathSum += root.val;
            }
        }
        if (pathSum == sum) {
            return true;
        }
        
        // general case
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            pathSum -= cur.val;
            
            TreeNode top = null;
            if (!stack.isEmpty()) {
                top = stack.peek();
            } else {
                return false;
            }
            
            if (top.left == cur) {
                if (top.right != null) {
                    cur = top.right;
                    pathSum += cur.val;
                    stack.push(cur);
                    while (cur.left != null || cur.right != null) {
                        if (cur.left != null) {
                            stack.push(cur.left);
                            pathSum += cur.left.val;
                            cur = cur.left;
                        } else if (cur.right != null) {
                            stack.push(cur.right);
                            pathSum += cur.right.val;
                            cur = cur.right;
                        }
                    }
                    if (cur.left == null && cur.right == null && pathSum == sum) {
                        return true;
                    }
                }
            } else { // top.right == cur
                // do nothing
            }
        }
        return false;
    }
}
}