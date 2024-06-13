//Given a pattern and a string s, return true if s matches the pattern. 
//
// A string s matches a pattern if there is some bijective mapping of single cha
//racters to strings such that if each character in pattern is replaced by the str
//ing it maps to, then the resulting string is s. A bijective mapping means that n
//o two characters map to the same string, and no character maps to two different 
//strings. 
//
// 
// Example 1: 
//
// 
//Input: pattern = "abab", s = "redblueredblue"
//Output: true
//Explanation: One possible mapping is as follows:
//'a' -> "red"
//'b' -> "blue" 
//
// Example 2: 
//
// 
//Input: pattern = "aaaa", s = "asdasdasdasd"
//Output: true
//Explanation: One possible mapping is as follows:
//'a' -> "asd"
// 
//
// Example 3: 
//
// 
//Input: pattern = "abab", s = "asdasdasdasd"
//Output: true
//Explanation: One possible mapping is as follows:
//'a' -> "a"
//'b' -> "sdasd"
//Note that 'a' and 'b' cannot both map to "asd" since the mapping is a bijectio
//n.
// 
//
// Example 4: 
//
// 
//Input: pattern = "aabb", s = "xyzabcxzyabc"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 0 <= pattern.length <= 20 
// 0 <= s.length <= 50 
// pattern and s consist of only lower-case English letters. 
// 
// Related Topics Backtracking 
// 👍 458 👎 29

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
class Solution {

    public boolean wordPatternMatch(String pattern, String s) {
        // corner case
        if (pattern.length() == 0 && s.length() == 0) {
            return true;
        }
        if (pattern.length() == 0 || s.length() == 0) {
            return false;
        }
        return dfs(pattern, s, 0, 0, new HashMap<>(), new HashSet<>());
    }

    /**
     * @param pattern: pattern word
     * @param s: s word
     * @param idxP: index of pattern word
     * @param idxS: index of s word
     * @param map: char to String Map
     * @param set: String in the s word Set
     * @return: boolean, whether pattern.sub(idxS) can match s.subString(idxS)
     */
    private boolean dfs(String pattern, String s, int idxP, int idxS,
            HashMap<Character, String> map, HashSet<String> set) {
        // base case - success
        if (idxP == pattern.length() && idxS == s.length()) {
            return true;
        }
        // base case - failure
        if (idxP == pattern.length() || idxS == s.length()) {
            return false;
        }
        int lenS = s.length();
        int lenP = pattern.length();
        char ch = pattern.charAt(idxP);
        if (map.containsKey(ch)) {
            String mapString = map.get(ch);
            if (s.substring(idxS).startsWith(mapString)) {
                return dfs(pattern, s, idxP + 1, idxS + mapString.length(), map, set);
            }
        } else {
            for (int i = idxS + 1; i < lenS - lenP + idxP + 2; i++) {
            // for (int i = idxS + 1; i < lenS - (lenP - (idxP + 1)) + 1; i++) {
                String mapString = s.substring(idxS, i);
                if (!set.contains(mapString)) {
                    map.put(ch, mapString);
                    set.add(mapString);
                    if (dfs(pattern, s, idxP + 1, i, map, set)) {
                        return true;
                    }
                    map.remove(ch);
                    set.remove(mapString);
                }
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