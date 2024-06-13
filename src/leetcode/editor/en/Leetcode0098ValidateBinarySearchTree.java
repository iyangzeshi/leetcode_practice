//Given a binary tree, determine if it is a valid binary search tree (BST). 
//
// Assume a BST is defined as follows: 
//
// 
// The left subtree of a node contains only nodes with keys less than the node's
// key. 
// The right subtree of a node contains only nodes with keys greater than the no
//de's key. 
// Both the left and right subtrees must also be binary search trees. 
// 
//
// 
//
// Example 1: 
//
// 
//    2
//   / \
//  1   3
//
//Input: [2,1,3]
//Output: true
// 
//
// Example 2: 
//
// 
//    5
//   / \
//  1   4
//     / \
//    3   6
//
//Input: [5,1,4,null,null,3,6]
//Output: false
//Explanation: The root node's value is 5 but its right child's value is 4.
// 
// Related Topics Tree Depth-first Search 
// 👍 3861 👎 533

package leetcode.editor.en;

import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-09 16:31:15
public class Leetcode0098ValidateBinarySearchTree {

	// Java: validate-binary-search-tree
	public static void main(String[] args) {
		Solution sol = new Leetcode0098ValidateBinarySearchTree().new Solution();
		// TO TEST
		TreeNode root = TreeGenerator.deserialize("1,1");
		boolean res = sol.isValidBST(root);
		System.out.println(res);
	}
//leetcode submit region begin(Prohibit modification and deletion)
//  Definition for a binary tree node.
/*public class TreeNode {

   int val;
   TreeNode left;
   TreeNode right;

   TreeNode() {
   }

   TreeNode(int val) {
       this.val = val;
   }

   TreeNode(int val, TreeNode
           left,
           TreeNode right) {
       this.val = val;
       this.left = left;
       this.right = right;
   }
}*/
class Solution {

    class subTreeStatus {

        Integer min;
        Integer max;
        boolean isValidBST;

        public subTreeStatus(Integer min, Integer max, boolean isValidBST) {
            this.min = min;
            this.max = max;
            this.isValidBST = isValidBST;
        }
    }

    public boolean isValidBST(TreeNode root) {
        subTreeStatus res = dfs(root);
        return res.isValidBST;
    }

    private subTreeStatus dfs(TreeNode root) {
        // base case
        if (root == null) {
            return new subTreeStatus(null, null, true);
        }
        subTreeStatus left = dfs(root.left);
        if (!left.isValidBST || (left.min != null && root.val <= left.min) ||
                (left.max != null && root.val <= left.max)) {
            return new subTreeStatus(null, null, false);
        }

        subTreeStatus right = dfs(root.right);
        if (!right.isValidBST || (right.min != null && root.val >= right.min) || (
                right.max != null && root.val >= right.max)) {
            return new subTreeStatus(null, null, false);
        }
        Integer min = left.min;
        if (left.min == null) {
            min = root.val;
        }
        Integer max = right.max;
        if (right.max == null) {
            max = root.val;
        }
        return new subTreeStatus(min, max, true);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: tail recursion, top to bottom, carry lower bound and upper bound from bottom to top
class Solution1 {

    public boolean isValidBST(TreeNode root) {
        //corner case
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, Integer lowerBound, Integer upperBound) {
        // base case - success
        if (root == null) {
            return true;
        }
        // base case - failure
        if ((lowerBound != null && root.val <= lowerBound) ||
                (upperBound != null && root.val >= upperBound)) {
            return false;
        }
        
        return isBST(root.left, lowerBound, root.val) && isBST(root.right, root.val, upperBound);
    }
}

// Solution 2: recursion, post-order Traversal, bottom up.
class Solution2 {
    // Jesse Yang's code
    class subTree {

        Integer min;
        Integer max;
        boolean isValidBST;

        public subTree(Integer min, Integer max, boolean isValidBST) {
            this.min = min;
            this.max = max;
            this.isValidBST = isValidBST;
        }
    }

    public boolean isValidBST(TreeNode root) {
        subTree res = dfs(root);
        return res.isValidBST;
    }

    private subTree dfs(TreeNode root) {
        // base case
        if (root == null) {
            return new subTree(null, null, true);
        }
        subTree left = dfs(root.left);
        if (!left.isValidBST || (left.min != null && root.val <= left.min) ||
                (left.max != null && root.val <= left.max)) {
            return new subTree(null, null, false);
        }

        subTree right = dfs(root.right);
        if (!right.isValidBST || (right.min != null && root.val >= right.min) || (
                right.max != null && root.val >= right.max)) {
            return new subTree(null, null, false);
        }
        Integer min = left.min;
        if (left.min == null) {
            min = root.val;
        }
        Integer max = right.max;
        if (right.max == null) {
            max = root.val;
        }
        return new subTree(min, max, true);
    }
}

// Solution 3: inOrder Traversal, increasing order
class Solution3 {

    public boolean isValidBST(TreeNode root) {
        return dfs(root, new Integer[1]);
    }

    private boolean dfs(TreeNode root, Integer[] prev) {
        // base case - success
        if (root == null) {
            return true;
        }
        
        if (!dfs(root.left, prev)) {
            return false;
        }
        if (prev[0] != null && prev[0] >= root.val) {
            return false;
        }
        prev[0] = root.val;
        return dfs(root.right, prev);
    }
}

}