//Given an array where elements are sorted in ascending order, convert it to a h
//eight balanced BST. 
//
// For this problem, a height-balanced binary tree is defined as a binary tree i
//n which the depth of the two subtrees of every node never differ by more than 1.
// 
//
// Example: 
//
// 
//Given the sorted array: [-10,-3,0,5,9],
//
//One possible answer is: [0,-3,9,-10,null,5], which represents the following he
//ight balanced BST:
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics Tree Depth-first Search 
// 👍 2500 👎 218

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 13:17:33
// Zeshi Yang
public class Leetcode0108ConvertSortedArrayToBinarySearchTree{
    // Java: convert-sorted-array-to-binary-search-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0108ConvertSortedArrayToBinarySearchTree().new Solution();
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
思路：convert的结果，不一定唯一
将sequence转化成Tree：往上走再往上走；往上走再往右下走也可以；但是往下走之后就不能再连续着往上走了。
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return null;
        }

        return constructBST(0, nums.length - 1, nums);
    }

    private TreeNode constructBST(int start, int end, int[] nums) {
        // corner case
        if (start > end) {
            return null;
        }
        //base case, 这个语句也可以不写，但是写了会早点返回
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode cur = new TreeNode(nums[mid]);

        cur.left = constructBST(start, mid - 1, nums);
        cur.right = constructBST(mid + 1, end, nums);

        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}