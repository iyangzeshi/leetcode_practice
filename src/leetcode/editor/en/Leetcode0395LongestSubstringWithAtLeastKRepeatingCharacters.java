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

import java.util.Arrays;

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
/*
这个题目和
Leetcode0003LongestSubstringWithoutRepeatingCharacters
Leetcode0159LongestSubstringWithAtMostTwoDistinctCharacters
Leetcode0340LongestSubstringWithAtMostKDistinctCharacters
Leetcode0992SubarraysWithKDifferentIntegers
不一样，前面这几个题目用双指针就行了，这个题目，不能用双指针。

最多只有26个字母，String s里面最多有maxUnique种char
把每个sliding window只有1种char的case，统计最长长度
把每个sliding window只有2种char的case，统计最长长度
……
把每个sliding window只有s里面最多有maxUnique种char种char的case，统计最长长度
取上面的长度的max，就是题目要求的答案
 */
public class Solution {
    
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        int maxUnique = getMaxUniqueLetters(s); // how many unique chars in the String s
        
        int result = 0;
        for (int curUnique = 1; curUnique <= maxUnique; curUnique++) {
            // reset countMap
            Arrays.fill(countMap, 0);
            int start = 0; // window start
            int end = 0; // window end
            int idx = 0;
            int unique = 0;
            int countAtLeastK = 0; // count the case of unique chars in the sliding windows
            while (end < str.length) {
                // expand the sliding window
                if (unique <= curUnique) {
                    idx = str[end] - 'a';
                    if (countMap[idx] == 0) {
                        unique++;
                    }
                    countMap[idx]++;
                    if (countMap[idx] == k) {
                        countAtLeastK++;
                    }
                    end++;
                } else {// shrink the sliding window
                    idx = str[start] - 'a';
                    if (countMap[idx] == k) {
                        countAtLeastK--;
                    }
                    countMap[idx]--;
                    if (countMap[idx] == 0) {
                        unique--;
                    }
                    start++;
                }
                if (unique == curUnique && unique == countAtLeastK) {
                    result = Math.max(end - start, result);
                }
            }
        }
        
        return result;
    }
    
    // get the maximum number of unique letters in the string s
    int getMaxUniqueLetters(String s) {
        boolean map[] = new boolean[26];
        int maxUnique = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map[s.charAt(i) - 'a']) {
                maxUnique++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return maxUnique;
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)

}
