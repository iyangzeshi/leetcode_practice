/**
Given a string num which represents an integer, return true if num is a 
strobogrammatic number. 

 A strobogrammatic number is a number that looks the same when rotated 180 
degrees (looked at upside down). 

 
 Example 1: 

 
Input: num = "69"
Output: true
 

 Example 2: 

 
Input: num = "88"
Output: true
 

 Example 3: 

 
Input: num = "962"
Output: false
 

 
 Constraints: 

 
 1 <= num.length <= 50 
 num consists of only digits. 
 num does not contain any leading zeros except for zero itself. 
 

 👍 631 👎 1050

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2026-01-22 14:01:56
// Jesse Yang
public class Leetcode0246StrobogrammaticNumber{
    // Java: strobogrammatic-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0246StrobogrammaticNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isStrobogrammatic(String num) {
        // Initialise a map with the five-digit rotation rules
        Map<Character, Character> map = new HashMap<>(); // rotate map
        map.put('1', '1');
        map.put('0', '0');
        map.put('9', '6');
        map.put('6', '9');
        map.put('8', '8');
        
        int l = 0;
        int r = num.length() - 1;
        while (l <= r) {
            char left = num.charAt(l);
            char right = num.charAt(r);
            
            if (!map.containsKey(left) || map.get(left) != right) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
