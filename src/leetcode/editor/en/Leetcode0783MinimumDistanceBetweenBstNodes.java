//Given a Binary Search Tree (BST) with the root node root, return the minimum d
//ifference between the values of any two different nodes in the tree. 
//
// Example : 
//
// 
//Input: root = [4,2,6,1,3,null,null]
//Output: 1
//Explanation:
//Note that root is a TreeNode object, not an array.
//
//The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
//
//          4
//        /   \
//      2      6
//     / \    
//    1   3  
//
//while the minimum difference in this tree is 1, it occurs between node 1 and n
//ode 2, also between node 3 and node 2.
// 
//
// Note: 
//
// 
// The size of the BST will be between 2 and 100. 
// The BST is always valid, each node's value is an integer, and each node's val
//ue is different. 
// This question is the same as 530: https://leetcode.com/problems/minimum-absol
//ute-difference-in-bst/ 
// 
// Related Topics Tree Recursion 
// 👍 833 👎 226

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-11-18 15:57:26
// Zeshi Yang
public class Leetcode0783MinimumDistanceBetweenBstNodes{
    // Java: minimum-distance-between-bst-nodes
    public static void main(String[] args) {
        Solution sol = new Leetcode0783MinimumDistanceBetweenBstNodes().new Solution();
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
	
	public int minDiffInBST(TreeNode root) {
		TreeNode[] prev = new TreeNode[1];
		int[] min = {Integer.MAX_VALUE};
		getMin(root, prev, min);
		return min[0];
	}
	
	private void getMin(TreeNode root, TreeNode[] prev, int[] min) {
		// base case
		if (root == null) {
			return;
		}
		// do sth for left
		getMin(root.left, prev, min);
		
		// do sth for current node
		// base case
		if (prev[0] != null) {
			min[0] = Math.min(min[0], root.val - prev[0].val);
		}
		prev[0] = root;
		// do sth for right
		getMin(root.right, prev, min);
	}
}
//leetcode submit region end(Prohibit modification and deletion)
}
