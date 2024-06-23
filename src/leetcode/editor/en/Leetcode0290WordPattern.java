/**
Given a pattern and a string s, find if s follows the same pattern.

 Here follow means a full match, such that there is a bijection between a
letter in pattern and a non-empty word in s.

 
 Example 1:

 
Input: pattern = "abba", s = "dog cat cat dog"
Output: true
 

 Example 2:

 
Input: pattern = "abba", s = "dog cat cat fish"
Output: false
 

 Example 3:

 
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
 

 
 Constraints:

 
 1 <= pattern.length <= 300
 pattern contains only lower-case English letters.
 1 <= s.length <= 3000
 s contains only lowercase English letters and spaces ' '.
 s does not contain any leading or trailing spaces.
 All the words in s are separated by a single space.
 

 Related Topics Hash Table String 👍 7210 👎 1001

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2024-06-14 21:58:39
// Jesse Yang
public class Leetcode0290WordPattern{
    // Java: word-pattern
    public static void main(String[] args) {
        Solution sol = new Leetcode0290WordPattern().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// split String s and matches 1 by 1
// T(n) = O(n), S(n) = O(n)
class Solution {
    public boolean wordPattern(String pattern, String s) {
        // corner case
        if (pattern.length() == 0 && s.length() == 0) {
            return true;
        }
        if (pattern.length() == 0 || s.length() == 0) {
            return false;
        }
        String[] strArr = s.split("\\s+"); // split by 1 or multiple spaces
        if (pattern.length() != strArr.length) {
            return false;
        }
        
        // general case
        Map<Character, String> charToString = new HashMap<>();
        Set<String> matchedString = new HashSet<>(); // red flag
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String str;
            if (charToString.containsKey(ch)) {
                str = charToString.get(ch);
                if (!str.equals(strArr[i])) {
                    return false;
                }
                matchedString.add(str);
            } else { // !charToString.containsKey(ch)
                if (matchedString.contains(strArr[i])) {
                    return false;
                }
                charToString.put(ch, strArr[i]);
                matchedString.add(strArr[i]);
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}