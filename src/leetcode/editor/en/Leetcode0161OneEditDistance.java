//Given two strings s and t, return true if they are both one edit distance apar
//t, otherwise return false. 
//
// A string s is said to be one distance apart from a string t if you can: 
//
// 
// Insert exactly one character into s to get t. 
// Delete exactly one character from s to get t. 
// Replace exactly one character of s with a different character to get t. 
// 
//
// 
// Example 1: 
//
// 
//Input: s = "ab", t = "acb"
//Output: true
//Explanation: We can insert 'c' into s to get t.
// 
//
// Example 2: 
//
// 
//Input: s = "", t = ""
//Output: false
//Explanation: We cannot get t from s by only one step.
// 
//
// Example 3: 
//
// 
//Input: s = "a", t = ""
//Output: true
// 
//
// Example 4: 
//
// 
//Input: s = "", t = "A"
//Output: true
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 104 
// 0 <= t.length <= 104 
// s and t consist of lower-case letters, upper-case letters and/or digits. 
// 
// Related Topics String 
// 👍 653 👎 120

package leetcode.editor.en;

// 2020-09-12 16:42:17
// Zeshi Yang
public class Leetcode0161OneEditDistance{
    // Java: one-edit-distance
    public static void main(String[] args) {
        Solution sol = new Leetcode0161OneEditDistance().new Solution();
        // TO TEST
        String s = "a";
        String t = "A";
        boolean res = sol.isOneEditDistance(s, t);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenS > lenT) {
            return isOneEditDistance(t, s);
        }
        if (lenT - lenS > 1) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (int i = 0; i < lenS; i++) {
            if (sArray[i] != tArray[i]) {
                if (lenS == lenT) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return lenS + 1 == lenT;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DP
class Solution1 {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 > len2) {
            return isOneEditDistance(t, s);
        }
        if (len2 - len1 > 1) {
            return false;
        }
        int[][] dist = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dist[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dist[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dist[i][j] = dist[i - 1][j - 1];
                } else {
                    dist[i][j] = Math.min(1 + Math.min(dist[i - 1][j], dist[i][j - 1]),
                            1 + dist[i - 1][j - 1]);
                }
            }
        }
        return dist[len1][len2] == 1;
    }
}

// Solution 2: two pointers
class Solution2 {
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenS > lenT) {
            return isOneEditDistance(t, s);
        }
        if (lenT - lenS > 1) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (int i = 0; i < lenS; i++) {
            if (sArray[i] != tArray[i]) {
                if (lenS == lenT) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return lenS + 1 == lenT;
    }
}

}