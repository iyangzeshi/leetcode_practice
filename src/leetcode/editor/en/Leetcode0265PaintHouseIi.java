//There are a row of n houses, each house can be painted with one of the k color
//s. The cost of painting each house with a certain color is different. You have t
//o paint all the houses such that no two adjacent houses have the same color. 
//
// The cost of painting each house with a certain color is represented by a n x 
//k cost matrix. For example, costs[0][0] is the cost of painting house 0 with col
//or 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Fin
//d the minimum cost to paint all houses. 
//
// Note: 
//All costs are positive integers. 
//
// Example: 
//
// 
//Input: [[1,5,3],[2,9,4]]
//Output: 5
//Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum c
//ost: 1 + 4 = 5; 
//             Or paint house 0 into color 2, paint house 1 into color 0. Minimu
//m cost: 3 + 2 = 5. 
// 
//
// Follow up: 
//Could you solve it in O(nk) runtime? 
// Related Topics Dynamic Programming 
// 👍 560 👎 24

package leetcode.editor.en;

import java.util.Arrays;

// 2020-10-22 22:50:49
// Zeshi Yang
public class Leetcode0265PaintHouseIi{
    // Java: paint-house-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0265PaintHouseIi().new Solution();
        // TO TEST
        int[][] costs = {
                {1, 5, 3},
                {2, 9, 4}
        };
        int res = sol.minCostII(costs);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCostII(int[][] costs) {
        // corner case
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int rows = costs.length;
        int cols = costs[0].length;
        // initialization
        int preMin = Integer.MAX_VALUE;
        int preSecondMin = Integer.MAX_VALUE;
        int preMinIdx = 0;
        int curMinIdx = 0;
        for (int i = 0; i < cols; i++) {
            if (costs[0][i] < preMin) {
                preSecondMin = preMin;
                preMin = costs[0][i];
                preMinIdx = i;
            } else if (costs[0][i] < preSecondMin) {
                preSecondMin = costs[0][i];
            }
        }
        // main dp part
        for (int i = 1; i < rows; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecondMin = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                int cur = costs[i][j] + (j == preMinIdx ? preSecondMin : preMin);
                if (cur < curMin) {
                    curSecondMin = curMin;
                    curMin = cur;
                    curMinIdx = j;
                } else if (cur < curSecondMin) {
                    curSecondMin = cur;
                }
            }
            preMin = curMin;
            preSecondMin = curSecondMin;
            preMinIdx = curMinIdx;
        }
        return preMin;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: DP,对于每个dp[i][j],遍历上一行找到和他不相邻的最小的邻居dp[i - 1][k],
// T(n, k) = O(n * k ^ 2), S(n, k) = O(nk)
class Solution1_1 {
    public int minCostII(int[][] costs) {
        // corner case
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int rows = costs.length;
        int cols = costs[0].length;
        int[][] dp = new int[rows][cols];
        
        // initialization
        dp[0] = Arrays.copyOf(costs[0], cols);
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < cols; k++) {
                    if (k == j) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], costs[i][j] + dp[i - 1][k]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cols; i++) {
            min = Math.min(min, dp[rows - 1][i]);
        }
        return min;
    }
}

// Solution 1_2: DP, T(n, k) = O(nk), S(n, k) = O(nk)
/*
算每个dp[i][j]的时候，其实只要知道cost[i][j]和dp[i]里面最小的两个值就行了，
如果和最小的相邻就用第二小的，否则就用最小的邻居
 */
class Solution1_2 {
    
    public int minCostII(int[][] costs) {
        // corner case
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int rows = costs.length;
        int cols = costs[0].length;
        int[][] dp = new int[rows][cols];
        
        // initialization
        int preMin = Integer.MAX_VALUE;
        int preSecondMin = Integer.MAX_VALUE;
        
        int preMinIdx = 0;
        int curMinIdx = 0;
        int curSecondMinIdx = 0;
        for (int i = 0; i < cols; i++) {
            dp[0][i] = costs[0][i];
            int cur = dp[0][i];
            if (cur < preMin) {
                preSecondMin = preMin;
                preMin = cur;
                preMinIdx = i;
            } else if (cur < preSecondMin) {
                preSecondMin = cur;
            }
        }
        for (int i = 1; i < rows; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecondMin = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                dp[i][j] = costs[i][j] + (j == preMinIdx ? preSecondMin : preMin);
                int cur = dp[i][j];
                if (cur < curMin) {
                    curSecondMin = curMin;
                    curSecondMinIdx = curMinIdx;
                    curMin = cur;
                    curMinIdx = j;
                } else if (cur < curSecondMin) {
                    curSecondMin = cur;
                    curSecondMinIdx = j;
                }
            }
            preMin = curMin;
            preSecondMin = curSecondMin;
            preMinIdx = curMinIdx;
        }
        return preMin;
    }
    
}

//Solution 1_3: DP, T(n, k) = O(nk), S(n, k) = O(1)
/*
优化空间复杂度，实际上不需要dp数组，只需要keep 4个数字和索引就行了
 */
class Solution1_3 {
    
    public int minCostII(int[][] costs) {
        // corner case
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int rows = costs.length;
        int cols = costs[0].length;
        // initialization
        int preMin = Integer.MAX_VALUE;
        int preSecondMin = Integer.MAX_VALUE;
        int preMinIdx = 0;
        int curMinIdx = 0;
        for (int i = 0; i < cols; i++) {
            if (costs[0][i] < preMin) {
                preSecondMin = preMin;
                preMin = costs[0][i];
                preMinIdx = i;
            } else if (costs[0][i] < preSecondMin) {
                preSecondMin = costs[0][i];
            }
        }
        // main dp part
        for (int i = 1; i < rows; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecondMin = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                int cur = costs[i][j] + (j == preMinIdx ? preSecondMin : preMin);
                if (cur < curMin) {
                    curSecondMin = curMin;
                    curMin = cur;
                    curMinIdx = j;
                } else if (cur < curSecondMin) {
                    curSecondMin = cur;
                }
            }
            preMin = curMin;
            preSecondMin = curSecondMin;
            preMinIdx = curMinIdx;
        }
        return preMin;
    }
    
}

}