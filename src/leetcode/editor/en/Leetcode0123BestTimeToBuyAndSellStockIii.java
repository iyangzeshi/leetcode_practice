//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most two 
//transactions. 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you
// must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 
//3-0 = 3.
//             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), prof
//it = 4-1 = 3. 
//
// Example 2: 
//
// 
//Input: [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 
//5-1 = 4.
//             Note that you cannot buy on day 1, buy on day 2 and sell them lat
//er, as you are
//             engaging multiple transactions at the same time. You must sell be
//fore buying again.
// 
//
// Example 3: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0. 
// Related Topics Array Dynamic Programming 
// 👍 2051 👎 73

package leetcode.editor.en;

// 2020-07-03 16:05:45
public class Leetcode0123BestTimeToBuyAndSellStockIii{
    // Java: best-time-to-buy-and-sell-stock-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0123BestTimeToBuyAndSellStockIii().new Solution();
        // TO TEST
        int[] prices = {3,3,5,0,0,3,1,4};
        int res = sol.maxProfit(prices);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 1_2: dynamic programming, rolling(space complexity optimized)
class Solution {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int buy1 = -prices[0]; // 第一次买的最好收益（负值）
        int buy2 = -prices[0]; // 当前第一次卖 + 第二次买 的最好收益
        int profit1 = 0; // 当前第一次卖的最好收益
        int profit2 = 0; // 当前第一次和第二次卖的最好收益
        // 在第二次买之前，必须先完成第一次卖，而且不能是同一天，所以for循环里面，是先执行2的部分，在执行1的部分
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            profit1 = Math.max(profit1, price + buy1);
            
            buy2 = Math.max(buy2, profit1 - price);
            profit2 = Math.max(profit2, price + buy2);
        }
        return profit2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: dynamic programming
// T(n) = O(n), S(n) = O(n)
// 3 ms,击败了99.23% 的Java用户, 55.5 MB,击败了35.28% 的Java用户
class Solution1_1 {
    
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int len = prices.length;
        int[] buy1 = new int[len + 1]; // buy1[i + 1] means from 0 to i, 第一次买的最好收益（负值）
        int[] buy2 = new int[len + 1]; // buy2[i + 1] means from 0 to i, 当前第一次买 + 卖 + 第二次买的最好收益
        buy1[0] = -prices[0];
        buy2[0] = -prices[0];
        int[] profit1 = new int[len + 1]; // 当前第一次买+ 卖完的最好收益
        int[] profit2 = new int[len + 1]; // 当前第一次和第二次卖完的最好收益
        // 在第二次买之前，必须先完成第一次卖，而且不能是同一天，所以for循环里面，是先执行2的部分，在执行1的部分
        for (int i = 0; i < len; i++) {
            buy1[i + 1] = Math.max(buy1[i], -prices[i]);
            profit1[i + 1] = Math.max(profit1[i], prices[i] + buy1[i]);
            
            buy2[i + 1] = Math.max(buy2[i], profit1[i] - prices[i]);
            profit2[i + 1] = Math.max(profit2[i], prices[i] + buy2[i]);
        }
        return profit2[len];
    }
    
}

// Solution 1_2: dynamic programming, rolling(space complexity optimized)
// T(n) = O(n), S(n) = O(1)
// 3 ms,击败了99.23% 的Java用户, 55.3 MB,击败了44.08% 的Java用户
class Solution1_2 {
    
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int buy1 = -prices[0]; // 第一次买的最好收益（负值）
        int buy2 = -prices[0]; // 当前第一次卖 + 第二次买 的最好收益
        int profit1 = 0; // 当前第一次卖的最好收益
        int profit2 = 0; // 当前第一次和第二次卖的最好收益
        // 在第二次买之前，必须先完成第一次卖，而且不能是同一天，所以for循环里面，是先执行2的部分，在执行1的部分
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            profit1 = Math.max(profit1, price + buy1);
            
            buy2 = Math.max(buy2, profit1 - price);
            profit2 = Math.max(profit2, price + buy2);
        }
        return profit2;
    }
    
}
}