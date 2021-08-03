//Your are given an array of integers prices, for which the i-th element is the 
//price of a given stock on day i; and a non-negative integer fee representing a t
//ransaction fee. 
// You may complete as many transactions as you like, but you need to pay the tr
//ansaction fee for each transaction. You may not buy more than 1 share of a stock
// at a time (ie. you must sell the stock share before you buy again.) 
// Return the maximum profit you can make. 
//
// Example 1: 
// 
//Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
//Output: 8
//Explanation: The maximum profit can be achieved by:
// Buying at prices[0] = 1 Selling at prices[3] = 8 Buying at prices[4] = 4 Sell
//ing at prices[5] = 9 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
// 
// 
//
// Note:
// 0 < prices.length <= 50000. 
// 0 < prices[i] < 50000. 
// 0 <= fee < 50000. 
// Related Topics Array Dynamic Programming Greedy 
// 👍 1500 👎 43

package leetcode.editor.en;

// 2020-07-03 19:39:59
public class Leetcode0714BestTimeToBuyAndSellStockWithTransactionFee{
    // Java: best-time-to-buy-and-sell-stock-with-transaction-fee
    public static void main(String[] args) {
        Solution sol = new Leetcode0714BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // dynamic programming
        public int maxProfit(int[] prices, int fee) {
            // corner case
            if (prices == null || prices.length == 0) {
                return 0;
            }
        
            // general case
            int buy = - prices[0];
            int sell = 0;
            int maxprofit = 0;
            for (int price: prices) {
                int nextSell = Math.max(sell, buy + price - fee);
                int nextBuy = Math.max(buy, sell - price);
                sell = nextSell;
                buy = nextBuy;
            }
            return sell;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}