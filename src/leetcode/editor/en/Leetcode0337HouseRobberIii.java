//The thief has found himself a new place for his thievery again. There is only 
//one entrance to this area, called the "root." Besides the root, each house has o
//ne and only one parent house. After a tour, the smart thief realized that "all h
//ouses in this place forms a binary tree". It will automatically contact the poli
//ce if two directly-linked houses were broken into on the same night. 
//
// Determine the maximum amount of money the thief can rob tonight without alert
//ing the police. 
//
// Example 1: 
//
// 
//Input: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//Output: 7 
//Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7. 
//
// Example 2: 
//
// 
//Input: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//Output: 9
//Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
// Related Topics Dynamic Programming Tree Depth-first Search 
// 👍 3530 👎 63

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-12-14 23:49:55
// Zeshi Yang
public class Leetcode0337HouseRobberIii{
    // Java: house-robber-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0337HouseRobberIii().new Solution();
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
// T(n) = O(n), S(n) = O(n), great
// 0 ms,击败了100.00% 的Java用户, 38.7 MB,击败了55.24% 的Java用户
class Solution {
    
    public int rob(TreeNode root) {
        int[] answer = robFrom(root);
        return Math.max(answer[0], answer[1]);
    }
    
    /**
     *
     * @param node: the start point of the recursion
     * @return : the int array {res1, res2}
     * res1 means the largest rob value from here and rob this node
     * res2 mean the largest rob value from here but not rob this node
     */
    public int[] robFrom(TreeNode node) {
        // return rob this node, and not rob this node
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = robFrom(node.left);
        int[] right = robFrom(node.right);
        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{rob, notRob};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}