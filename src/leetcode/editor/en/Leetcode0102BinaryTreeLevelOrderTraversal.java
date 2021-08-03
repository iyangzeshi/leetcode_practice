//Given a binary tree, return the level order traversal of its nodes' values. (i
//e, from left to right, level by level). 
//
// 
//For example: 
//Given binary tree [3,9,20,null,null,15,7], 
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// 
// 
//return its level order traversal as: 
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics Tree Breadth-first Search 
// 👍 3102 👎 77

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.TreeNode;

// 2020-07-26 12:00:06
// Zeshi Yang
public class Leetcode0102BinaryTreeLevelOrderTraversal{
    // Java: binary-tree-level-order-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0102BinaryTreeLevelOrderTraversal().new Solution();
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            int size = queue.size();

            while (size --> 0) {
                TreeNode cur = queue.poll();
                tempList.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            res.add(tempList);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}