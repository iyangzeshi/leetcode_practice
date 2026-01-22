/**
Given two strings low and high that represent two integers low and high where 
low <= high, return the number of strobogrammatic numbers in the range [low, high]
. 

 A strobogrammatic number is a number that looks the same when rotated 180 
degrees (looked at upside down). 

 
 Example 1: 
 Input: low = "50", high = "100"
Output: 3
 
 Example 2: 
 Input: low = "0", high = "0"
Output: 1
 
 
 Constraints: 

 
 1 <= low.length, high.length <= 15 
 low and high consist of only digits. 
 low <= high 
 low and high do not contain any leading zeros except for zero itself. 
 

 👍 307 👎 193

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2026-01-22 14:02:03
// Jesse Yang
public class Leetcode0248StrobogrammaticNumberIii{
    // Java: strobogrammatic-number-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0248StrobogrammaticNumberIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(5^(n/2)), S(n) =
class Solution {
    
    private Map<Character, Character> map;
    private int count = 0;
    private String low, high;
    
    public int strobogrammaticInRange(String low, String high) {
        this.low = low;
        this.high = high;
        
        map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        
        for (int len = low.length(); len <= high.length(); len++) {
            dfs(new char[len], 0, len - 1);
        }
        return count;
    }
    
    private void dfs(char[] arr, int left, int right) {
        if (left > right) {
            String s = new String(arr);
            
            // 去掉前导 0
            if (s.length() > 1 && s.charAt(0) == '0') {
                return;
            }
            
            if (
                (s.length() == low.length() && compare(arr, low) < 0)
                || (s.length() == high.length() && compare(arr, high) > 0)
            ) {
                return;
            }
            
            count++;
            return;
        }
        
        for (char ch : map.keySet()) {
            if (arr.length > 1 && left == 0 && ch == '0') {
                continue;
            }
            if (left == right && map.get(ch) != ch) {
                continue;
            }
            
            arr[left] = ch;
            arr[right] = map.get(ch);
            dfs(arr, left + 1, right - 1);
        }
    }
    
    private int compare(char[] arr, String bound) {
        if (arr.length != bound.length()) {
            return Integer.compare(arr.length, bound.length());
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != bound.charAt(i)) {
                return Integer.compare(arr[i] - '0', bound.charAt(i) - '0');
            }
        }
        return 0; // equal
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
