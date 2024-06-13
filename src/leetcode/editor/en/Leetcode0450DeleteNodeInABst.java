//Given a root node reference of a BST and a key, delete the node with the given
// key in the BST. Return the root node reference (possibly updated) of the BST. 
//
// Basically, the deletion can be divided into two stages:
// 
// Search for a node to remove. 
// If the node is found, delete the node. 
// 
// 
//
// Note: Time complexity should be O(height of tree). 
//
// Example:
// 
//root = [5,3,6,2,4,null,7]
//key = 3
//
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Given key to delete is 3. So we find the node with value 3 and delete it.
//
//One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
//
//    5
//   / \
//  4   6
// /     \
//2       7
//
//Another valid answer is [5,2,6,null,4,null,7].
//
//    5
//   / \
//  2   6
//   \   \
//    4   7
// 
// Related Topics Tree 
// 👍 1801 👎 84

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 14:13:17
// Jesse Yang
public class Leetcode0450DeleteNodeInABst {
	
	// Java: delete-node-in-a-bst
	public static void main(String[] args) {
		Solution sol = new Leetcode0450DeleteNodeInABst().new Solution();
		// TO TEST
		
		System.out.println();
	}
//leetcode submit region begin(Prohibit modification and deletion)
/*
  Definition for a binary tree node.
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 */
// Solution : DFS to find the node and delete it T(n) = O(lg(n)), S(n) = O(lg(n))

/**
 * pre order 思路 to find the target node, delete it and using the largest left child to replace it
 * case 1: root.val == target
 * 		case 1.1: if root has both lchild and rchild, find smallest
 * element from right subtree( or find the largest element from the left subtree), say x, assign
 * x's value to root, remove x's value from the right subtree by recursion.
 * 		case 1.2: if root
 * has only one child, iff has one child, replace root with non-null child / parent connect
 * directly to child.
 * 		case 1.3: if root has no child, remove self / root = null;
 *
 * case 2: root.val > target, go to left subtree by recursion
 *
 * case 3: root.val < target, go to right subtree by recursion
 */
// pre order 思路
class Solution {
    
    // delete the node from the tree and return the root
    public TreeNode deleteNode(TreeNode root, int key) {
        // corner case
        if (root == null) {
            return root;
        }
        
        // base case
        if (root.val == key) { // case 1
            if (root.left != null && root.right != null) { // case 1.1
                root.val = findMin1(root.right).val;
                root.right = deleteNode(root.right, root.val);
            } else { // case 1.2 and case 1.3
                root = (root.left != null ? root.left : root.right);
            }
        } else if (root.val > key) { // case 2
            root.left = deleteNode(root.left, key);
        } else { // case 3
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
    
    // by iteration
    private TreeNode findMin1(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
    
    // by recursion
    private TreeNode findMin2(TreeNode root) {
        // corner case
        if (root.left == null) {
            return root;
        }
        return findMin2(root.left);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}