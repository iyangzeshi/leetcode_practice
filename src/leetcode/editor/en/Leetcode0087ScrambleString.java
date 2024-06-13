//We can scramble a string s to get a string t using the following algorithm: 
//
// 
// If the length of the string is 1, stop. 
// If the length of the string is > 1, do the following:
// 
// Split the string into two non-empty substrings at a random index, i.e., if th
//e string is s, divide it to x and y where s = x + y. 
// Randomly decide to swap the two substrings or to keep them in the same order.
// i.e., after this step, s may become s = x + y or s = y + x. 
// Apply step 1 recursively on each of the two substrings x and y. 
// 
// 
// 
//
// Given two strings s1 and s2 of the same length, return true if s2 is a scramb
//led string of s1, otherwise, return false. 
//
// 
// Example 1: 
//
// 
//Input: s1 = "great", s2 = "rgeat"
//Output: true
//Explanation: One possible scenario applied on s1 is:
//"great" --> "gr/eat" // divide at random index.
//"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and
// keep them in order.
//"gr/eat" --> "graph/r / e/at" // apply the same algorithm recursively on both subs
//trings. divide at ranom index each of them.
//"graph/r / e/at" --> "r/graph / e/at" // random decision was to swap the first substri
//ng and to keep the second substring in the same order.
//"r/graph / e/at" --> "r/graph / e/ a/t" // again apply the algorithm recursively, divi
//de "at" to "a/t".
//"r/graph / e/ a/t" --> "r/graph / e/ a/t" // random decision is to keep both substring
//s in the same order.
//The algorithm stops now and the result string is "rgeat" which is s2.
//As there is one possible scenario that led s1 to be scrambled to s2, we return
// true.
// 
//
// Example 2: 
//
// 
//Input: s1 = "abcde", s2 = "caebd"
//Output: false
// 
//
// Example 3: 
//
// 
//Input: s1 = "a", s2 = "a"
//Output: true
// 
//
// 
// Constraints: 
//
// 
// s1.length == s2.length 
// 1 <= s1.length <= 30 
// s1 and s2 consist of lower-case English letters. 
// 
// Related Topics String Dynamic Programming 
// 👍 671 👎 765

package leetcode.editor.en;

import java.util.Arrays;

// 2020-12-05 16:43:22
// Jesse Yang
public class Leetcode0087ScrambleString{
    // Java: scramble-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0087ScrambleString().new Solution();
        // TO TEST
        String s1 = "great";
        String s2 = "rgeat";
        boolean res = sol.isScramble(s1, s2);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean isScramble(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len = s1.length(); // 题目条件说，s1和s2的长度相等
        if (!isAnagram(chars1, chars2)) {
            return false;
        }
        
        Boolean[][][] dp = new Boolean[len][len][len + 1];
        return isScramble(chars1, 0, chars2, 0, len, dp);
    }
    
    private boolean isScramble(char[] chars1, int start1, char[] chars2, int start2, int len,
            Boolean[][][] dp) {
        if (dp[start1][start2][len] != null) {
            return dp[start1][start2][len];
        }
        // base case
        if (equals(chars1, start1, chars2, start2, len)) {
            dp[start1][start2][len] = true;
            return true;
        }
        int length = chars1.length;
        int[] countChars1Left = new int['z' - 'a' + 1];
        int[] countChars2Left = new int['z' - 'a' + 1];
        int[] countChars2Right = new int['z' - 'a' + 1];
        boolean matched = false;
        for (int i = 1; i < len && start1 + i <= length && start2 + i <= length; i++) {
            char ch1Left = chars1[start1 + i - 1];
            char ch2Left = chars2[start2 + i - 1];
            char ch2Right = chars2[start2 + len - i];
            countChars1Left[ch1Left - 'a']++;
            countChars2Left[ch2Left - 'a']++;
            countChars2Right[ch2Right - 'a']++;
            if (Arrays.equals(countChars1Left, countChars2Left)) {
                if (isScramble(chars1, start1, chars2, start2, i, dp)
                        && isScramble(chars1, start1 + i, chars2, start2 + i, len - i, dp)
                ) {
                    matched = true;
                    break;
                }
            }
            if (Arrays.equals(countChars1Left, countChars2Right)) {
                if (isScramble(chars1, start1, chars2, start2 + len - i, i, dp)
                        && isScramble(chars1, start1 + i, chars2, start2, len - i, dp)
                ) {
                    matched = true;
                    break;
                }
            }
        }
        dp[start1][start2][len] = matched;
        return matched;
    }
    
    private boolean isAnagram(char[] chars1, char[] chars2) {
        int[] countChars1 = count(chars1);
        int[] countChars2 = count(chars2);
        return Arrays.equals(countChars1, countChars2);
    }
    
    private int[] count(char[] chars) {
        int[] countChar = new int['z' - 'a' + 1];
        for (char ch : chars) {
            countChar[ch - 'a']++;
        }
        return countChar;
    }
    
    private boolean equals(char[] chars1, int start1, char[] chars2, int start2, int len) {
        boolean equaled = true;
        for (int i = 0; i < len; i++) {
            if (chars1[start1 + i] != chars2[start2 + i]) {
                equaled = false;
                break;
            }
        }
        return equaled;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS, T(n) = O(n^4), S(n) = O(n^3)
// 4 ms,击败了50.07% 的Java用户, 39.2 MB,击败了53.71% 的Java用户
/**
 * 1. s1与s2长度必定相等，按照拆分，S1 == T1, S2 == T2 / S1 == T2, S2 == T1这两种case。
 * 2. dp[i][j][k]分别代表起点为i的s1，起点为j的s2和长度为k的划分区间，
 *      那另一段区间则是由总区间长度 l - k
 *      其中 l可以取值的范围在2 ~ len之间且left +
 * 3. 初始化三维dp是用单一字符匹配的情况，同时第三维由于表示string区间长度，要多开一格来表示可能为空的情况。
 */
class Solution1 {
    
    public boolean isScramble(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len = s1.length(); // 题目条件说，s1和s2的长度相等
        if (!isAnagram(chars1, chars2)) {
            return false;
        }
        
        Boolean[][][] dp = new Boolean[len][len][len + 1];
        return isScramble(chars1, 0, chars2, 0, len, dp);
    }
    
    private boolean isScramble(char[] chars1, int start1, char[] chars2, int start2, int len,
            Boolean[][][] dp) {
        if (dp[start1][start2][len] != null) {
            return dp[start1][start2][len];
        }
        // base case
        if (equals(chars1, start1, chars2, start2, len)) {
            dp[start1][start2][len] = true;
            return true;
        }
        int length = chars1.length;
        int[] countChars1Left = new int['z' - 'a' + 1];
        int[] countChars2Left = new int['z' - 'a' + 1];
        int[] countChars2Right = new int['z' - 'a' + 1];
        boolean matched = false;
        for (int i = 1; i < len && start1 + i <= length && start2 + i <= length; i++) {
            char ch1Left = chars1[start1 + i - 1];
            char ch2Left = chars2[start2 + i - 1];
            char ch2Right = chars2[start2 + len - i];
            countChars1Left[ch1Left - 'a']++;
            countChars2Left[ch2Left - 'a']++;
            countChars2Right[ch2Right - 'a']++;
            if (Arrays.equals(countChars1Left, countChars2Left)) {
                if (isScramble(chars1, start1, chars2, start2, i, dp)
                        && isScramble(chars1, start1 + i, chars2, start2 + i, len - i, dp)
                ) {
                    matched = true;
                    break;
                }
            }
            if (Arrays.equals(countChars1Left, countChars2Right)) {
                if (isScramble(chars1, start1, chars2, start2 + len - i, i, dp)
                        && isScramble(chars1, start1 + i, chars2, start2, len - i, dp)
                ) {
                    matched = true;
                    break;
                }
            }
        }
        dp[start1][start2][len] = matched;
        return matched;
    }
    
    private boolean isAnagram(char[] chars1, char[] chars2) {
        int[] countChars1 = count(chars1);
        int[] countChars2 = count(chars2);
        return Arrays.equals(countChars1, countChars2);
    }
    
    private int[] count(char[] chars) {
        int[] countChar = new int['z' - 'a' + 1];
        for (char ch : chars) {
            countChar[ch - 'a']++;
        }
        return countChar;
    }
    
    private boolean equals(char[] chars1, int start1, char[] chars2, int start2, int len) {
        boolean equaled = true;
        for (int i = 0; i < len; i++) {
            if (chars1[start1 + i] != chars2[start2 + i]) {
                equaled = false;
                break;
            }
        }
        return equaled;
    }
    
}

// Solution 2: DP, T(n) = O(n^4), S(n) = O(n^3)
// 7 ms,击败了39.45% 的Java用户, 39.1 MB,击败了53.71% 的Java用户
/**
 * 1. s1与s2长度必定相等，按照拆分，S1 == T1, S2 == T2 / S1 == T2, S2 == T1这两种case。
 * 2. dp[i][j][k]分别代表起点为i的s1，起点为j的s2和长度为k的划分区间，
 *      那另一段区间则是由总区间长度 l - k
 *      其中 l可以取值的范围在2 ~ len之间且left +
 * 3. 初始化三维dp是用单一字符匹配的情况，同时第三维由于表示string区间长度，要多开一格来表示可能为空的情况。
 */
class Solution2 {
    
    public boolean isScramble(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len = s1.length(); // 题目条件说，s1和s2的长度相等
        if (!isAnagram(ch1, ch2)) {
            return false;
        }
        
        boolean[][][] dp = new boolean[len][len][len + 1];
        // 初始化单个字符
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][1] = ch1[i] == ch2[j];
            }
        }
        
        // 枚举区间长度 2 ~ len1
        for (int l = 2; l <= len; l++) {
            // 枚举S中的起点位置
            for (int left = 0; left <= len - l; left++) {
                // 枚举T中的起点位置
                for (int right = 0; right <= len - l; right++) {
                    // 枚举划分的长度
                    int longestLen = Math.min(len - Math.max(left, right) - 1, l - 1);
                    for (int k = 1; k <= longestLen; k++) {
                        // case 1: S1 --> T1, S2 --> T2
                        if (dp[left][right][k] && dp[left + k][right + k][l - k]) {
                            dp[left][right][l] = true;
                            break;
                        }
                        // case 2: S1 --> T2, S2 --> T1
                        // S1起点i,T2起点 = j + l - k，S2起点i + k，T1起点 = j
                        if (dp[left][right + l - k][k] && dp[left + k][right][l - k]) {
                            dp[left][right][l] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][len];
    }
    
    private boolean isAnagram(char[] chars1, char[] chars2) {
        int[] countChars1 = count(chars1);
        int[] countChars2 = count(chars2);
        return Arrays.equals(countChars1, countChars2);
    }
    
    private int[] count(char[] chars) {
        int[] countChar = new int['z' - 'a' + 1];
        for (char ch : chars) {
            countChar[ch - 'a']++;
        }
        return countChar;
    }
    
}
}