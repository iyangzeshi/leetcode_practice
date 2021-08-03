//You are given a string s that consists of lower case English letters and brack
//ets. 
//
// Reverse the strings in each pair of matching parentheses, starting from the i
//nnermost one. 
//
// Your result should not contain any brackets. 
//
// 
// Example 1: 
//
// 
//Input: s = "(abcd)"
//Output: "dcba"
// 
//
// Example 2: 
//
// 
//Input: s = "(u(love)i)"
//Output: "iloveu"
//Explanation: The substring "love" is reversed first, then the whole string is 
//reversed.
// 
//
// Example 3: 
//
// 
//Input: s = "(ed(et(oc))el)"
//Output: "leetcode"
//Explanation: First, we reverse the substring "oc", then "etco", and finally, t
//he whole string.
// 
//
// Example 4: 
//
// 
//Input: s = "a(bcdefghijkl(mno)p)q"
//Output: "apmnolkjihgfedcbq"
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 2000 
// s only contains lower case English characters and parentheses. 
// It's guaranteed that all parentheses are balanced. 
// 
// Related Topics Stack 
// 👍 573 👎 23

package leetcode.editor.en;

import java.util.*;
// 2020-12-21 18:38:41
// Zeshi Yang
public class Leetcode1190ReverseSubstringsBetweenEachPairOfParentheses{
    // Java: reverse-substrings-between-each-pair-of-parentheses
    public static void main(String[] args) {
        Solution sol = new Leetcode1190ReverseSubstringsBetweenEachPairOfParentheses().new Solution();
        // TO TEST
        String s = "a(bcdefghijkl(mno)p)q";
        String res = sol.reverseParentheses(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char ch: s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                sb.setLength(0);
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                for (int i = 0; i < sb.length(); i++) {
                    stack.push(sb.charAt(i));
                }
            } else {
                stack.push(ch);
            }
        }
        sb.setLength(0);
        for(char ch: stack) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
