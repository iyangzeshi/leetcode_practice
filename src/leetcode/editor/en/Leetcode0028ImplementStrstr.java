//Implement strStr(). 
//
// Return the index of the first occurrence of needle in haystack, or -1 if need
//le is not part of haystack. 
//
// Example 1: 
//
// 
//Input: haystack = "hello", needle = "ll"
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: haystack = "aaaaa", needle = "bba"
//Output: -1
// 
//
// Clarification: 
//
// What should we return when needle is an empty string? This is a great questio
//n to ask during an interview. 
//
// For the purpose of this problem, we will return 0 when needle is an empty str
//ing. This is consistent to C's strstr() and Java's indexOf(). 
//
// 
// Constraints: 
//
// 
// haystack and needle consist only of lowercase English characters. 
// 
// Related Topics Two Pointers String 
// 👍 1627 👎 1934

package leetcode.editor.en;

// 2020-07-26 12:34:56
// Jesse Yang
public class Leetcode0028ImplementStrstr{
    // Java: implement-strstr
    public static void main(String[] args) {
        Solution sol = new Leetcode0028ImplementStrstr().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        
        search:
        for (int i = 0; i <= hLen - nLen; i++) {
            for (int j = 0; j <= nLen; j++) {
                if (j == nLen) {
                    return i;
                }
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    continue search;
                }
            }
        }
        return -1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}