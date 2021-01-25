//Say you have an array for which the j-th element is the price of a given stock
// on day j. 
//
// Design an algorithm to find the maximum profit. You may complete at most k tr
//ansactions. 
//
// Note: 
//You may not engage in multiple transactions at the same time (ie, you must sel
//l the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [2,4,1], k = 2
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 
//4-2 = 2.
// 
//
// Example 2: 
//
// 
//Input: [3,2,6,5,0,3], k = 2
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 
//6-2 = 4.
//             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), prof
//it = 3-0 = 3.
// 
// Related Topics Dynamic Programming 
// 👍 1418 👎 88

package leetcode.editor.en;

// 2020-07-03 18:28:03
public class Leetcode0188BestTimeToBuyAndSellStockIv{
    // Java: best-time-to-buy-and-sell-stock-iv
    public static void main(String[] args) {
        Solution sol = new Leetcode0188BestTimeToBuyAndSellStockIv().new Solution();
        // TO TEST
        int[] prices = {2,4,1};
        int res = sol.maxProfit(2, prices);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int k, int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // edge case
        int len = prices.length;
        if (k >= len / 2) {
            int sum = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        
        int[] sell = new int[k + 1];
        int[] profit = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            profit[i] = -prices[0];
        }
        for (int j = 1; j < len; j++) { // j在 [0, len - 1]之间
            for (int i = 1; i <= k; i++) { // i在[0, k]之间
                sell[i] = Math.max(sell[i], profit[i] + prices[j]);
                profit[i] = Math.max(profit[i], sell[i - 1] - prices[j]);
            }
        }
        return sell[k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: dynamic programming
// T(n) = O(k * n), S(n) = O(k * n)
// 5 ms,击败了64.10% 的Java用户, 38.6 MB,击败了50.86% 的Java用户
class Solution1_1 {

    public int maxProfit(int k, int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // edge case
        int len = prices.length;
        /* start 可有可没有 */
        if (k >= len / 2) {
            int sum = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        /* end 可有可没有 */

        int[][] buy = new int[k + 1][len];
        int[][] profit = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            buy[i][0] = -prices[0];
        }
        for (int i = 1; i <= k; i++) { // i在[0, k]之间
            for (int j = 1; j < len; j++) { // j在 [0, len - 1]之间
                profit[i][j] = Math.max(profit[i][j - 1], buy[i][j - 1] + prices[j]);
                buy[i][j] = Math.max(buy[i][j - 1], profit[i - 1][j - 1] - prices[j]);
            }
        }
        return profit[k][len - 1];
    }
}

// Solution 1_2: dynamic programming
// T(n) = O(k * n), S(n) = O(k)
// 5 ms,击败了64.10% 的Java用户, 36.4 MB,击败了94.42% 的Java用户
class Solution1_2 {
    
    public int maxProfit(int k, int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // edge case
        int len = prices.length;
        if (k >= len / 2) {
            int sum = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        
        int[] profit = new int[k + 1];
        int[] buy = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            buy[i] = -prices[0];
        }
        for (int j = 1; j < len; j++) { // j在 [0, len - 1]之间
            for (int i = 1; i <= k; i++) { // i在[0, k]之间
                profit[i] = Math.max(profit[i], buy[i] + prices[j]);
                buy[i] = Math.max(buy[i], profit[i - 1] - prices[j]);
            }
        }
        return profit[k];
    }
    
}
}