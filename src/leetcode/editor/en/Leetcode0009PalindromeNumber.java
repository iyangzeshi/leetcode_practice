//Determine whether an integer is a palindrome. An integer is a palindrome when 
//it reads the same backward as forward. 
//
// Follow up: Could you solve it without converting the integer to a string? 
//
// 
// Example 1: 
//
// 
//Input: x = 121
//Output: true
// 
//
// Example 2: 
//
// 
//Input: x = -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes
// 121-. Therefore it is not a palindrome.
// 
//
// Example 3: 
//
// 
//Input: x = 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
// 
//
// Example 4: 
//
// 
//Input: x = -101
//Output: false
// 
//
// 
// Constraints: 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics Math 
// 👍 2833 👎 1657

package leetcode.editor.en;

// 2020-12-16 19:22:02
// Jesse Yang
public class Leetcode0009PalindromeNumber{
    // Java: palindrome-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0009PalindromeNumber().new Solution();
        // TO TEST
        int x = 121;
        boolean res = sol.isPalindrome(x);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(int x) {
        // corner case
        if (x < 0 || x != 0 && x % 10 == 0) {
            return false;
        } else if (x == 0) {
            return true;
        }
        int num = x;
        int reversedNum = 0;
        while (x != 0) {
            reversedNum = reversedNum * 10 + x % 10;
            x /= 10;
        }
        return num == reversedNum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}