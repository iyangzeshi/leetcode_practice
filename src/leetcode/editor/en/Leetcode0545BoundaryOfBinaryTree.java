//Given a binary tree, return the values of its boundary in anti-clockwise direc
//tion starting from root. Boundary includes left boundary, leaves, and right boun
//dary in order without duplicate nodes. (The values of the nodes may still be dup
//licates.) 
//
// Left boundary is defined as the path from root to the left-most node. Right b
//oundary is defined as the path from root to the right-most node. If the root doe
//sn't have left subtree or right subtree, then the root itself is left boundary o
//r right boundary. Note this definition only applies to the input binary tree, an
//d not applies to any subtrees. 
//
// The left-most node is defined as a leaf node you could reach when you always 
//firstly travel to the left subtree if exists. If not, travel to the right subtre
//e. Repeat until you reach a leaf node. 
//
// The right-most node is also defined by the same way with left and right excha
//nged. 
//
// Example 1 
//
// 
//Input:
//  1
//   \
//    2
//   / \
//  3   4
//
//Ouput:
//[1, 3, 4, 2]
//
//Explanation:
//The root doesn't have left subtree, so the root itself is left boundary.
//The leaves are node 3 and 4.
//The right boundary are node 1,2,4. Note the anti-clockwise direction means you
// should output reversed right boundary.
//So order them in anti-clockwise without duplicates and we have [1,3,4,2].
// 
//
// 
//
// Example 2 
//
// 
//Input:
//    ____1_____
//   /          \
//  2            3
// / \          / 
//4   5        6   
//   / \      / \
//  7   8    9  10  
//       
//Ouput:
//[1,2,4,7,8,9,10,6,3]
//
//Explanation:
//The left boundary are node 1,2,4. (4 is the left-most node according to defini
//tion)
//The leaves are node 4,7,8,9,10.
//The right boundary are node 1,3,6,10. (10 is the right-most node).
//So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,1
//0,6,3].
// 
//
// 
// Related Topics Tree 
// 👍 550 👎 940

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;
import leetcode.editor.TreeNode;

// 2020-09-07 18:16:22
// Jesse Yang
public class Leetcode0545BoundaryOfBinaryTree {

	// Java: boundary-of-binary-tree
	public static void main(String[] args) {
		Solution sol = new Leetcode0545BoundaryOfBinaryTree().new Solution();
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
public class Solution {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> leftBoundary = new LinkedList<>();
        List<Integer> rightBoundary = new LinkedList<>();
        List<Integer> leaves = new LinkedList<>();

        preorder(root, leftBoundary, rightBoundary, leaves, 0);
        leftBoundary.addAll(leaves);
        leftBoundary.addAll(rightBoundary);
        return leftBoundary;
    }

    public boolean isLeaf(TreeNode cur) {
        return (cur.left == null && cur.right == null);
    }

    public boolean isRightBoundary(int flag) {
        return (flag == 2);
    }

    public boolean isLeftBoundary(int flag) {
        return (flag == 1);
    }

    public boolean isRoot(int flag) {
        return (flag == 0);
    }

    public int leftChildFlag(TreeNode cur, int flag) {
        if (isLeftBoundary(flag) || isRoot(flag)) {
            return 1;
        } else if (isRightBoundary(flag) && cur.right == null) {
            return 2;
        } else {
            return 3;
        }
    }

    public int rightChildFlag(TreeNode cur, int flag) {
        if (isRightBoundary(flag) || isRoot(flag)) {
            return 2;
        } else if (isLeftBoundary(flag) && cur.left == null) {
            return 1;
        } else {
            return 3;
        }
    }

    public void preorder(TreeNode cur, List<Integer> left_boundary,
            List<Integer> right_boundary, List<Integer> leaves, int flag) {
        if (cur == null) {
            return;
        }
        if (isRightBoundary(flag)) {
            right_boundary.add(0, cur.val);
        } else if (isLeftBoundary(flag) || isRoot(flag)) {
            left_boundary.add(cur.val);
        } else if (isLeaf(cur)) {
            leaves.add(cur.val);
        }
        preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
        preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}