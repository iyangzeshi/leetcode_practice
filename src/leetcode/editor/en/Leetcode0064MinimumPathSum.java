//Given a m x n grid filled with non-negative numbers, find a path from top left
// to bottom right which minimizes the sum of all numbers along its path. 
//
// Note: You can only move either down or right at any point in time. 
//
// Example: 
//
// 
//Input:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//Output: 7
//Explanation: Because the path 1→3→1→1→1 minimizes the sum.
// 
// Related Topics Array Dynamic Programming 
// 👍 3115 👎 64

package leetcode.editor.en;

// 2020-07-26 13:24:02
// Jesse Yang
public class Leetcode0064MinimumPathSum{
    // Java: minimum-path-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0064MinimumPathSum().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(m, n) = O(m, n), S(m,n) = O(m, n)
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // base case
        for (int i = 1; i < rows; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            grid[0][j] = grid[0][j - 1] + grid[0][j];
        }

        //general case
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[rows - 1][cols - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}