//Given a string s that consists of only uppercase English letters, you can perf
//orm at most k operations on that string. 
//
// In one operation, you can choose any character of the string and change it to
// any other uppercase English character. 
//
// Find the length of the longest sub-string containing all repeating letters yo
//u can get after performing the above operations. 
//
// Note: 
//Both the string's length and k will not exceed 104. 
//
// Example 1: 
//
// 
//Input:
//s = "ABAB", k = 2
//
//Output:
//4
//
//Explanation:
//Replace the two 'A's with two 'B's or vice versa.
// 
//
// 
//
// Example 2: 
//
// 
//Input:
//s = "AABABBA", k = 1
//
//Output:
//4
//
//Explanation:
//Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//The substring "BBBB" has the longest repeating letters, which is 4.
// 
//
// 
// Related Topics Two Pointers Sliding Window 
// 👍 1422 👎 84

package leetcode.editor.en;

// 2020-07-24 01:05:21
// Jesse Yang
public class Leetcode0424LongestRepeatingCharacterReplacement {

	// Java: longest-repeating-character-replacement
	public static void main(String[] args) {
		Solution sol = new Leetcode0424LongestRepeatingCharacterReplacement().new Solution();
		// TO TEST
        String s = "AABABBA";
        int k = 1;
        int res = sol.characterReplacement(s, k);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int characterReplacement(String s, int k) {
        // corner case
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }

        // general case
        int len = s.length();
        int[] count = new int['Z' - 'A' + 1];
        int left = 0;
        int maxLen = 0;
        char freChar = '\0'; // most frequent char
        for (int right = 0; right < len; right++) {
            char ch = s.charAt(right);
            count[ch - 'A']++;
            // update the most frequent char
            if (freChar == '\0' || count[ch - 'A'] > count[freChar - 'A']) {
                freChar = ch;
            }

            while (right - left + 1 - count[freChar - 'A'] > k) {
                char leftChar = s.charAt(left++);
                count[leftChar - 'A']--;

                if (leftChar == freChar) {
                    for (char c = 'A'; c <= 'Z'; c++) {
                        if (count[c - 'A'] > count[freChar - 'A']) {
                            freChar = c;
                        }
                    }
                }
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}