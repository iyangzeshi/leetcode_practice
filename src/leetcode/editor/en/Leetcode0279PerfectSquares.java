//Given a positive integer n, find the least number of perfect square numbers (f
//or example, 1, 4, 9, 16, ...) which sum to n. 
//
// Example 1: 
//
// 
//Input: n = 12
//Output: 3 
//Explanation: 12 = 4 + 4 + 4. 
//
// Example 2: 
//
// 
//Input: n = 13
//Output: 2
//Explanation: 13 = 4 + 9. Related Topics Math Dynamic Programming Breadth-first
// Search 
// 👍 3565 👎 214

package leetcode.editor.en;

import java.util.Arrays;

// 2020-11-07 13:00:02
// Zeshi Yang
public class Leetcode0279PerfectSquares{
    // Java: perfect-squares
    public static void main(String[] args) {
        Solution sol = new Leetcode0279PerfectSquares().new Solution();
        // TO TEST
        int n = 12;
        int res = sol.numSquares(n);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 1D array DP
// Solution 1_3: DFS with 1D array Pruning, T(n) = O(n^1.5), 80 ms
class Solution {
    public int numSquares(int n) {
        int[] squareNum = getSquareNum(n);
        // int len = squareNum.length;
        return dfs(n, squareNum, new Integer[n + 1]);
    }
    
    private int dfs(int num, int[] squareNum, Integer[] memo) {
        // base case
        if (num <= 0) {
            return 0;
        }
        if (memo[num] != null) {
            return memo[num];
        }
        int res = num;
        for (int i = 0; i < squareNum.length && squareNum[i] <= num; i++) {
            res = Math.min(res, 1 + dfs(num - squareNum[i], squareNum, memo));
        }
        memo[num] = res;
        return res;
    }
    
    private int[] getSquareNum(int n) {
        int squareRoot = (int) Math.sqrt(n);
        int[] list = new int[squareRoot];
        for (int i = 1; i <= squareRoot; i++) {
            list[i - 1] = i * i;
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1_1: DFS with 2D array Pruning, T(n) = O(n^2.5), 2549 ms
class Solution1_1 {
    public int numSquares(int n) {
        int[] squareNum = getSquareNum(n);
        int len = squareNum.length;
        return dfs(n, len - 1, squareNum, new Integer[len][n + 1]);
    }
    
    private int dfs(int num, int index, int[] squareNum, Integer[][] memo) {
        // base case
        if (num <= 0) {
            return 0;
        }
        if (index == 0) {
            return num;
        }
        if (memo[index][num] != null) {
            return memo[index][num];
        }
        int curNum = squareNum[index];
        int res = num;
        for (int i = num / curNum; i >= 0; i--) {
            res = Math.min(res, i + dfs(num - i * curNum, index - 1, squareNum, memo));
        }
        memo[index][num] = res;
        return res;
    }
    
    private int[] getSquareNum(int n) {
        int squareRoot = (int) Math.sqrt(n);
        int[] list = new int[squareRoot];
        for (int i = 1; i <= squareRoot; i++) {
            list[i - 1] = i * i;
        }
        return list;
    }
}

// Solution 1_2: backtracking + DFS, 8 ms
class Solution1_2 {
    public int numSquares(int n) {
        int[] squareNum = getSquareNum(n);
        int len = squareNum.length;
        int[] minCount = {n};
        dfs(n, len - 1, 0, minCount, squareNum);
        return minCount[0];
    }
    
    private void dfs(int num, int index, int curCount, int[] minCount, int[] squareNum) {
        // base case
        if (index == 0) {
            minCount[0] = Math.min(minCount[0], curCount + num);
            return;
        }
        int curNum = squareNum[index];
        for (int i = num / curNum; i >= 0; i--) {
            if (curCount + i >= minCount[0]) {
                break;
            }
            dfs(num - i * curNum, index - 1, curCount + i, minCount, squareNum);
        }
    }
    
    
    private int[] getSquareNum(int n) {
        int squareRoot = (int) Math.sqrt(n);
        int[] list = new int[squareRoot];
        for (int i = 1; i <= squareRoot; i++) {
            list[i - 1] = i * i;
        }
        return list;
    }
}

// Solution 1_3: DFS with 1D array Pruning, T(n) = O(n^1.5), 80 ms
class Solution1_3 {
    public int numSquares(int n) {
        int[] squareNum = getSquareNum(n);
        // int len = squareNum.length;
        return dfs(n, squareNum, new Integer[n + 1]);
    }
    
    private int dfs(int num, int[] squareNum, Integer[] memo) {
        // base case
        if (num <= 0) {
            return 0;
        }
        if (memo[num] != null) {
            return memo[num];
        }
        int res = num;
        for (int i = 0; i < squareNum.length && squareNum[i] <= num; i++) {
            res = Math.min(res, 1 + dfs(num - squareNum[i], squareNum, memo));
        }
        memo[num] = res;
        return res;
    }
    
    private int[] getSquareNum(int n) {
        int squareRoot = (int) Math.sqrt(n);
        int[] list = new int[squareRoot];
        for (int i = 1; i <= squareRoot; i++) {
            list[i - 1] = i * i;
        }
        return list;
    }
}

// Solution 2: DP with 1D array, T(n) = O(n^1.5), 32 ms
// 背包问题
class Solution2 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}

// Solution 3: Mathematics Solution, do not need to grasp
class Solution3 {
    
    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }
    
    public int numSquares(int n) {
        // four-square and three-square theorems.
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        
        if (this.isSquare(n))
            return 1;
        // enumeration to check if the number can be decomposed into sum of two squares.
        for (int i = 1; i * i <= n; ++i) {
            if (this.isSquare(n - i * i))
                return 2;
        }
        // bottom case of three-square theorem.
        return 3;
    }
}

}