//Given a string, determine if it is a palindrome, considering only alphanumeric
// characters and ignoring cases. 
//
// Note: For the purpose of this problem, we define empty string as valid palind
//rome. 
//
// Example 1: 
//
// 
//Input: "A man, a plan, a canal: Panama"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: "race a car"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// s consists only of printable ASCII characters. 
// 
// Related Topics Two Pointers String 
// 👍 1234 👎 2956

package leetcode.editor.en;

// 2020-07-26 12:35:32
// Zeshi Yang
public class Leetcode0125ValidPalindrome{
    // Java: valid-palindrome
    public static void main(String[] args) {
        Solution sol = new Leetcode0125ValidPalindrome().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase();
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}