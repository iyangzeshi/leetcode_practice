
//There are N piles of stones arranged in a row. The i-th pile has stones[i] sto
//nes. 
//
// A move consists of merging exactly K consecutive piles into one pile, and the
// cost of this move is equal to the total number of stones in these K piles. 
//
// Find the minimum cost to merge all piles of stones into one pile. If it is im
//possible, return -1. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: stones = [3,2,4,1], K = 2
//Output: 20
//Explanation: 
//We start with [3, 2, 4, 1].
//We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
//We merge [4, 1] for a cost of 5, and we are left with [5, 5].
//We merge [5, 5] for a cost of 10, and we are left with [10].
//The total cost was 20, and this is the minimum possible.
// 
//
// 
// Example 2: 
//
// 
//Input: stones = [3,2,4,1], K = 3
//Output: -1
//Explanation: After any merge operation, there are 2 piles left, and we can't m
//erge anymore.  So the task is impossible.
// 
//
// 
// Example 3: 
//
// 
//Input: stones = [3,5,1,2,6], K = 3
//Output: 25
//Explanation: 
//We start with [3, 5, 1, 2, 6].
//We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
//We merge [3, 8, 6] for a cost of 17, and we are left with [17].
//The total cost was 25, and this is the minimum possible.
// 
//
// 
//
// Note: 
//
// 
// 1 <= stones.length <= 30 
// 2 <= K <= 30 
// 1 <= stones[i] <= 100 
// 
// 
// 
// Related Topics Dynamic Programming 
// 👍 838 👎 55

package leetcode.editor.en;

import java.util.Arrays;

// 2021-02-02 14:36:18
// Zeshi Yang
public class Leetcode1000MinimumCostToMergeStones{
    // Java: minimum-cost-to-merge-stones
    public static void main(String[] args) {
        Solution sol = new Leetcode1000MinimumCostToMergeStones().new Solution();
        // TO TEST
        int []stones = {3,5,1,2,6};
        int K = 3;
        int res = sol.mergeStones(stones, K);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        // corner case
        if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        int[] preSum = new int[len];
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        Integer[][] memo = new Integer[len][len];
        return minCost(0, len - 1, K, memo, preSum);
    }
    
    // min
    private int minCost(int i, int j, int K, Integer[][] memo, int[] preSum) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // base case
        if (i == j) {
            memo[i][j] = 0;
            return 0;
        }
        
        int res = Integer.MAX_VALUE;
        for (int cut = i; cut < j; cut += K - 1) {
            int res1 = minCost(i, cut, K, memo, preSum);
            int res2 = minCost(cut + 1, j, K, memo, preSum);
            res = Math.min(res, res1 + res2);
        }
        if ((j - i) % (K - 1) == 0) {
            res += sum(preSum, i, j);
        }
        memo[i][j] = res;
        return res;
    }
    
    private int sum(int[] preSum, int i, int j) {
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS

// Solution 1_1: T(n, k) = O(n^3  / (k - 1) * k), S(n, k) = O(n^2 * K)
// 2 ms,击败了93.09% 的Java用户, 38.5 MB,击败了24.01% 的Java用户
/*
memo[i][j][k] 表示将 [i, j] 区间的石头缩小成 k 堆的最小花费cost
memo[i][j][1] = memo[i][j][k] + sum(i, j)。不能直接求出合并为1堆的成本，得先合并成k堆。
memo[i][j][1] = memo[i][j][k] + sum(i, j)。不能直接求出合并为1堆的成本，得先合并成k堆。
memo[i][j][m] = min(memo[i][p][1] + memo[p + 1][j][m - 1])，i <= p < j，2 <= m <= k。
这里m为堆数，不能直接用k是因为右部分的存在，要对右部分继续划分求解的话，子问题就必须有合并成m堆的情况。
 */
class Solution1_1 {
    
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if (len == 1) {
            return 0;
        } else if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        
        int[] preSum = new int[len];
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        Integer[][][] memo = new Integer[len][len][K + 1];
        
        int res = memoSearch(0, len - 1, 1, K, memo, preSum);
        return res < Integer.MAX_VALUE ? res : -1;
    }
    
    private int memoSearch(int i, int j, int m, int K, Integer[][][] memo, int[] preSum) {
        // if memo[i][j][m] exists
        if (memo[i][j][m] != null) {
            return memo[i][j][m];
        }
        // base case
        if (j - i + 1 == m) { // 从stones[i ~ j] 一共有m堆，要分成m堆的cost是0（直接不用分）
            memo[i][j][m] = 0;
            return 0;
        }
        
        if (m == 1) {
            memo[i][j][m] = memoSearch(i, j, K, K, memo, preSum) + sum(preSum, i, j);
            return memo[i][j][m];
        }
        int res = Integer.MAX_VALUE;
        for (int cut = i; cut < j; cut += (K - 1)) {
            res = Math.min(res,
                    memoSearch(i, cut, 1, K, memo, preSum)
                            + memoSearch(cut + 1, j, m - 1, K, memo, preSum));
        }
        memo[i][j][m] = res;
        return res;
    }
    
    // sum of stones[i ~ j]
    private int sum(int[] preSum, int i, int j) {
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
}

// Solution 1_2: DFS, T(n, k) = O(n^3  / (k - 1)), S(n, k) = O(n^2)
// 1 ms,击败了97.04% 的Java用户, 38.4 MB,击败了29.28% 的Java用户
/*
合并成一对的条件的是(len - 1) % (K - 1) = 0
memo[i][j] 表示将 [i, j] 区间的石头缩小成尽可能小的堆的cost，可能不是1堆
但合并完成后剩下的堆数一定小于k，更具体地，剩余的堆数一定是(n - 1) % (k - 1) + 1。
计算res的时候
    先合并i到j的时候，先合并i到cut，再合并cut + 1 到j，
    将这两个cost加起来就是将i到j堆石头尽可能合并的结果
    如果i到j的石头堆的个数满足可以合并成一堆
    就res += sum[i to j]
return res; 用memo来pruning
 */

class Solution1_2 {
    
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        // corner case
        if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        int[] preSum = new int[len];
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        Integer[][] memo = new Integer[len][len];
        return minCost(0, len - 1, K, memo, preSum);
    }
    
    // min
    private int minCost(int i, int j, int K, Integer[][] memo, int[] preSum) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // base case
        if (i == j) {
            memo[i][j] = 0;
            return 0;
        }
        
        int res = Integer.MAX_VALUE;
        for (int cut = i; cut < j; cut += K - 1) {
            int res1 = minCost(i, cut, K, memo, preSum);
            int res2 = minCost(cut + 1, j, K, memo, preSum);
            res = Math.min(res, res1 + res2);
        }
        if ((j - i) % (K - 1) == 0) {
            res += sum(preSum, i, j);
        }
        memo[i][j] = res;
        return res;
    }
    
    private int sum(int[] preSum, int i, int j) {
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
    
}


// Solution 2: DP
// 分析过程:
// https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/solution/yi-dong-you-yi-dao-nan-yi-bu-bu-shuo-ming-si-lu-he/

// Solution 2_1: 3 维 DP, T(n, k) = O(n^3  / (k - 1) * k), S(n, k) = O(n^2 * K)
// 9 ms,击败了6.58% 的Java用户, 39.8 MB,击败了6.58% 的Java用户
/**
 * 动态规划
 * 定义状态: dp[i][j][k] 表示将 [i, j] 区间的石头缩小成 k 堆的最小花费cost
 * 合法状态: j-i+1 >= k
 * 最终答案: dp[0][n-1][1]
 * 初始化dp[i][i][1] = 0，答案就是dp[0][n - 1][1]。对于无法实现的情况，定义dp[i][j][k] = max。
 * 状态转移: 想要把 [i, j] 区间的合并成 1 堆, 那么它的上一个状态一定是 K 堆
 *          dp[i][j][1] = dp[i][j][k] + sum(i, j)。不能直接求出合并为1堆的成本，得先合并成k堆。
 *          dp[i][j][m] = min(dp[i][p][1] + dp[p + 1][j][m - 1])，i <= p < j，2 <= m <= k。
 *          这里m为堆数，不能直接用k是因为右部分的存在，要对右部分继续划分求解的话，子问题就必须有合并成m堆的情况。
 */
class Solution2_1 {
    
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if (len == 1) {
            return 0;
        } else if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        int[][][] dp = new int[len][len][K + 1];
        int[] preSum = new int[len + 1]; // preSum[i] —— preSum of stones[0 - i]
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        // initialization
        // 不用Integer.MAX_VALUE,因为Integer.MAX_VALUE + 正数 会溢出变为负数
        int MAX_VALUE = Integer.MAX_VALUE / 2;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                Arrays.fill(dp[i][j], 2, K + 1, MAX_VALUE);
            }
            // dp[i][i][1] = 0; 可以省略，因为默认的int数组申请好空间之后，的默认值就是0
        }
        
        for (int size = 2; size <= len; size++) { // size, 枚举区间长度
            for (int i = 0; i + size - 1 < len; i++) { // i, 枚举区间起点
                int j = i + size - 1;
                for (int m = 2; m <= K; m++) { // m, 枚举堆数
                    for (int cut = i; cut < j; cut += K - 1) {  // cut, 枚举分界点
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][cut][1] + dp[cut + 1][j][m - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + sum(preSum, i, j);
            }
        }
        return dp[0][len - 1][1];
    }
    
    private int sum(int[] preSum, int i, int j) {
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
    
}

// Solution 2_2: 简化成2维DP
//2 ms,击败了93.09% 的Java用户, 36.9 MB,击败了59.54% 的Java用户
/*
定义dp[i][j]为尽可能多的合并区间[i, j] 所需的成本，不一定能合并成一堆，
但合并完成后剩下的堆数一定小于k，更具体地，剩余的堆数一定是(n - 1) % (k - 1) + 1。
j >= i
state transaction function:
dp[i][j] = min(dp[i][p] + dp[p + 1][j]),             where i <= p < j and ((j - i) % (K - 1) != 0)
dp[i][j] = min(dp[i][p] + dp[p + 1][j]) + sum[i, j], where i <= p < j and ((j - i) % (K - 1) == 0)
结果是dp[0][len - 1]
 */
class Solution2_2 {
    
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if (len == 1) {
            return 0;
        } else if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        int[][] dp = new int[len][len];
        int[] preSum = new int[len];
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        for (int size = K; size <= len; size++) { // size, 枚举区间长度
            for (int i = 0; i + size - 1 < len; i++) { // i, 枚举区间起点
                int j = i + size - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int cut = i; cut < j; cut += K - 1) {  // cut, 枚举分界点
                    dp[i][j] = Math.min(dp[i][j], dp[i][cut] + dp[cut + 1][j]);
                }
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += sum(preSum, i, j);
                }
            }
        }
        return dp[0][len - 1];
    }
    
    private int sum(int[] preSum, int i, int j) {
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
    
}
}