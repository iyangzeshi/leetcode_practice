//Given a binary tree, determine if it is a complete binary tree. 
//
// Definition of a complete binary tree from Wikipedia: 
//In a complete binary tree every level, except possibly the last, is completely
// filled, and all nodes in the last level are as far left as possible. It can hav
//e between 1 and 2h nodes inclusive at the last level h. 
//
// 
//
// Example 1: 
//
// 
//
// 
//Input: [1,2,3,4,5,6]
//Output: true
//Explanation: Every level before the last is full (ie. levels with node-values 
//{1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as 
//possible.
// 
//
// 
// Example 2: 
//
// 
//
// 
//Input: [1,2,3,4,5,null,7]
//Output: false
//Explanation: The node with value 7 isn't as far left as possible.
// 
//
// 
// 
//
// Note: 
//
// 
// The tree will have between 1 and 100 nodes. 
// 
// Related Topics Tree 
// 👍 805 👎 12

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-07-26 12:03:06
// Zeshi Yang
public class Leetcode0958CheckCompletenessOfABinaryTree{
    // Java: check-completeness-of-a-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0958CheckCompletenessOfABinaryTree().new Solution();
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
设置boolean hashNull，每一层从左往右遍历，有Null的时候，hashNull设置成true，
然后继续遍历，如果又遇到Null，就return false，否则，return true
 */
class Solution {
    
    public boolean isCompleteTree(TreeNode root) {
        //corner case
        if (root == null) {
            return true;
        }
        boolean hasNull = false;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node;
        queue.offer(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            
            // node.left
            if (node.left == null) {
                hasNull = true;
            } else {
                if (hasNull) {
                    return false;
                }
                queue.offer(node.left);
                
            }
            // node.right
            if (node.right == null) {
                hasNull = true;
            } else {
                if (hasNull) {
                    return false;
                }
                queue.offer(node.right);
                
            }
            
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}