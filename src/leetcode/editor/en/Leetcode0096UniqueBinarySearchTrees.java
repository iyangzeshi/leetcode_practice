//Given n, how many structurally unique BST's (binary search trees) that store v
//alues 1 ... n? 
//
// Example: 
//
// 
//Input: 3
//Output: 5
//Explanation:
//Given n = 3, there are a total of 5 unique BST's:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 19 
// 
// Related Topics Dynamic Programming Tree 
// 👍 3982 👎 143

package leetcode.editor.en;

// 2020-11-14 14:06:23
// Zeshi Yang
public class Leetcode0096UniqueBinarySearchTrees{
    // Java: unique-binary-search-trees
    public static void main(String[] args) {
        Solution sol = new Leetcode0096UniqueBinarySearchTrees().new Solution();
        // TO TEST
        int n = 4;
        int res = sol.numTrees(n);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DP
class Solution {
    public int numTrees(int n) {
        // corner case
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                temp += dp[j] * dp[i - 1 - j];
            }
            dp[i] = temp;
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS
class Solution1 {
    public int numTrees(int n) {
        // corner case
        Integer[] dp = new Integer[n + 1];
        return dfs(n, dp);
    }
    
    private int dfs(int n, Integer[] dp) {
        if (dp[n] != null) {
            return dp[n];
        }
        // base case
        if (n == 0 || n == 1) {
            dp[n] = 1;
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int left = dfs(i, dp);
            int right = dfs(n - 1 - i, dp);
            res += left * right;
        }
        dp[n] = res;
        return res;
    }
}

// Solution 2: DP
class Solution2 {
    public int numTrees(int n) {
        // corner case
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                temp += dp[j] * dp[i - 1 - j];
            }
            dp[i] = temp;
        }
        return dp[n];
    }
}
}