//A message containing letters from A-Z is being encoded to numbers using the fo
//llowing mapping: 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// Given a non-empty string containing only digits, determine the total number o
//f ways to decode it. 
//
// The answer is guaranteed to fit in a 32-bit integer. 
//
// 
// Example 1: 
//
// 
//Input: s = "12"
//Output: 2
//Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// 
//
// Example 2: 
//
// 
//Input: s = "226"
//Output: 3
//Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
//.
// 
//
// Example 3: 
//
// 
//Input: s = "0"
//Output: 0
//Explanation: There is no character that is mapped to a number starting with '0
//'. We cannot ignore a zero when we face it while decoding. So, each '0' should b
//e part of "10" --> 'J' or "20" --> 'T'.
// 
//
// Example 4: 
//
// 
//Input: s = "1"
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 100 
// s contains only digits and may contain leading zero(s). 
// 
// Related Topics String Dynamic Programming 
// 👍 3274 👎 3179

package leetcode.editor.en;

// 2020-10-21 17:41:23
// Jesse Yang
public class Leetcode0091DecodeWays{
    // Java: decode-ways
    public static void main(String[] args) {
        Solution sol = new Leetcode0091DecodeWays().new Solution();
        // TO TEST
        String s = "0";
        int res = sol.numDecodings(s);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        return dfs(0, new Integer[len], s);
    }
    
    private int dfs(int index, Integer[] memo, String s) {
        // base case
        if (index == s.length()) {
            return 1;
        }
        if (memo[index] != null) {
            return memo[index];
        }
        int res = 0;
        for (int i = index + 1; i <= Math.min(index + 2, s.length()); i++) {
            int num = Integer.parseInt(s.substring(index, i));
            if (0 < num && num <= 26) {
                res += dfs(i, memo, s);
            } else {
                break;
            }
            
        }
        memo[index] = res;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}