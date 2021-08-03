
//Given a string s. In one step you can insert any character at any index of the
// string. 
//
// Return the minimum number of steps to make s palindrome. 
//
// A Palindrome String is one that reads the same backward as well as forward. 
//
// 
// Example 1: 
//
// 
//Input: s = "zzazz"
//Output: 0
//Explanation: The string "zzazz" is already palindrome we don't need any insert
//ions.
// 
//
// Example 2: 
//
// 
//Input: s = "mbadm"
//Output: 2
//Explanation: String can be "mbdadbm" or "mdbabdm".
// 
//
// Example 3: 
//
// 
//Input: s = "leetcode"
//Output: 5
//Explanation: Inserting 5 characters the string becomes "leetcodocteel".
// 
//
// Example 4: 
//
// 
//Input: s = "g"
//Output: 0
// 
//
// Example 5: 
//
// 
//Input: s = "no"
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 500 
// All characters of s are lower case English letters. 
// 
// Related Topics Dynamic Programming 
// 👍 632 👎 9

package leetcode.editor.en;

// 2021-02-02 12:35:34
// Zeshi Yang
public class Leetcode1312MinimumInsertionStepsToMakeAStringPalindrome{
    // Java: minimum-insertion-steps-to-make-a-string-palindrome
    public static void main(String[] args) {
        Solution sol = new Leetcode1312MinimumInsertionStepsToMakeAStringPalindrome().new Solution();
        // TO TEST
        String s = "mbadm";
        int res = sol.minInsertions(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DFS, T(n) = O(n^2), S(n) = O(n^2);
/*
dp[i][j] := min chars to insert
dp[i][j] = dp[i-1][j+1] if s[i] == s[j] else min(dp[i+1][j] , dp[i][j-1]) + 1
base case: dp[i][i] = 0
res: dp[0][n-1]
 */
class Solution {
    
    public int minInsertions(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Integer[][] memo = new Integer[len][len];
        
        return dfs(s.toCharArray(), 0, len - 1, memo);
    }
    
    private int dfs(char[] chars, int i, int j, Integer[][] memo) {
        // base case
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        
        int res;
        if (chars[i] == chars[j]) {
            res = dfs(chars, i + 1, j - 1, memo);
        } else {
            res = Math.min(dfs(chars, i + 1, j, memo), dfs(chars, i, j - 1, memo)) + 1;
        }
        memo[i][j] = res;
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS, T(n) = O(n^2), S(n) = O(n^2);
// 35 ms,击败了19.46% 的Java用户, 52.6 MB,击败了5.98% 的Java用户
/*
dp[i][j] := min chars to insert
dp[i][j] = dp[i-1][j+1] if s[i] == s[j] else min(dp[i+1][j] , dp[i][j-1]) + 1
base case: dp[i][i] = 0
res: dp[0][n-1]
 */
class Solution1 {
    
    public int minInsertions(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Integer[][] memo = new Integer[len][len];
        
        return dfs(s.toCharArray(), 0, len - 1, memo);
    }
    
    private int dfs(char[] chars, int i, int j, Integer[][] memo) {
        // base case
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        
        int res;
        if (chars[i] == chars[j]) {
            res = dfs(chars, i + 1, j - 1, memo);
        } else {
            res = Math.min(dfs(chars, i + 1, j, memo), dfs(chars, i, j - 1, memo)) + 1;
        }
        memo[i][j] = res;
        return res;
    }
    
}


// Solution 2: DP, T(n) = O(n^2), S(n) = O(n^2);
// 14 ms,击败了93.74% 的Java用户, 40.2 MB,击败了77.14% 的Java用户
/*
dp[i][j] := min chars to insert
dp[i][j] = dp[i-1][j+1] if s[i] == s[j] else min(dp[i+1][j] , dp[i][j-1]) + 1
base case: dp[i][i] = 0
res: dp[0][n-1]
 */
class Solution2 {
    
    public int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][len - 1];
    }
    
}
}