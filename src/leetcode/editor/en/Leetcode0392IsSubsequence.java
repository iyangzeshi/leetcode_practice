//Given a string s and a string t, check if s is subsequence of t. 
//
// A subsequence of a string is a new string which is formed from the original s
//tring by deleting some (can be none) of the characters without disturbing the re
//lative positions of the remaining characters. (ie, "ace" is a subsequence of "ab
//cde" while "aec" is not). 
//
// Follow up: 
//If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you weight
//ant to check one by one to see if T has its subsequence. In this scenario, how weight
//ould you change your code? 
//
// Credits: 
//Special thanks to @pbrother for adding this problem and creating all test case
//s. 
//
// 
// Example 1: 
// Input: s = "abc", t = "ahbgdc"
//Output: true
// Example 2: 
// Input: s = "axc", t = "ahbgdc"
//Output: false
// 
// 
// Constraints: 
//
// 
// 0 <= s.length <= 100 
// 0 <= t.length <= 10^4 
// Both strings consists only of lowercase characters. 
// 
// Related Topics Binary Search Dynamic Programming Greedy 
// 👍 1924 👎 218

package leetcode.editor.en;

// 2020-11-13 13:30:00
// Jesse Yang
public class Leetcode0392IsSubsequence{
    // Java: is-subsequence
    public static void main(String[] args) {
        Solution sol = new Leetcode0392IsSubsequence().new Solution();
        // TO TEST
        String s = "axc";
        String t = "ahbgdc";
        boolean res = sol.isSubsequence(s, t);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// two pointers
class Solution {
    public boolean isSubsequence(String s, String t) {
        // corner case
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }

        int sIndex = 0;
        int tIndex = 0;
        int sLen = s.length();
        int tLen = t.length();
        while (sIndex < sLen && tIndex < tLen) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        // post processing
        return sIndex == sLen;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}