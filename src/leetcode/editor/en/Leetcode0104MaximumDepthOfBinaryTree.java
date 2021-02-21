//Given a binary tree, find its maximum depth. 
//
// The maximum depth is the number of nodes along the longest path from the root
// node down to the farthest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given binary tree [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// return its depth = 3. 
// Related Topics Tree Depth-first Search 
// 👍 2585 👎 75

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import leetcode.editor.TreeNode;

// 2020-07-26 11:43:32
// Zeshi Yang
public class Leetcode0104MaximumDepthOfBinaryTree{
    // Java: maximum-depth-of-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0104MaximumDepthOfBinaryTree().new Solution();
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
    
    public int maxDepth(TreeNode root) {
        //corner case
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: DFS recursion, T(n) = O(n), S(n) = O(h), lgn <= h <= n
// 0 ms,击败了100.00% 的Java用户, 39.3 MB,击败了21.72% 的Java用户
class Solution1_1 {
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
        
    }
    
}

// Solution 1_2: DFS using while and stack, T(n) = O(n), S(n) = O(h), lgn <= h <= n
// 1 ms,击败了14.50% 的Java用户, 39.2 MB,击败了37.42% 的Java用户
class Solution1_2 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int max = 1;
        // pre-processing
        while (cur != null) { // 一直往下走,优先走左边
            stack.push(cur);
            max = Math.max(max, stack.size());
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (!stack.isEmpty() && cur != stack.peek().right) {
                cur = stack.peek().right;
                while (cur != null) { // 一直往下走,优先走左边
                    stack.push(cur);
                    max = Math.max(max, stack.size());
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur = cur.right;
                    }
                }
            }
        }
        return max;
    }
}

// Solution 2: BFS, T(n) = O(n), S(n) = O(h), lgn <= h <= n
// 1 ms,击败了14.50% 的Java用户, 38.8 MB,击败了85.92% 的Java用户
class Solution2 {
    
    public int maxDepth(TreeNode root) {
        //corner case
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
    
}
}