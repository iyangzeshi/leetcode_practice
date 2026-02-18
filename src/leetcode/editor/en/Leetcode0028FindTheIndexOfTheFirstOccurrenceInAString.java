/**
Given two strings needle and haystack, return the index of the first occurrence 
of needle in haystack, or -1 if needle is not part of haystack. 

 
 Example 1: 

 
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
 

 Example 2: 

 
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
 

 
 Constraints: 

 
 1 <= haystack.length, needle.length <= 10⁴ 
 haystack and needle consist of only lowercase English characters. 
 

 👍 7488 👎 568

*/
package leetcode.editor.en;

// 2026-02-17 23:15:39
// Jesse Yang
public class Leetcode0028FindTheIndexOfTheFirstOccurrenceInAString{
    // Java: find-the-index-of-the-first-occurrence-in-a-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0028FindTheIndexOfTheFirstOccurrenceInAString().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*sliding window
T(n,m) = O(n * m)
S(n,m) = O(1)
 */
class Solution {
    
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 > len1) {
            return -1;
        }
        // 滑动窗口
        for (int i = 0; i <= len1 - len2; i++) {
            int j = 0;
            // 比较窗口内字符
            while (j < len2 && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == len2) {
                return i;  // 找到完整匹配
            }
        }
        return -1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
