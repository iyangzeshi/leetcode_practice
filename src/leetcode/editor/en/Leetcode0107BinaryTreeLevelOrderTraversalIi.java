//Given a binary tree, return the bottom-up level order traversal of its nodes' 
//values. (ie, from left to right, level by level from leaf to root). 
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
//return its bottom-up level order traversal as: 
// 
//[
//  [15,7],
//  [9,20],
//  [3]
//]
// 
// Related Topics Tree Breadth-first Search 
// 👍 1514 👎 214

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.TreeNode;

// 2020-07-26 12:00:24
// Jesse Yang
public class Leetcode0107BinaryTreeLevelOrderTraversalIi{
    // Java: binary-tree-level-order-traversal-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0107BinaryTreeLevelOrderTraversalIi().new Solution();
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
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		LinkedList<List<Integer>> result = new LinkedList<>();
		// corner case
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new ArrayList<>();
			while (size != 0) {
				TreeNode node = queue.poll();
				list.add(node.val);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
				size--;
			}
			result.addFirst(list);
		}
		return result;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}