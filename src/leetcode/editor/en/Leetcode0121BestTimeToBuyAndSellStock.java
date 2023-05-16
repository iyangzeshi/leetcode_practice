//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// If you were only permitted to complete at most one transaction (i.e., buy one
// and sell one share of the stock), design an algorithm to find the maximum profi
//t. 
//
// Note that you cannot sell a stock before you buy one. 
//
// Example 1: 
//
// 
//Input: [7,1,5,3,6,4]
//Output: 5
//Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 
//6-1 = 5.
//             Not 7-1 = 6, as selling price needs to be larger than buying pric
//e.
// 
//
// Example 2: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0.
// 
// Related Topics Array Dynamic Programming 
// 👍 5072 👎 225

package leetcode.editor.en;

// 2020-07-03 14:41:20
public class Leetcode0121BestTimeToBuyAndSellStock{
    // Java: best-time-to-buy-and-sell-stock
    public static void main(String[] args) {
        Solution sol = new Leetcode0121BestTimeToBuyAndSellStock().new Solution();
        // TO TEST
        int[] prices = {7,1,5,3,6,4};
        int res = sol.maxProfit(prices);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: Greedy
// T(n) = O(n), S(n) = O(1)
class Solution {
    
    // greedy
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1_1:dynamic programming
// T(n) = O(n), S(n) = O(n)
// 3 ms,击败了21.38% 的Java用户, 48.6 MB,击败了20.82% 的Java用户
class Solution1_1 {
    
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int len = prices.length;
        int[] buy = new int[len + 1];// buy[i + 1] means the lowest price from start to i(including)
        int[] profit = new int[len + 1];// profit[i + 1] means max profit from start to i(including)
        
        // initialization
        buy[0] = prices[0];
        for (int i = 0; i < len; i++) {
            profit[i + 1] = Math.max(profit[i], prices[i] - buy[i]);
            buy[i + 1] = Math.min(buy[i], prices[i]);
        }
        return profit[len];
    }
    
}

// Solution 1_2: dynamic programming, rolling(space complexity optimized)
// T(n) = O(n), S(n) = O(1)
// 2 ms,击败了35.48% 的Java用户, 52.5 MB,击败了8.00% 的Java用户
class Solution1_2 {
    
    // dynamic programming, rolling(space complexity optimized)
    // the code is nearly the same as Solution 1, but the logic is different
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int buy = prices[0];// buy means the lowest price from start to i(including)
        int profit = 0;
        for (int price : prices) {
            profit = Math.max(profit, price - buy);
            buy = Math.min(buy, price);
        }
        return profit;
    }
    
}

// Solution 2: Greedy
// T(n) = O(n), S(n) = O(1)
// 2 ms,击败了35.48% 的Java用户, 52.7 MB,击败了7.17% 的Java用户
class Solution2 {
    
    // greedy
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
    
}

}