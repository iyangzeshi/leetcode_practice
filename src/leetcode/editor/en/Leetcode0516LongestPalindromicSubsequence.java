//Given a string s, find the longest palindromic subsequence's length in s. You 
//may assume that the maximum length of s is 1000. 
//
// Example 1: 
//Input: 
//
// 
//"bbbab"
// 
//Output:
//
// 
//4
// 
//One possible longest palindromic subsequence is "bbbb".
//
// 
//
// Example 2: 
//Input: 
//
// 
//"cbbd"
// 
//Output:
//
// 
//2
// 
//One possible longest palindromic subsequence is "bb".
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists only of lowercase English letters. 
// 
// Related Topics Dynamic Programming 
// 👍 2791 👎 212

package leetcode.editor.en;

// 2021-02-14 14:11:25
// Zeshi Yang
public class Leetcode0516LongestPalindromicSubsequence{
    // Java: longest-palindromic-subsequence
    public static void main(String[] args) {
        Solution sol = new Leetcode0516LongestPalindromicSubsequence().new Solution();
        // TO TEST
        String s = "bbbab";
        int res = sol.longestPalindromeSubseq(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DP, T(n) = O(n), S(n) = O(n^2)
// 44 ms,击败了48.64% 的Java用户, 49.1 MB,击败了73.51% 的Java用户
class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int palLen[][] = new int[len][len]; // longest palindromic subsequence’s length in s[i ~ j]
        // initialization
        for (int i = 0; i < len; i++) {
            palLen[i][i] = 1;
        }
        int maxLen = 1;
        for (int size = 2; size <= len; size++) {
            for (int i = 0; i < len; i++) {
                int j = i + size - 1;
                if (j >= len) {
                    break;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    palLen[i][j] = palLen[i + 1][j - 1] + 2;
                    maxLen = Math.max(maxLen, palLen[i][j]);
                } else {
                    palLen[i][j] = Math.max(palLen[i + 1][j], palLen[i][j - 1]);
                }
            }
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}