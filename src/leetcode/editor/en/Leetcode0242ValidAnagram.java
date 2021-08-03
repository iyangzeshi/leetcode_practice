//Given two strings s and t , write a function to determine if t is an anagram o
//f s. 
//
// Example 1: 
//
// 
//Input: s = "anagram", t = "nagaram"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s = "rat", t = "car"
//Output: false
// 
//
// Note: 
//You may assume the string contains only lowercase alphabets. 
//
// Follow up: 
//What if the inputs contain unicode characters? How would you adapt your soluti
//on to such case? 
// Related Topics Hash Table Sort 
// 👍 1576 👎 143

package leetcode.editor.en;

// 2020-07-26 12:35:16
// Zeshi Yang
public class Leetcode0242ValidAnagram{
    // Java: valid-anagram
    public static void main(String[] args) {
        Solution sol = new Leetcode0242ValidAnagram().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        //corner case
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a';
            freq[pos] += 1;
        }

        for (int j = 0; j < t.length(); j++) {
            int pos = t.charAt(j) - 'a';
            freq[pos] -= 1;
        }
        for (int i: freq) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}