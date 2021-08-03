//Given a string S and a string T, count the number of distinct subsequences of 
//S which equals T. 
//
// A subsequence of a string is a new string which is formed from the original s
//tring by deleting some (can be none) of the characters without disturbing the re
//lative positions of the remaining characters. (ie, "ACE" is a subsequence of "AB
//CDE" while "AEC" is not). 
//
// It's guaranteed the answer fits on a 32-bit signed integer. 
//
// Example 1: 
//
// 
//Input: S = "rabbbit", T = "rabbit"
//Output: 3
//Explanation:
//As shown below, there are 3 ways you can generate "rabbit" from S.
//(The caret symbol ^ means the chosen letters)
//
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
// 
//
// Example 2: 
//
// 
//Input: S = "babgbag", T = "bag"
//Output: 5
//Explanation:
//As shown below, there are 5 ways you can generate "bag" from S.
//(The caret symbol ^ means the chosen letters)
//
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^
// 
// Related Topics String Dynamic Programming 
// 👍 1210 👎 54

package leetcode.editor.en;

// 2020-07-03 22:27:02
public class Leetcode0115DistinctSubsequences{
    // Java: distinct-subsequences
    public static void main(String[] args) {
        Solution sol = new Leetcode0115DistinctSubsequences().new Solution();
        // TO TEST
        
        System.out.println();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDistinct(String s, String t) {
        // corner case
        if (s== null || t == null) {
            return 0;
        }
        int lenS = s.length();
        int lenT = t.length();
        if (lenS < lenT || (lenS == lenT && !s.equals(t))) {
            return 0;
        }
        
        // general case
        int[][] dp = new int[lenT + 1][lenS + 1]; // means how many matches from t[0, i) to s[0, j)
        // initialization
        dp[0][0] = 1;
        for (int i = 0; i < lenT; i++) {
            dp[i + 1][0] = 0;
        }
        for (int i = 0; i < lenS; i++) {
            dp[0][i + 1] = 1;
        }
        for (int i = 0; i < lenT; i++) {
            for (int j = 0; j < lenS; j++) { // j >= i
                if (t.charAt(i) != s.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j];
                }
            }
        }
        return dp[lenT][lenS];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}