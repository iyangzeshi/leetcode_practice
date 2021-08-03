//Given two strings s and t, determine if they are isomorphic. 
//
// Two strings are isomorphic if the characters in s can be replaced to get t. 
//
// All occurrences of a character must be replaced with another character while 
//preserving the order of characters. No two characters may map to the same charac
//ter but a character may map to itself. 
//
// Example 1: 
//
// 
//Input: s = "egg", t = "add"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s = "foo", t = "bar"
//Output: false 
//
// Example 3: 
//
// 
//Input: s = "paper", t = "title"
//Output: true 
//
// Note: 
//You may assume both s and t have the same length. 
// Related Topics Hash Table 
// 👍 1414 👎 360

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:35:55
// Zeshi Yang
public class Leetcode0205IsomorphicStrings{
    // Java: isomorphic-strings
    public static void main(String[] args) {
        Solution sol = new Leetcode0205IsomorphicStrings().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //S2: using array to represent it
    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];

        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i);
            char tt = t.charAt(i);
            if (map1[ss] == 0) {
                map1[ss] = tt;
            } else {
                if (map1[ss] != tt) return false;
            }
            if (map2[tt] == 0) {
                map2[tt] = ss;
            } else {
                if (map2[tt] != ss) return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: check corresponding relation by HashMap
class Solution1 {
    //S1: HashMap
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();
        int len = s.length();
        char c1;
        char c2;
        for (int i = 0; i < len; i++) {
            c1 = s.charAt(i);
            c2 = t.charAt(i);
            if (!mapS.containsKey(c1)) {
                mapS.put(c1,c2);
            }
            else if(mapS.get(c1) != c2) {
                return false;
            }

            if (!mapT.containsKey(c2)) {
                mapT.put(c2,c1);
            }
            else if(mapT.get(c2) != c1) {
                return false;
            }
        }
        return true;
    }
}

// Solution 2: check corresponding relation by Array
class Solution2 {
    //S2: using array to represent it
    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];

        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i);
            char tt = t.charAt(i);
            if (map1[ss] == 0) {
                map1[ss] = tt;
            } else {
                if (map1[ss] != tt) return false;
            }
            if (map2[tt] == 0) {
                map2[tt] = ss;
            } else {
                if (map2[tt] != ss) return false;
            }
        }
        return true;
    }
}
}