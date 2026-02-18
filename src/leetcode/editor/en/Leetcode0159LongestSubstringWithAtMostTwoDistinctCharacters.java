//Given a string s , find the length of the longest substring t that contains at
// most 2 distinct characters. 
//
// Example 1: 
//
// 
//Input: "eceba"
//Output: 3
//Explanation: t is "ece" which its length is 3.
// 
//
// Example 2: 
//
// 
//Input: "ccaabbb"
//Output: 5
//Explanation: t is "aabbb" which its length is 5.
// 
// Related Topics Hash Table Two Pointers String Sliding Window 
// 👍 911 👎 16

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2020-07-23 17:02:27
// Jesse Yang
public class Leetcode0159LongestSubstringWithAtMostTwoDistinctCharacters {

	// Java: longest-substring-with-at-most-two-distinct-characters
	public static void main(String[] args) {
		Solution sol = new Leetcode0159LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /*
    用 Hashmap character -> count 去计算 window [start, end)
    map:
    for every char
        如果 map size == 2 并且这个是个 new char(Map里没有）, shrink the start
        else continue
        更新 maxLen
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // corner case
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len <= 2) {
            return len;
        }

        //general case
        int start = 0; // window{start, end)
        Map<Character, Integer> countMap = new HashMap<>();
        int maxLen = 0;
        for (int end = 0; end < len; end++) {
            char ch = s.charAt(end);
            if (countMap.size() == 2 && !countMap.containsKey(ch)) {
                while (countMap.size() == 2) {
                    char startCh = s.charAt(start);
                    countMap.put(startCh, countMap.getOrDefault(startCh, 0) - 1);
                    if (countMap.get(startCh) == 0) {
                        countMap.remove(startCh);
                    }
                    start++;
                }
            }
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution2 {
    
    /*
    12123
    我们用滑动窗口保证窗口内最多只有两种不同字符， ch1, ch2
    同时记录这两种字符在窗口中最后一次出现的位置, idx1, idx2, window:[start, end]
    当出现第三种字符时，就把最后出现位置更靠左的那个字符移出窗口，并更新左边界。
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // corner case
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len <= 2) {
            return len;
        }
        
        //general case
        char ch1 = '\0';
        char ch2 = '\0';
        int idx1 = -1; // window 里 最后一次吹按ch1的地方
        int idx2 = -1;// window 里 最后一次吹按ch2的地方
        int start = 0;
        int maxLen = 0;
        for (int end = 0; end < len; end++) {
            char ch = s.charAt(end);
            if (ch == ch1) {
                idx1 = end;
            } else if (ch == ch2) {
                idx2 = end;
            } else { // 3rd char, different from previous 2 chars
                if (idx1 < idx2) {
                    ch1 = ch;
                    start = idx1 + 1;
                    idx1 = end;
                } else {
                    ch2 = ch;
                    start = idx2 + 1;
                    idx2 = end;
                }
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
}
