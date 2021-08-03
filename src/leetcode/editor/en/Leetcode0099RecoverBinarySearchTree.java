//Two elements of a binary search tree (BST) are swapped by mistake. 
//
// Recover the tree without changing its structure. 
//
// Example 1: 
//
// 
//Input: [1,3,null,null,2]
//
//   1
//  /
// 3
//  \
//   2
//
//Output: [3,1,null,null,2]
//
//   3
//  /
// 1
//  \
//   2
// 
//
// Example 2: 
//
// 
//Input: [3,1,4,null,null,2]
//
//  3
// / \
//1   4
//   /
//  2
//
//Output: [2,1,4,null,null,3]
//
//  2
// / \
//1   4
//   /
//  3
// 
//
// Follow up: 
//
// 
// A solution using O(n) space is pretty straight forward. 
// Could you devise a constant space solution? 
// 
// Related Topics Tree Depth-first Search 
// 👍 1536 👎 72

package leetcode.editor.en;

import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-08 17:55:41
public class Leetcode0099RecoverBinarySearchTree {

	// Java: recover-binary-search-tree
	public static void main(String[] args) {
		Solution1 sol1 = new Leetcode0099RecoverBinarySearchTree().new Solution1();
		Solution2 sol2 = new Leetcode0099RecoverBinarySearchTree().new Solution2();
		
		// TO TEST
		String str = "8,4,11,2,6,9,13,3,1,5,7,8,10,12,14";
		TreeNode root;
		long startTime;
		long endTime;
		
		startTime = System.currentTimeMillis();   //获取开始时间
		for (int i = 0; i < 1000000; i++) {
			root = TreeGenerator.deserialize(str);
			sol1.recoverTree(root);
		}
		endTime = System.currentTimeMillis(); //获取结束时间
		System.out.println("程序1运行时间： " + (endTime - startTime) + "ms");
		
		startTime = System.currentTimeMillis();   //获取开始时间
		for (int i = 0; i < 1000000; i++) {
			root = TreeGenerator.deserialize(str);
			sol2.recoverTree(root);
		}
		endTime = System.currentTimeMillis(); //获取结束时间
		System.out.println("程序2运行时间： " + (endTime - startTime) + "ms");
	}
//leetcode submit region begin(Prohibit modification and deletion)

//  Definition for a binary tree node.
//  public class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode() {}
//      TreeNode(int val) { this.val = val; }
//      TreeNode(int val, TreeNode left, TreeNode right) {
//          this.val = val;
//          this.left = left;
//          this.right = right;
//      }
//  }
// Solution2 :
class Solution {

	public void recoverTree(TreeNode root) {
		TreeNode[] mistake = new TreeNode[2];
		TreeNode[] prev = new TreeNode[1];
		dfs(root, mistake, prev, new int[1]);

		if (mistake[0] != null && mistake[1] != null) {
			int temp = mistake[0].val;
			mistake[0].val = mistake[1].val;
			mistake[1].val = temp;
		}
	}

	private void dfs(TreeNode root, TreeNode[] mistake, TreeNode[] prev, int[] flag) {

		// base case
		if (root == null) {
			return;
		}

		dfs(root.left, mistake, prev, flag);
		if (prev[0] != null && prev[0].val > root.val) {
			// keep the first and last violate node
			mistake[1] = root;
			flag[0]++;
			if (mistake[0] == null) {
				mistake[0] = prev[0];
			}
		}
		prev[0] = root;
		if (flag[0] == 2) {
			return;
		}
		dfs(root.right, mistake, prev, flag);
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1:
class Solution1 {

	public void recoverTree(TreeNode root) {
		TreeNode[] mistake = new TreeNode[2];

		dfs(root, mistake, new TreeNode[1]);

		if (mistake[0] != null && mistake[1] != null) {
			int temp = mistake[0].val;
			mistake[0].val = mistake[1].val;
			mistake[1].val = temp;
		}
	}

	private void dfs(TreeNode root, TreeNode[] mistake, TreeNode[] prev) {

		// base case
		if (root == null) {
			return;
		}

		dfs(root.left, mistake, prev);
		if (prev[0] != null && prev[0].val > root.val) {
			// keep the first and last violate node
			mistake[1] = root;
			if (mistake[0] == null) {
				mistake[0] = prev[0];
			}
		}
		prev[0] = root;
		dfs(root.right, mistake, prev);
	}
}

class Solution2Wrong {
	
	public void recoverTree(TreeNode root) {
		TreeNode[] mistake = new TreeNode[2];
		
		dfs(root, mistake, new TreeNode[1]);
		
		if (mistake[0] != null && mistake[1] != null) {
			int temp = mistake[0].val;
			mistake[0].val = mistake[1].val;
			mistake[1].val = temp;
		}
	}
	
	private void dfs(TreeNode root, TreeNode[] mistake, TreeNode[] prev) {
		
		// base case
		if (root == null) {
			return;
		}
		
		dfs(root.left, mistake, prev);
		if (prev[0] != null && prev[0].val > root.val) {
			// keep the first and last violate node
			mistake[1] = root;
			if (mistake[0] == null) {
				mistake[0] = prev[0];
			}
		}
		prev[0] = root;
		dfs(root.right, mistake, prev);
	}
}

// Solution 2:
class Solution2 {
	
	public void recoverTree(TreeNode root) {
		TreeNode[] mistake = new TreeNode[2];
		TreeNode[] prev = new TreeNode[1];
		dfs(root, mistake, prev, new int[1]);
		
		if (mistake[0] != null && mistake[1] != null) {
			int temp = mistake[0].val;
			mistake[0].val = mistake[1].val;
			mistake[1].val = temp;
		}
	}
	
	private void dfs(TreeNode root, TreeNode[] mistake, TreeNode[] prev, int[] flag) {
		
		// base case
		if (root == null) {
			return;
		}
		
		dfs(root.left, mistake, prev, flag);
		if (prev[0] != null && prev[0].val > root.val) {
			// keep the first and last violate node
			mistake[1] = root;
			flag[0]++;
			if (mistake[0] == null) {
				mistake[0] = prev[0];
			}
		}
		prev[0] = root;
		if (flag[0] == 2) {
			return;
		}
		dfs(root.right, mistake, prev, flag);
	}
}
}