/**
Given a pattern and a string s, return true if s matches the pattern. 

 A string s matches a pattern if there is some bijective mapping of single 
characters to non-empty strings such that if each character in pattern is replaced 
by the string it maps to, then the resulting string is s. A bijective mapping 
means that no two characters map to the same string, and no character maps to two 
different strings. 

 
 Example 1: 

 
Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue" 

 Example 2: 

 
Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"
 

 Example 3: 

 
Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false
 

 
 Constraints: 

 
 1 <= pattern.length, s.length <= 20 
 pattern and s consist of only lowercase English letters. 
 

 Related Topics Hash Table String Backtracking 👍 912 👎 73

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;

// 2020-10-21 16:55:08
// Jesse Yang
public class Leetcode0291WordPatternIi{
    // Java: word-pattern-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0291WordPatternIi().new Solution();
        // TO TEST
        String pattern = "aabb";
        String s = "xyzabcxzyabc";
        boolean res = sol.wordPatternMatch(pattern, s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*

思路：
使用递归和回溯来尝试每一种可能的匹配方案。如果在某一步匹配成功，则继续下一步，否则回退并尝试另一种匹配方案。
step 1.从当前索引开始遍历字符串，尝试每一种可能的子串。
step 2.检查当前模式字符是否已有匹配的子串：
    如果有，验证当前子串是否与之前匹配的子串一致，不一致则跳过该子串。
        匹配到底则return true
    如果没有，则尝试当前子串：
        将当前模式字符和子串加入哈希表。
        递归检查剩余部分。
        如果递归结果为 false，则从哈希表中移除当前匹配。
匹配关系：
    1. charToString: char to String Map
    2. StringSet: existed matched String in the s word Set
    
    
DFS: T(m, n) = O(m * n^3), S(m,n) = O(m + n)
assuming m is length of p, n is length s
Time complexity analysis:
DFS, isMatched is called for every char in p, in which we do all subString the for loop in String n
there is p * n levels here,
for every level, generating substring takes O(n), it generating at most n times, so here is O(n^2)
Therefore the time complexity is T(m, n) = O(m * n * n^2)= O(m * n^3)

S(m,n) = O(m + n)
There is at most m levels
HashMap and set takes S(m,n) = O(26 + n) = O(n) since there is at most 26 chars inside
So S(m,n) = O(m + n)
 */
class Solution {
    
    public boolean wordPatternMatch(String pattern, String s) {
        // corner case
        if (pattern.length() == 0 && s.length() == 0) {
            return true;
        }
        if (pattern.length() == 0 || s.length() == 0) {
            return false;
        }
        
        // general case
        return isMatched(pattern, s, 0, 0, new HashMap<>(), new HashSet<>());
    }
    
    /**
     * @param pattern: pattern word
     * @param s: s word
     * @param idxP: index of pattern word(inclusive)
     * @param idxS: index of s word(inclusive)
     * @param charToString: char to String Map
     * @param StringSet: existed matched String in the s word Set
     * @return: boolean, whether pattern.sub(idxS) can match s.subString(idxS)
     */
    private boolean isMatched(String pattern, String s, int idxP, int idxS,
            HashMap<Character, String> charToString, HashSet<String> StringSet) {
        // base case - success
        if (idxP == pattern.length() && idxS == s.length()) {
            return true;
        }
        // base case - failure
        if (idxP == pattern.length() || idxS == s.length()) {
            return false;
        }
        
        // general case
        int lenS = s.length();
        int lenP = pattern.length();
        char ch = pattern.charAt(idxP);
        if (charToString.containsKey(ch)) {// match the existed pattern
            String mapString = charToString.get(ch);
            if (s.substring(idxS).startsWith(mapString)) {
                return isMatched(pattern, s, idxP + 1, idxS + mapString.length(), charToString,
                        StringSet);
            }
        } else { // try to match new pattern
        
        }
        for (int i = idxS + 1; i < lenS - lenP + idxP + 2; i++) {
            // for (int i = idxS + 1; i < lenS - (lenP - (idxP + 1)) + 1; i++) {
            String mapString = s.substring(idxS, i);
            if (!StringSet.contains(mapString)) {
                charToString.put(ch, mapString);
                StringSet.add(mapString);
                if (isMatched(pattern, s, idxP + 1, i, charToString, StringSet)) {
                    return true;
                }
                charToString.remove(ch);
                StringSet.remove(mapString);
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
    
// Solution 2: Ryan的code
class Solution2 {
    
    public boolean wordPatternMatch(String pattern, String s) {
        
        // cc
        if (pattern == null || s == null) {
            return false;
        }
        
        HashMap<Character, String> dict = new HashMap<>();
        return dfs(pattern, s, 0, 0, dict);
    }
    
    private boolean dfs(String pattern, String s, int idxP, int idxS, HashMap<Character, String> dict) {
        
        if (idxP == pattern.length()) {
            return idxS == s.length();
        }
        
        int curMaxLen = curMaxLen(pattern, s, idxP, idxS, dict);
        if (curMaxLen < 1) {
            System.out.println();
            return false;
        }
        
        for (int i = idxS; i < s.length(); i++) {
            String word = s.substring(idxS, i + 1);
            char key = pattern.charAt(idxP);
            
            // 有当前 ch --> string
            if (dict.containsKey(key)) {
                if (word.equals(dict.get(key))) { // val 相等
                    if (dfs(pattern, s, idxP + 1, i + 1, dict)) {
                        return true;
                    }
                }
            } else { // new key --> val
                if (dict.containsValue(word)) {
                    continue;
                }
                dict.put(key, word);
                boolean res = dfs(pattern, s, idxP + 1, i + 1, dict);
                dict.remove(key);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int curMaxLen(String pattern, String s, int idxP, int idxS, HashMap<Character, String> dict) {
        
        int leftLen = s.length() - idxS;
        int count = 1;
        char curChar = pattern.charAt(idxP);
        for (int i = idxP + 1; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == curChar) {
                count++;
            } else {
                leftLen -= dict.containsKey(c) ? dict.get(c).length() : 1;
            }
        }
        return leftLen / count;
    }
    
}
}