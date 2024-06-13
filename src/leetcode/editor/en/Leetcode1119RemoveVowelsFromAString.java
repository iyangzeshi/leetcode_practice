//Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and r
//eturn the new string. 
//
// 
// Example 1: 
//
// 
//Input: s = "leetcodeisacommunityforcoders"
//Output: "ltcdscmmntyfrcdrs"
// 
//
// Example 2: 
//
// 
//Input: s = "aeiou"
//Output: ""
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists of only lowercase English letters. 
// 
// Related Topics String 
// 👍 137 👎 87

package leetcode.editor.en;

import java.util.*;
// 2020-12-16 15:22:27
// Jesse Yang
public class Leetcode1119RemoveVowelsFromAString{
    // Java: remove-vowels-from-a-string
    public static void main(String[] args) {
        Solution sol = new Leetcode1119RemoveVowelsFromAString().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String removeVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        StringBuilder sb = new StringBuilder();
        for(char ch: s.toCharArray()) {
            if (!vowels.contains(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}