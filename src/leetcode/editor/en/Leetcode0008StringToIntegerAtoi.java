//Implement atoi which converts a string to an integer. 
//
// The function first discards as many whitespace characters as necessary until 
//the first non-whitespace character is found. Then, starting from this character,
// takes an optional initial plus or minus sign followed by as many numerical digi
//ts as possible, and interprets them as a numerical value. 
//
// The string can contain additional characters after those that form the integr
//al number, which are ignored and have no effect on the behavior of this function
//. 
//
// If the first sequence of non-whitespace characters in str is not a valid inte
//gral number, or if no such sequence exists because either str is empty or it con
//tains only whitespace characters, no conversion is performed. 
//
// If no valid conversion could be performed, a zero value is returned. 
//
// Note: 
//
// 
// Only the space character ' ' is considered as whitespace character. 
// Assume we are dealing with an environment which could only store integers wit
//hin the 32-bit signed integer range: [−231, 231 − 1]. If the numerical value is 
//out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is
// returned. 
// 
//
// Example 1: 
//
// 
//Input: "42"
//Output: 42
// 
//
// Example 2: 
//
// 
//Input: "   -42"
//Output: -42
//Explanation: The first non-whitespace character is '-', which is the minus sig
//n.
//             Then take as many numerical digits as possible, which gets 42.
// 
//
// Example 3: 
//
// 
//Input: "4193 with words"
//Output: 4193
//Explanation: Conversion stops at digit '3' as the next character is not a nume
//rical digit.
// 
//
// Example 4: 
//
// 
//Input: "words and 987"
//Output: 0
//Explanation: The first non-whitespace character is 'w', which is not a numeric
//al 
//             digit or a +/- sign. Therefore no valid conversion could be perfo
//rmed. 
//
// Example 5: 
//
// 
//Input: "-91283472332"
//Output: -2147483648
//Explanation: The number "-91283472332" is out of the range of a 32-bit signed 
//integer.
//             Thefore INT_MIN (−231) is returned. 
// Related Topics Math String 
// 👍 1731 👎 9944

package leetcode.editor.en;

// 2020-09-10 15:14:08
// Zeshi Yang
public class Leetcode0008StringToIntegerAtoi{
    // Java: string-to-integer-atoi
    public static void main(String[] args) {
        Solution sol = new Leetcode0008StringToIntegerAtoi().new Solution();
        // TO TEST
        String str = "4193-2 with words";
        int res = sol.myAtoi(str);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int myAtoi(String str) {
        String newStr = str.trim();
        if (newStr.length() == 0) {
            return 0;
        }
        int sign = 1;
        char[] chArr;
        char firstChar = newStr.charAt(0);
        if (firstChar == '-') {
            sign= -1;
            chArr = newStr.substring(1).toCharArray();
        } else if (firstChar == '+') {
            chArr = newStr.substring(1).toCharArray();
        } else if (!Character.isDigit(firstChar)) {
            return 0;
        } else {
            chArr = newStr.toCharArray();
        }
		
        long num = 0;
        for (char ch : chArr) {
            if (Character.isDigit(ch)) {
                num = num * 10 + sign * (ch - '0');
                if (num > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (num < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else { // not number
                return (int) num;
            }
        }
        return (int) num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}