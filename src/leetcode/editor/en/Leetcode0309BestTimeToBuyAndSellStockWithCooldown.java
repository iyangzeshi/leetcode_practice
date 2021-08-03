//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete as many tran
//sactions as you like (ie, buy one and sell one share of the stock multiple times
//) with the following restrictions: 
//
// 
// You may not engage in multiple transactions at the same time (ie, you must se
//ll the stock before you buy again). 
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 
//day) 
// 
//
// Example: 
//
// 
//Input: [1,2,3,0,2]
//Output: 3 
//Explanation: transactions = [buy, sell, cooldown, buy, sell]
// Related Topics Dynamic Programming 
// 👍 2290 👎 81

package leetcode.editor.en;

// 2020-07-03 19:59:16
public class Leetcode0309BestTimeToBuyAndSellStockWithCooldown {
	
	// Java: best-time-to-buy-and-sell-stock-with-cooldown
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0309BestTimeToBuyAndSellStockWithCooldown().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    // dynamic programming, roll over(space complexity optimized)
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        // sell1 | sell2 | cSell2
        // buy1  | buy2  | cBuy2
    
        for (int i = 1; i < len; i++) {
            // 买/卖
            int cBuy2 = sell1 - prices[i];
            int cSell2 = Math.max(buy1, buy2) + prices[i];
        
            // 不买/不卖
            int cBuy1 = Math.max(buy1, buy2);
            int cSell1 = Math.max(sell1, sell2);
        
            buy2 = cBuy2;
            sell2 = cSell2;
            buy1 = cBuy1;
            sell1 = cSell1;
        }
        return Math.max(sell1, sell2);
    
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1_1 {
    
    // dynamic programming
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        sell[0] = 0;
        
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], (i - 2 < 0 ? 0 : sell[i - 2]) - prices[i]); // 不买/买
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); // 不卖/卖
        }
        return sell[len - 1];
        
    }
}

}