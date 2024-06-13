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
        int idx1 = -1;
        int idx2 = -1;
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
//leetcode submit region end(Prohibit modification and deletion)

}