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
class Solution {
    
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
    
        for (int row = len1 - 1; row >= 0; row--) {
            for (int col = len2 - 1; col >= 0; col--){
                if (text1.charAt(row) == text2.charAt(col)) {
                    dp[row][col] = dp[row + 1][col + 1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row + 1][col], dp[row][col + 1]);
                }
            }
        }
        return dp[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}