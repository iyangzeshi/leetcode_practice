//You are given a binary tree in which each node contains an integer value. 
//
// Find the number of paths that sum to a given value. 
//
// The path does not need to start or end at the root or a leaf, but it must go 
//downwards
//(traveling only from parent nodes to child nodes). 
//
// The tree has no more than 1,000 nodes and the values are in the range -1,000,
//000 to 1,000,000.
//
// Example:
// 
//root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//      10
//     /  \
//    5   -3
//   / \    \
//  3   2   11
// / \   \
//3  -2   1
//
//Return 3. The paths that sum to 8 are:
//
//1.  5 -> 3
//2.  5 -> 2 -> 1
//3. -3 -> 11
// 
// Related Topics Tree 
// 👍 3528 👎 302

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-07-26 13:08:30
// Zeshi Yang
public class Leetcode0437PathSumIii{
    // Java: path-sum-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0437PathSumIii().new Solution();
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
    public int pathSum(TreeNode root, int sum) {
        //corner case
        if (root == null) {
            return 0;
        }
        int fromCurrent = pathSumFrom(root, sum);

        int fromLeft = pathSum(root.left, sum);
        int fromRight = pathSum(root.right, sum);

        return fromCurrent + fromLeft + fromRight;
    }
    
    private int pathSumFrom(TreeNode root, int sum) {
        //base case
        if (root == null) {
            return 0;
        }
        //general case
        /*
         if (root.val == sum) {
         return 1 + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root
         .val);
         }
         else {
         return pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
         }
         */
        //上面的可以简写成下面一行
        return (root.val == sum ? 1: 0) + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}