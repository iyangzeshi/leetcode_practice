//Given a binary search tree (BST), find the lowest common ancestor (LCA) of two
// given nodes in the BST. 
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor 
//is defined between two nodes p and q as the lowest node in T that has both p and
// q as descendants (where we allow a node to be a descendant of itself).” 
//
// Given binary search tree: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// Example 1: 
//
// 
//Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//Output: 6
//Explanation: The LCA of nodes 2 and 8 is 6.
// 
//
// Example 2: 
//
// 
//Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//Output: 2
//Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant o
//f itself according to the LCA definition.
// 
//
// 
// Constraints: 
//
// 
// All of the nodes' values will be unique. 
// p and q are different and both values will exist in the BST. 
// 
// Related Topics Tree 
// 👍 2116 👎 106

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 13:01:21
// Zeshi Yang
public class Leetcode0235LowestCommonAncestorOfABinarySearchTree{
    // Java: lowest-common-ancestor-of-a-binary-search-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0235LowestCommonAncestorOfABinarySearchTree().new Solution();
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
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     假设p < q，找到第一个在[p, q]之间的TreeNode，注意BST的性质
     如果p， q < root的话，往左边走；
     如果p，q > root的话，往右边走
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //corner case
        if (root == null || p == null || q == null) {
            return root;
        }
        if(p == q) {
            return p;
        }
        // make p.val < q.val
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }

        while (root != null && (root.val > q.val || root.val < p.val)) {
            if (root.val > q. val) {
                root = root.left;
            } else if (root.val < p.val) {
                root = root.right;
            }
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: recursion
/**
 假设p < q，找到第一个在[p, q]之间的TreeNode，注意BST的性质
 如果p， q < root的话，往左边call；
 如果p，q > root的话，往右边call
 */
class Solution1 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //corner case
        if (root == null || p == null || q == null) {
            return root;
        }
        if(p == q) {
            return p;
        }
        // make p < q
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }

        // base case
        if (root.val >= p.val && root.val <= q.val) {
            return root;
        }
        if (root.val > q.val) {
            return lowestCommonAncestor(root.left, q, p);
        }
        else { // root.val < p.val
            return lowestCommonAncestor(root.right, q, p);
        }
    }
}

// Solution 2: while loop
/**
 假设p < q，找到第一个在[p, q]之间的TreeNode，注意BST的性质
 如果p， q < root的话，往左边走；
 如果p，q >root的话，往右边走
 */
class Solution2 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //corner case
        if (root == null || p == null || q == null) {
            return root;
        }
        if(p == q) {
            return p;
        }
        // make p < q
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }

        while (root != null &&(root.val > q.val || root.val < p.val)) {
            if (root.val > q. val) {
                root = root.left;
            }
            else if (root.val < p.val) {
                root = root.right;
            }
        }
        return root;
    }
}
}