//
//Given a binary tree, find the leftmost value in the last row of the tree. 
// 
//
// Example 1: 
// 
//Input:
//
//    2
//   / \
//  1   3
//
//Output:
//1
// 
// 
//
// Example 2: 
// 
//Input:
//
//        1
//       / \
//      2   3
//     /   / \
//    4   5   6
//       /
//      7
//
//Output:
//7
// 
// 
//
// Note:
//You may assume the tree (i.e., the given root node) is not NULL.
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 1077 👎 152

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-10-18 22:50:45
// Jesse Yang
public class Leetcode0513FindBottomLeftTreeValue{
    // Java: find-bottom-left-tree-value
    public static void main(String[] args) {
        Solution sol = new Leetcode0513FindBottomLeftTreeValue().new Solution();
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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- >0) {
                TreeNode cur = queue.poll();
                res = cur.val;
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}