//A robot is located at the top-left corner of a m x n grid (marked 'Start' in t
//he diagram below). 
//
// The robot can only move either down or right at any point in time. The robot 
//is trying to reach the bottom-right corner of the grid (marked 'Finish' in the d
//iagram below). 
//
// Now consider if some obstacles are added to the grids. How many unique paths 
//would there be? 
//
// 
//
// An obstacle and empty space is marked as 1 and 0 respectively in the grid. 
//
// Note: m and n will be at most 100. 
//
// Example 1: 
//
// 
//Input:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//Output: 2
//Explanation:
//There is one obstacle in the middle of the 3x3 grid above.
//There are two ways to reach the bottom-right corner:
//1. Right -> Right -> Down -> Down
//2. Down -> Down -> Right -> Right
// 
// Related Topics Array Dynamic Programming 
// 👍 1763 👎 240

package leetcode.editor.en;

// 2020-07-26 13:25:46
// Zeshi Yang
public class Leetcode0063UniquePathsIi{
    // Java: unique-paths-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0063UniquePathsIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: dynamic programming with reduced space complexity
class Solution {
    // Solution 2: dynamic programming with reduced space complexity
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // corner case
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        int width = obstacleGrid[0].length;
        int height = obstacleGrid.length;

        int[] result = new int[width];

        //base case
        for (int i = 0; i < width; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            result[i] = 1;
        }

        //general case
        for(int i = 1; i < height; i++) {
            for(int j = 0; j < width; j++) { // j 从0开始，第0行已经更新完了，但是每一行都得从头开始遍历
                if(obstacleGrid[i][j] == 1) { // part A
                    result[j] = 0;
                } else if (j == 0) { // part B, part A 和 part B 不能对调
                    continue;
                } else {
                    result[j] = result[j - 1] + result[j];
                }
            }
        }
        return result[width - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dynamic programming
class Solution1 {
    // Solution 1: dynamic programming
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // corner case
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        int width = obstacleGrid[0].length;
        int height = obstacleGrid.length;

        int[][] result = new int[height][width];

        //base case
        for (int i = 0; i < width; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            result[0][i] = 1;
        }
        for (int j = 0; j < height; j++) {
            if (obstacleGrid[j][0] == 1) {
                break;
            }
            result[j][0] = 1;
        }

        //general case
        for(int i = 1; i < height; i++) {
            for(int j = 1; j < width; j++) {
                if(obstacleGrid[i][j] == 1) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = result[i][j - 1] + result[i - 1][j];
                }
            }
        }
        return result[height - 1][width - 1];
    }
}

// Solution 2: dynamic programming with reduced space complexity
class Solution2 {
    // Solution 2: dynamic programming with reduced space complexity
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // corner case
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        int width = obstacleGrid[0].length;
        int height = obstacleGrid.length;

        int[] result = new int[width];

        //base case
        for (int i = 0; i < width; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            result[i] = 1;
        }

        //general case
        for(int i = 1; i < height; i++) {
            for(int j = 0; j < width; j++) { // j 从0开始，第0行已经更新完了，但是每一行都得从头开始遍历
                if(obstacleGrid[i][j] == 1) { // part A
                    result[j] = 0;
                } else if (j == 0) { // part B, part A 和 part B 不能对调
                    continue;
                } else {
                    result[j] = result[j - 1] + result[j];
                }
            }
        }
        return result[width - 1];
    }
}
}