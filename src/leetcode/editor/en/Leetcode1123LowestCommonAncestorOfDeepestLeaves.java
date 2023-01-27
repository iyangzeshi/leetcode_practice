
/**
Given the root of a binary tree, return the lowest common ancestor of its 
deepest leaves. 

 Recall that: 

 
 The node of a binary tree is a leaf if and only if it has no children 
 The depth of the root of the tree is 0. if the depth of a node is d, the depth 
of each of its children is d + 1. 
 The lowest common ancestor of a set S of nodes, is the node A with the largest 
depth such that every node in S is in the subtree with root A. 
 

 
 Example 1: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest leaf-nodes of the tree.
Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, 
but the depth of nodes 7 and 4 is 3. 

 Example 2: 

 
Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree, and it's the lca of 
itself.
 

 Example 3: 

 
Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest leaf node in the tree is 2, the lca of one node is 
itself.
 

 
 Constraints: 

 
 The number of nodes in the tree will be in the range [1, 1000]. 
 0 <= Node.val <= 1000 
 The values of the nodes in the tree are unique. 
 

 
 Note: This question is the same as 865: https://leetcode.com/problems/smallest-
subtree-with-all-the-deepest-nodes/ 

 Related Topics Hash Table Tree Depth-First Search Breadth-First Search Binary 
Tree 👍 1608 👎 781

*/
package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2023-01-24 16:41:06
// Jesse Yang
public class Leetcode1123LowestCommonAncestorOfDeepestLeaves{
    // Java: lowest-common-ancestor-of-deepest-leaves
    public static void main(String[] args) {
        Solution sol = new Leetcode1123LowestCommonAncestorOfDeepestLeaves().new Solution();
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

// T(n) = O(n), S(n) = O(n)
// 找两边subtree的是深度，如果两边的深度都是maxDepth的话，就更新LCA，否则不更新
class Solution {
	
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		int[] maxDepth = new int[1];
		
		TreeNode[] lca = new TreeNode[1];
		getDepth(root, 0, maxDepth, lca);
		return lca[0];
	}
	
	private int getDepth(TreeNode node, int depth, int[] maxDepth, TreeNode[] lca) {
		maxDepth[0] = Math.max(maxDepth[0], depth);
		if (node == null) {
			return depth;
		}
		int left = getDepth(node.left, depth + 1, maxDepth, lca);
		int right = getDepth(node.right, depth + 1, maxDepth, lca);
		if (left == maxDepth[0] && right == maxDepth[0]) {
			lca[0] = node;
		}
		return Math.max(left, right);
	}
}
//leetcode submit region end(Prohibit modification and deletion)

// T(n) = O(n), S(n) = O(n)
/** step 1: find the max depth
 *  step 2: traverse the tree, find the TreeNode with max depth and find their LCA
 *
 */

class Solution1 {
	
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		if (root == null) {
			return root;
		}
		int[] deepest = new int[1];
		getDepth(root, 0, deepest);
		// return helper(root,0);
		return findLca(root, 0, deepest);
	}
	
	private void getDepth(TreeNode root, int depth, int[] deepest) {
		if (root == null) {
			deepest[0] = Math.max(deepest[0], depth - 1);
			return;
		}
		getDepth(root.left, depth + 1, deepest);
		getDepth(root.right, depth + 1, deepest);
	}
	
	private TreeNode findLca(TreeNode root, int depth, int[] deepest) {
		if (root != null && depth == deepest[0]) {
			return root;
		}
		if (root == null) {
			return null;
		}
		TreeNode left = findLca(root.left, depth + 1, deepest);
		TreeNode right = findLca(root.right, depth + 1, deepest);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}
}

// T(n) = O(n), S(n) = O(n)
// 找两边subtree的是深度，如果两边的深度都是maxDepth的话，就更新LCA，否则不更新
class Solution2 {
	
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		int[] maxDepth = new int[1];
		
		TreeNode[] lca = new TreeNode[1];
		getDepth(root, 0, maxDepth, lca);
		return lca[0];
	}
	
	private int getDepth(TreeNode node, int depth, int[] maxDepth, TreeNode[] lca) {
		maxDepth[0] = Math.max(maxDepth[0], depth);
		if (node == null) {
			return depth;
		}
		int left = getDepth(node.left, depth + 1, maxDepth, lca);
		int right = getDepth(node.right, depth + 1, maxDepth, lca);
		if (left == maxDepth[0] && right == maxDepth[0]) {
			lca[0] = node;
		}
		return Math.max(left, right);
	}
}

}
