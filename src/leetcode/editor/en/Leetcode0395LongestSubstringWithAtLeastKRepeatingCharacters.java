//Given a string s and an integer k, return the length of the longest substring 
//of s such that the frequency of each character in this substring is greater than
// or equal to k. 
//
// 
// Example 1: 
//
// 
//Input: s = "aaabb", k = 3
//Output: 3
//Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
// 
//
// Example 2: 
//
// 
//Input: s = "ababbc", k = 2
//Output: 5
//Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 
//'b' is repeated 3 times.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 104 
// s consists of only lowercase English letters. 
// 1 <= k <= 105 
// 
// Related Topics Divide and Conquer Recursion Sliding Window 
// 👍 2219 👎 250

package leetcode.editor.en;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 2021-01-30 17:28:52
// Zeshi Yang
public class Leetcode0395LongestSubstringWithAtLeastKRepeatingCharacters{
    // Java: longest-substring-with-at-least-k-repeating-characters
    public static void main(String[] args) {
        Solution sol = new Leetcode0395LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();
        // TO TEST
        String s = "ababbc";
        int k = 2;
        int res = sol.longestSubstring(s, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Map<Character, Integer> countMap = new HashMap<>();
        // initialization
        int right = 0;
        int minCount = Integer.MAX_VALUE;
        for (; right < len; right++) {
            char ch = s.charAt(right);
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            minCount = Collections.min(countMap.values());
            if (minCount == k) {
                break;
            }
        }
        
        int maxLen = right + 1;
        for (int left = 0; left < len; left++) {
            char ch = s.charAt(left);
            countMap.put(ch, countMap.get(ch) - 1);
            if (countMap.get(ch) == 0) {
                countMap.remove(ch);
            }
            minCount = countMap.get(ch);
            while (minCount < k && right < len - 1) {
                right++;
                ch = s.charAt(right);
                countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
                if (ch == s.charAt(left)) {
                    maxLen = Math.max(maxLen, right - left);
                    break;
                }
            }
            if (right == len - 1) {
                break;
            }
        }
        return maxLen;
    }
}
class Cell implements Comparable<Cell> {
    Character ch;
    int count;
    
    @Override
    public int compareTo(Cell o) {
        if (this.count != o.count) {
            return this.count - o.count;
        } else {
            return Character.compare(this.ch, o.ch);
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
