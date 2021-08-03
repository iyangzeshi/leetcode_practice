//A robot is located at the top-left corner of a m x n grid (marked 'Start' in t
//he diagram below). 
//
// The robot can only move either down or right at any point in time. The robot 
//is trying to reach the bottom-right corner of the grid (marked 'Finish' in the d
//iagram below). 
//
// How many possible unique paths are there? 
//
// 
//Above is a 7 x 3 grid. How many possible unique paths are there? 
//
// 
// Example 1: 
//
// 
//Input: m = 3, n = 2
//Output: 3
//Explanation:
//From the top-left corner, there are a total of 3 ways to reach the bottom-righ
//t corner:
//1. Right -> Right -> Down
//2. Right -> Down -> Right
//3. Down -> Right -> Right
// 
//
// Example 2: 
//
// 
//Input: m = 7, n = 3
//Output: 28
// 
//
// 
// Constraints: 
//
// 
// 1 <= m, n <= 100 
// It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9. 
// 
// Related Topics Array Dynamic Programming 
// 👍 3382 👎 211

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 13:24:25
// Zeshi Yang
public class Leetcode0062UniquePaths{
    // Java: unique-paths
    public static void main(String[] args) {
        Solution sol = new Leetcode0062UniquePaths().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 3: dynamic programming, reuse the space and reduce space complexity
class Solution {
    // Solution 3: dynamic programming
    public int uniquePaths(int m, int n) {
        int shortLen = Math.min(m, n);
        int longLen = Math.max(m, n);

        int[] result = new int[shortLen];

        // base case
        Arrays.fill(result, 1);

        // general case
        for(int i = 1; i < longLen; i++) {
            for (int j = 1; j < shortLen; j++) {
                result[j] = result[j - 1] + result[j];
            }
        }

        return result[shortLen - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// solution 1: recursion
class Solution1 {
    public int uniquePaths(int m, int n) {
        // base case
        if (m == 1) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        // general case
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);

    }
}

// Solution 2: dynamic programming
// dynamic的思想就是，在recursion中存下要用的结果，避免重复计算
class Solution2 {
    public int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];

        // base case
        for(int i = 0; i < m; i++) {
            result[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            result[0][j] = 1;
        }

        // general case
        for(int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }

        return result[m - 1][n - 1];
    }
}

// Solution 3: dynamic programming, reuse the space and reduce space complexity
class Solution3 {
    // Solution 3: dynamic programming
    public int uniquePaths(int m, int n) {
        int shortLen = Math.min(m, n);
        int longLen = Math.max(m, n);

        int[] result = new int[shortLen];

        // base case
        Arrays.fill(result, 1);

        // general case
        for(int i = 1; i < longLen; i++) {
            for (int j = 1; j < shortLen; j++) {
                result[j] = result[j - 1] + result[j];
            }
        }

        return result[shortLen - 1];
    }
}
}