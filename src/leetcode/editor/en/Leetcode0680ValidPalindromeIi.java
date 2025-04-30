/**
Given a string s, return true if the s can be palindrome after deleting at most 
one character from it. 

 
 Example 1: 

 
Input: s = "aba"
Output: true
 

 Example 2: 

 
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
 

 Example 3: 

 
Input: s = "abc"
Output: false
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of lowercase English letters. 
 

 Related Topics Two Pointers String Greedy 👍 8323 👎 455

*/
package leetcode.editor.en;

// 2024-10-18 14:07:54
// Jesse Yang
public class Leetcode0680ValidPalindromeIi{
    // Java: valid-palindrome-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0680ValidPalindromeIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean validPalindrome(String s) {
        //cc
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return helper(s, i + 1, j) || helper(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }
    
    private boolean helper(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}