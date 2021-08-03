//Given a binary tree, find the largest subtree which is a Binary Search Tree (B
//ST), where largest means subtree with largest number of nodes in it. 
//
// Note: 
//A subtree must include all of its descendants. 
//
// Example: 
//
// 
//Input: [10,5,15,1,8,null,7]
//
//   10 
//   / \ 
//  5  15 
// / \   \ 
//1   8   7
//
//Output: 3
//Explanation: The Largest BST Subtree in this case is the highlighted one.
//             The return value is the subtree's size, which is 3.
// 
//
// Follow up: 
//Can you figure out ways to solve it with O(n) time complexity? 
// Related Topics Tree 
// 👍 595 👎 57

package leetcode.editor.en;

import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-12 13:48:30
public class Leetcode0333LargestBstSubtree {

	// Java: largest-bst-subtree
	public static void main(String[] args) {
		Solution sol = new Leetcode0333LargestBstSubtree().new Solution();
		// TO TEST
		String str = "10,5,15,1,8,null,7";
		TreeNode root = TreeGenerator.deserialize(str);
		TreeDrawer.draw(root);
		int res = sol.largestBSTSubtree(root);
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

	class Cell {

		int min;
		int max;
		int size;
		boolean isBST;

		public Cell(int min, int max, int size, boolean isBST) {
			this.min = min;
			this.max = max;
			this.size = size;
			this.isBST = isBST;
		}
	}

	public int largestBSTSubtree(TreeNode root) {
		// corner case
		if (root == null) {
			return 0;
		}
		int[] count = new int[1];
		dfs(root, count);
		return count[0];
	}

	private Cell dfs(TreeNode root, int[] count) {
		// base case
		if (root == null) {
			return new Cell(Integer.MAX_VALUE, Integer.MIN_VALUE,0, true);
		}
		// general case
		Cell left = dfs(root.left, count);
		Cell right = dfs(root.right, count);
		if (left == null || right == null) {
			return null;
		}
		if (right.isBST && left.isBST && left.max < root.val && root.val < right.min) {
			int min = left.size > 0 ? left.min : root.val;
			int max = right.size > 0 ? right.max : root.val;
			int size = left.size + right.size + 1;
			count[0] = Math.max(count[0], size);
			return new Cell(min, max, size, true);
		} else {
			return null;
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)
}