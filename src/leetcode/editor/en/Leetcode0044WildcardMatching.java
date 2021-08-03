//Given an input string (s) and a pattern (p), implement wildcard pattern matchi
//ng with support for '?' and '*'. 
//
// 
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
// 
//
// The matching should cover the entire input string (not partial). 
//
// Note: 
//
// 
// s could be empty and contains only lowercase letters a-z. 
// p could be empty and contains only lowercase letters a-z, and characters like
// ? or *. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "aa"
//p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
// 
//
// Example 2: 
//
// 
//Input:
//s = "aa"
//p = "*"
//Output: true
//Explanation: '*' matches any sequence.
// 
//
// Example 3: 
//
// 
//Input:
//s = "cb"
//p = "?a"
//Output: false
//Explanation: '?' matches 'c', but the second letter is 'a', which does not mat
//ch 'b'.
// 
//
// Example 4: 
//
// 
//Input:
//s = "adceb"
//p = "*a*b"
//Output: true
//Explanation: The first '*' matches the empty sequence, while the second '*' ma
//tches the substring "dce".
// 
//
// Example 5: 
//
// 
//Input:
//s = "acdcb"
//p = "a*c?b"
//Output: false
// 
// Related Topics String Dynamic Programming Backtracking Greedy 
// 👍 1957 👎 108

package leetcode.editor.en;

// 2020-07-08 15:52:35
public class Leetcode0044WildcardMatching{
    // Java: wildcard-matching
    public static void main(String[] args) {
        Solution sol = new Leetcode0044WildcardMatching().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        
        int sLen = s.length();
        int pLen = p.length();
        
        //edge case
        int countNotStar = 0;
        for (int i = 0; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                countNotStar += 1;
            }
        }
        //如果非star数目比s长度还大，不可能匹配
        if (countNotStar > sLen) {
            return false;
        }
        
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;
        //初始化第一列，找出开始的最长的连续 *， 它可以匹配所有字符，对应的dp赋值成true
        int i;
        for (i = 1; i <= pLen; i++) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
        }
        for (int j = 1; j < i; j++) {
            dp[j][0] = true;
        }
        
        for (i = 1; i <= pLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        return dp[pLen][sLen];
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dynamic programming, T(n,m) = O(n * m), S(n, m) = O(n * m)
// 18 ms,击败了69.03% 的Java用户, 39.3 MB,击败了69.00% 的Java用户
/*
                a	    d	    c	    e	    b       s String
        0	    1	    2	    3	    4	    5
    0   TRUE	FALSE	FALSE	FALSE	FALSE	FALSE
 *	1	TRUE	TRUE	TRUE	TRUE	TRUE	TRUE
 a	2	FALSE	TRUE	FALSE	FALSE	FALSE	FALSE
 *	3	FALSE	TRUE	TRUE	TRUE	TRUE	TRUE
 b	4	FALSE	FALSE	FALSE	FALSE	FALSE	TRUE
 
 p String
 
 从左到右，从上往下填表格
 boolean dp[][] = new boolean[pLength][sLength]
 if p[i] == * : dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || d[i][j - 1]
 if p[i] == ? :dp[i][j] = dp[i - 1][j - 1];
 else :dp[i][j] = dp[i - 1][j - 1] && p[i] == s[i]
 */
class Solution1 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        
        int sLen = s.length();
        int pLen = p.length();
        
        //edge case
        int countNotStar = 0;
        for (int i = 0; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                countNotStar += 1;
            }
        }
        //如果非star数目比s长度还大，不可能匹配
        if (countNotStar > sLen) {
            return false;
        }
        
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;
        //初始化第一列，找出开始的最长的连续 *， 它可以匹配所有字符，对应的dp赋值成true
        int i;
        for (i = 1; i <= pLen; i++) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
        }
        for (int j = 1; j < i; j++) {
            dp[j][0] = true;
        }
        
        for (i = 1; i <= pLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        return dp[pLen][sLen];
    }
    
}

// Solution 2: 按照人类思维匹配
// 2 ms,击败了100.00% 的Java用户,38.7 MB,击败了99.22% 的Java用户
/*
 Greedy最原始的做法
 按照s的字符顺序进行while loop
 如果是s, p指针sIndex, pIndex对应的字符一样，或者p中字符出现 ? ，s, p都往后走一个单位;
 如果p中出现*号，把这是s， p指针的位置记录下来sStar， pStar，假设*不匹配字符；然后把p指针往后挪一个位置；
 如果可以直接匹配，一直往后做。
 如果出现不能匹配的现象，
 如果前面有 * : 回溯：把s中sStar的位置往后挪一个位置，表示p中的 * 在s中再多匹配一个字符；sIndex从sStar + 1开始匹配
 如果前面没有 * ，不匹配，返回false
 把s遍历完之后，看p是否到终点，到终点了，就return false；如果没到终点，看p中有没有不能匹配的部分
*/
class Solution2 {
    
    public boolean isMatch(String s, String p) {
        //corner case
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        
        int lengths = s.length();
        int lengthp = p.length();
        
        int sStar = -1; // 用来记录*匹配到的位置
        int pStar = -1; // 用来记录p中*的位置
        int sIndex = 0;
        int pIndex = 0;
        
        while (sIndex < lengths) {
            if (pIndex == lengthp) {//false，回溯
                if (pStar == -1) {
                    return false;
                }
                pIndex = pStar + 1;
                sIndex = sStar++;
            }
            //两个字符串的相应位置字符相同；或者p出现？匹配任意字符
            else if (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex)) {
                pIndex++;
                sIndex++;
                // 如果p出现 * 号
            } else if (p.charAt(pIndex) == '*') {
                pStar = pIndex;
                sStar = sIndex;
                pIndex = pStar + 1;
                //发现有字符不匹配之后，看前面p有没有*号：
                //没有的话return false；有的话，p中的*号在s中往后匹配一个
            } else {
                if (pStar == -1) {
                    return false;
                }
                pIndex = pStar + 1;
                sIndex = sStar++;
            }
        }
        while (pIndex < lengthp) {
            if (p.charAt(pIndex) != '*') {
                break;
            }
            pIndex++;
        }
        return pIndex == lengthp;
    }
    
}

// Solution 3: not figure out yet, Laoliu's solution, I do not figure out yet
// 2 ms,击败了100.00% 的Java用户, 39.9 MB,击败了35.07% 的Java用户
/**
 返回0表示匹配到了s串的末尾，但是未匹配成功；
 返回1表示未匹配到s串的末尾就失败了；
 返回2表示成功匹配。那么只有返回值大于1，才表示成功匹配。
 */
class Solution3 {
    
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        
        return isMatch(s, p, 0, 0) == 2;
    }
    
    private int isMatch(String s, String p, int i, int j) {
        if (i == s.length() && j == p.length()) {
            return 2;
        }
        if (i == s.length() && p.charAt(j) != '*') {
            return 0;
        }
        if (j == p.length()) {
            return 1;
        }
        
        if (p.charAt(j) == '*') {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return isMatch(s, p, i, j + 1);
            }
            for (int k = 0; k <= s.length() - i; k++) {
                int result = isMatch(s, p, i + k, j + 1);
                if (result == 0 || result == 2) {
                    return result;
                }
            }
        }
        
        if (p.charAt(j) == '?' || (p.charAt(j) == s.charAt(i))) {
            return isMatch(s, p, i + 1, j + 1);
        }
        
        return 1;
    }
    
}
}