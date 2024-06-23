//Given two strings text1 and text2, return the length of their longest common s
//ubsequence. 
//
// A subsequence of a string is a new string generated from the original string 
//with some characters(can be none) deleted without changing the relative order of
// the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is
// not). A common subsequence of two strings is a subsequence that is common to bo
//th strings. 
//
// 
//
// If there is no common subsequence, return 0. 
//
// 
// Example 1: 
//
// 
//Input: text1 = "abcde", text2 = "ace" 
//Output: 3  
//Explanation: The longest common subsequence is "ace" and its length is 3.
// 
//
// Example 2: 
//
// 
//Input: text1 = "abc", text2 = "abc"
//Output: 3
//Explanation: The longest common subsequence is "abc" and its length is 3.
// 
//
// Example 3: 
//
// 
//Input: text1 = "abc", text2 = "def"
//Output: 0
//Explanation: There is no such common subsequence, so the result is 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= text1.length <= 1000 
// 1 <= text2.length <= 1000 
// The input strings consist of lowercase English characters only. 
// 
// Related Topics Dynamic Programming 
// 👍 2551 👎 30

package leetcode.editor.en;

// 2021-02-14 14:47:41
// Jesse Yang
public class Leetcode1143LongestCommonSubsequence{
    // Java: longest-common-subsequence
    public static void main(String[] args) {
        Solution sol = new Leetcode1143LongestCommonSubsequence().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(m * n), S(m, n) = O(m * n)
// 8 ms,击败了93.62% 的Java用户, 43.1 MB,击败了26.49% 的Java用户
/*
DFS
创建一个二维的DP数组，dp[i][j]表示字符串s1的前i个字符和字符串s2的前j个字符的最长公共子串的长度。
如果s1[i-1]等于s2[j-1]，那么dp[i][j] = dp[i-1][j-1] + 1。
如果s1[i-1]不等于s2[j-1]，那么dp[i][j] = 0。
最终答案是dp数组中的最大值。
 */
class Solution {
    
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        Integer[][] dp = new Integer[len1][len2];
        
        dfs(text1, text2, len1 - 1, len2 - 1, dp);
        return dp[len1 - 1][len2 - 1];
    }
    
    private int dfs(String text1, String text2, int index1, int index2, Integer[][] dp) {
        // base case
        if (index1 == -1 || index2 == -1) {
            return 0;
        }
        if (dp[index1][index2] != null) {
            return dp[index1][index2];
        }
        
        // general case
        if (text1.charAt(index1) == text2.charAt(index2)) {
            dp[index1][index2] = dfs(text1, text2, index1 - 1, index2 - 1, dp) + 1;
        } else {
            dp[index1][index2] = Math.max(
                    dfs(text1, text2, index1 - 1, index2, dp),
                    dfs(text1, text2, index1, index2 - 1, dp)
            );
        }
        return dp[index1][index2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// DFS T(n) = O(m * n), S(m, n) = O(m * n)
/*
DFS
创建一个二维的DP数组，dp[i][j](including)表示字符串s1的前i-1个字符和字符串s2的前j-2个字符的最长公共子串的长度。
如果s1[i-1]等于s2[j-1]，那么dp[i][j] = dp[i-1][j-1] + 1。
如果s1[i-1]不等于s2[j-1]，那么dp[i][j] = Max{dp[i-1][j]， dp[i][j-1]}
最终答案是dp数组中的最大值。
 */
class Solution1 {
    
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        Integer[][] dp = new Integer[len1][len2];
        
        dfs(text1, text2, len1 - 1, len2 - 1, dp);
        return dp[len1 - 1][len2 - 1];
    }
    
    private int dfs(String text1, String text2, int i, int j, Integer[][] dp) {
        // base case
        if (i == -1 || j == -1) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        
        // general case
        if (text1.charAt(i) == text2.charAt(j)) {
            dp[i][j] = dfs(text1, text2, i - 1, j - 1, dp) + 1;
        } else {
            dp[i][j] = Math.max(
                    dfs(text1, text2, i - 1, j, dp),
                    dfs(text1, text2, i, j - 1, dp)
            );
        }
        return dp[i][j];
    }
}

// T(n) = O(m * n), S(m, n) = O(m * n)
/*
DP
创建一个二维的DP数组，dp[i][j](exclude i and j)表示字符串s1的前i-1个字符和字符串s2的前j-2个字符的最长公共子串的长度。
如果s1[i-1]等于s2[j-1]，那么dp[i][j] = dp[i-1][j-1] + 1。
如果s1[i-1]不等于s2[j-1]，那么dp[i][j] = Max{dp[i-1][j]， dp[i][j-1]}
最终答案是dp数组中的最大值。
 */
class Solution2 {
    
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++){
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
}