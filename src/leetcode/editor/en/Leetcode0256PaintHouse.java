
//There is a row of n houses, where each house can be painted one of three color
//s: red, blue, or green. The cost of painting each house with a certain color is 
//different. You have to paint all the houses such that no two adjacent houses hav
//e the same color. 
//
// The cost of painting each house with a certain color is represented by a n x 
//3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with the
// color red; costs[1][2] is the cost of painting house 1 with color green, and so
// on... Find the minimum cost to paint all houses. 
//
// 
// Example 1: 
//
// 
//Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
//Output: 10
//Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 
//into blue.
//Minimum cost: 2 + 5 + 3 = 10.
// 
//
// Example 2: 
//
// 
//Input: costs = []
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: costs = [[7,6,2]]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// costs.length == n 
// costs[i].length == 3 
// 0 <= n <= 100 
// 1 <= costs[i][j] <= 20 
// 
// Related Topics Dynamic Programming 
// 👍 993 👎 92

package leetcode.editor.en;

// 2020-11-09 13:45:50
// Zeshi Yang
public class Leetcode0256PaintHouse{
    // Java: paint-house
    public static void main(String[] args) {
        Solution sol = new Leetcode0256PaintHouse().new Solution();
        // TO TEST
        int[][] costs = {{20,18,4},{9,9,10}};
        int res = sol.minCost(costs);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DP, with 2D array, T(n) = O(3n) = O(n), S(n) = O(3n) = O(n),
// S(n) can be optimized to O(1)
class Solution {
    public int minCost(int[][] costs) {
        // corner case
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length ==0) {
            return 0;
        }
        int houseNum = costs.length;
        int colorNum = costs[0].length;
        // dp[i][j] means cost to paint from house 0 to house i and house i's color is j
        int[][] dp = new int[houseNum][colorNum];
        for (int i = 0; i < houseNum; i++) {
            for (int j = 0; j < colorNum; j++) {
                if (i == 0) {
                    dp[i][j] = costs[i][j];
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < colorNum; k++) {
                    if (k != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i][j]);
                    }
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int cost: dp[houseNum - 1]) {
            minCost =  Math.min(minCost, cost);
        }
        return minCost;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}