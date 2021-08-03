//Given the root of a binary tree, return an array of the largest value in each 
//row of the tree (0-indexed). 
//
// 
//
// 
// Example 1: 
//
// 
//Input: root = [1,3,2,5,3,null,9]
//Output: [1,3,9]
// 
//
// Example 2: 
//
// 
//Input: root = [1,2,3]
//Output: [1,3]
// 
//
// Example 3: 
//
// 
//Input: root = [1]
//Output: [1]
// 
//
// Example 4: 
//
// 
//Input: root = [1,null,2]
//Output: [1,2]
// 
//
// Example 5: 
//
// 
//Input: root = []
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree will be in the range [0, 104]. 
// -231 <= Node.val <= 231 - 1 
// 
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 1040 👎 67

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-10-19 16:59:52
// Zeshi Yang
public class Leetcode0515FindLargestValueInEachTreeRow{
    // Java: find-largest-value-in-each-tree-row
    public static void main(String[] args) {
        Solution sol = new Leetcode0515FindLargestValueInEachTreeRow().new Solution();
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer levelMax = null;
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                levelMax = levelMax == null ? cur.val : Math.max(levelMax, cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(levelMax);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}