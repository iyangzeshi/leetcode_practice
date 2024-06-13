//You are given coins of different denominations and a total amount of money amo
//unt. Write a function to compute the fewest number of coins that you need to mak
//e up that amount. If that amount of money cannot be made up by any combination o
//f the coins, return -1. 
//
// Example 1: 
//
// 
//Input: coins = [1, 2, 5], amount = 11
//Output: 3 
//Explanation: 11 = 5 + 5 + 1 
//
// Example 2: 
//
// 
//Input: coins = [2], amount = 3
//Output: -1
// 
//
// Note: 
//You may assume that you have an infinite number of each kind of coin. 
// Related Topics Dynamic Programming 
// 👍 4663 👎 145

package leetcode.editor.en;

import java.util.Arrays;
// 2020-08-29 16:51:09
// Jesse Yang
public class Leetcode0322CoinChange{
    // Java: coin-change
    public static void main(String[] args) {
        Solution sol = new Leetcode0322CoinChange().new Solution();
        // TO TEST
        int[] coins = {1,2,5};
        int amount = 11;
        int res =sol.coinChange(coins, amount);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        int[] coinsCopy = Arrays.copyOf(coins, coins.length);
        reverseOrder(coinsCopy);
        int[] ans = {amount + 1};
        dfs(0, 0, ans, amount, coinsCopy, new Integer[len + 1][amount + 1]);
        return ans[0] > amount ? -1 : ans[0];
    }

    private int dfs(int index, int count, int[] ans, int leftAmount, int[] coins,
            Integer[][] memo) {
        int len = coins.length;
        int coin = coins[index];
        int amount = memo[0].length;
        if (memo[index][leftAmount] != null) {
            return memo[index][leftAmount];
        }
        // base case
        if (index == len - 1) {
            if (leftAmount % coin == 0) {
                ans[0] = Math.min(ans[0], count + leftAmount / coin);
                return count + leftAmount / coin;
            } else {
                return amount + 1;
            }
        }
        int res = amount + 1;
        for (int i = leftAmount / coin; i >= 0 && count + i < ans[0]; i--) {
            res = Math.min(res, dfs(index + 1, count + i, ans, leftAmount - i * coin, coins, memo));
        }
        return res;
    }

    private void reverseOrder(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1_1: DFS + Pruning, T(n) = O(n * amount ^ 2), S(n, amount) = O(n * amount)
// 310ms,
class Solution1_1 {
    
    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }
        int len = coins.length;
        int[] coinsCopy = Arrays.copyOf(coins, coins.length);
        reverseOrder(coinsCopy);
        int res = dfs(0, amount, coinsCopy, new Integer[len + 1][amount + 1], amount);
        return res > amount ? -1 : res;
    }
    
    private Integer dfs(int index, int leftSum, int[] coins, Integer[][] memo, int amount) {
        int len = coins.length;
        if (memo[index][leftSum] != null) {
            return memo[index][leftSum];
        }
        // base case
        if (index == len - 1) {
            if (leftSum % coins[index] != 0) {
                return amount + 1;
            } else {
                return leftSum / coins[index];
            }
        }
        int numCoins = amount + 1;
        for (int i = 0; i <= leftSum / coins[index]; i++) {
            int followingNumCoins = dfs(index + 1, leftSum - i * coins[index],
                    coins, memo, amount);
            numCoins = Math.min(numCoins, i + followingNumCoins);
        }
        memo[index][leftSum] = numCoins;
        return numCoins;
    }
    
    private void reverseOrder(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    
}

//Solution 1_2: DFS + Greedy + BackTracking
// 4 ms,击败了99.34% 的Java用户, T(n) = O(n * amount)
class Solution1_2 {

    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        int[] coinsCopy = Arrays.copyOf(coins, coins.length);
        reverseOrder(coinsCopy);
        int[] ans = {amount + 1};
        dfs(0, 0, ans, amount, coinsCopy);
        return ans[0] > amount ? -1 : ans[0];
    }

    private void dfs(int index, int count, int[] ans, int leftAmount, int[] coins) {
        int len = coins.length;
        int coin = coins[index];

        // base case
        if (index == len - 1) {
            if (leftAmount % coin == 0) {
                ans[0] = Math.min(ans[0], count + leftAmount / coin);
            }
            return;
        }

        for (int i = leftAmount / coin; i >= 0 && count + i < ans[0]; i--) {
            dfs(index + 1, count + i, ans, leftAmount - i * coin, coins);
        }
    }

    private void reverseOrder(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

//Solution 1_3: DFS + Greedy + BackTracking + Pruning
// 18 ms,击败了56.18% 的Java用户, T(n) = O(n * amount)
class Solution1_3 {

    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        int[] coinsCopy = Arrays.copyOf(coins, coins.length);
        reverseOrder(coinsCopy);
        int[] ans = {amount + 1};
        dfs(0, 0, ans, amount, coinsCopy, new Integer[len + 1][amount + 1]);
        return ans[0] > amount ? -1 : ans[0];
    }

    private int dfs(int index, int count, int[] ans, int leftAmount, int[] coins,
            Integer[][] memo) {
        int len = coins.length;
        int coin = coins[index];
        int amount = memo[0].length;
        if (memo[index][leftAmount] != null) {
            return memo[index][leftAmount];
        }
        // base case
        if (index == len - 1) {
            if (leftAmount % coin == 0) {
                ans[0] = Math.min(ans[0], count + leftAmount / coin);
                return count + leftAmount / coin;
            } else {
                return amount + 1;
            }
        }
        int res = amount + 1;
        for (int i = leftAmount / coin; i >= 0 && count + i < ans[0]; i--) {
            res = Math.min(res, dfs(index + 1, count + i, ans, leftAmount - i * coin, coins, memo));
        }
        return res;
    }

    private void reverseOrder(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

}

// Solution 2_1: DP
// 852 ms,击败了5.05% 的Java用户 T(n) = O(n * amount ^ 2)
//dp(i, j) = min{dp(i - 1, j - k * coins[i - 1]) + k}, (k > = 0 && j - k * coins[i - 1] >= 0)
class Solution2_1 {
    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        // dp
        int[][] dp = new int[len + 1][amount + 1];
        for (int[] rows: dp) {
            Arrays.fill(rows, amount + 1);
        }
        // initial condition
        for (int i = 0; i <= len ; i++) {
            dp[i][0] = 0;
        }
        // induction function
        // dp(i, j) = min{dp(i - 1, j - k * coins[i + 1)}, (k > = 0 && j - k * coins[i + 1) >= 0)
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                for (int k = 0; k <= j / coins[i - 1]; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + dp[i - 1][j - k * coins[i - 1]]);
                }
            }
        }
        return dp[len][amount] > amount ? -1 : dp[len][amount];
    }
}

// Solution 2_2: DP
// 53 ms,击败了11.93% 的Java用户, T(n) = O(n * amount)
// Solution 2_1到Solution 2_2的讲解: https://www.youtube.com/watch?v=htdBJul3xoc
//optimized induction function: dp(i, j) = min{dp(i - 1, j)，dp(i, j - coin[i] + 1)}
class Solution2_2 {
    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        // dp
        int[][] dp = new int[len + 1][amount + 1];
        for (int[] rows: dp) {
            Arrays.fill(rows, amount + 1);
        }
        // initial condition
        for (int i = 0; i <= len ; i++) {
            dp[i][0] = 0;
        }
        // induction function
        //dp(i, j) = min{dp(i - 1, j)，dp(i, j - coin[i] + 1}
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[len][amount] > amount ? -1 : dp[len][amount];
    }
}

// Solution 2_3: DP
// 11 ms,击败了91.39% 的Java用户, optimized. T(n) = O(n * amount)
// optimized induction function + rolling base:
// dp(i) = min{dp(i - 1, j)，dp(i, j - coin[i] + 1)}
// 也可以直接看成是完全背包问题
class Solution2_3 {
    
    public int coinChange(int[] coins, int amount) {
        // corner case
        if (amount == 0) {
            return 0;
        }
        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
}

}