//Given a string s, find the length of the longest substring without repeating c
//haracters. 
//
// 
// Example 1: 
//
// 
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
// 
//
// Example 2: 
//
// 
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// Example 3: 
//
// 
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a 
//substring.
// 
//
// Example 4: 
//
// 
//Input: s = ""
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 5 * 104 
// s consists of English letters, digits, symbols and spaces. 
// 
// Related Topics Hash Table Two Pointers String Sliding Window 
// 👍 11791 👎 637

package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2020-11-28 15:39:55
// Zeshi Yang
public class Leetcode0003LongestSubstringWithoutRepeatingCharacters{
    // Java: longest-substring-without-repeating-characters
    public static void main(String[] args) {
        Solution sol = new Leetcode0003LongestSubstringWithoutRepeatingCharacters().new Solution();
        // TO TEST
        String s = "aabc";
        int res = sol.lengthOfLongestSubstring(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        // use moving window[left, right], the char inside has not duplicates, and traverse the s
        int left = 0;
        int right;
        int maxLen = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (right = 0; right < len; right++) {
            char ch = s.charAt(right);
            if (charToIndex.containsKey(ch)) {
                left = Math.max(left, charToIndex.get(ch) + 1);// 注意：start可能在上次出现位置之后！
            }
            maxLen = Math.max(maxLen, right - left + 1);
            charToIndex.put(ch, right);
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1:time = O(n), space = O(k)
/**
 * 本题思路：移动window做法，[left, right)，窗口内不能有重复数字
 * 检查重复用HashSet
 * right一直往右走，走到让windows出现重复数字的时候，就让left往右走一个数字，直到windows不重复为止
 */
class Solution1_1 {
    public int lengthOfLongestSubstring(String s) {
        // corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        // use moving window[left, right), the char inside has not duplicates, and traverse the s
        int left = 0;
        int right = 1;
        int maxLen = 1;
        Set<Character> visited = new HashSet<>(); // to store the char in the window
        visited.add(s.charAt(left));
        while (right < len) {
            if (!visited.contains(s.charAt(right))) {
                visited.add(s.charAt(right));
                right++;
            } else {
                visited.remove(s.charAt(left));
                left++;
            }
            maxLen = Math.max(maxLen, visited.size());
        }
        return maxLen;
    }
}

// Solution 2:
/**
 * 本题思路：移动window做法，[left, right]，窗口内不能有重复数字
 * right一直往右走，走到让windows出现重复数字的时候，就让left跳到让windows不重复的地方,
 * 走到和right的char重复为止
 */

class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        // corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        // use moving window[left, right],
        // the char inside does not have duplicates, and traverse the String s
        int left = 0;
        int right;
        int maxLen = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (right = 0; right < len; right++) {
            char ch = s.charAt(right);
            if (charToIndex.containsKey(ch)) {
                left = Math.max(left, charToIndex.get(ch) + 1);// 注意：start可能在上次出现位置之后！
            }
            maxLen = Math.max(maxLen, right - left + 1);
            charToIndex.put(ch, right);
        }
        return maxLen;
    }
}
}