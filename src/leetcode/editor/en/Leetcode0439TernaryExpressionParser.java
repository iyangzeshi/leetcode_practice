//Given a string representing arbitrarily nested ternary expressions, calculate 
//the result of the expression. You can always assume that the given expression is
// valid and only consists of digits 0-9, ?, :, T and F (T and F represent True an
//d False respectively).
//
// Note:
// 
// The length of the given string is ≤ 10000. 
// Each number will contain only one digit. 
// The conditional expressions group right-to-left (as usual in most languages).
// 
// The condition will always be either T or F. That is, the condition will never
// be a digit. 
// The result of the expression will always evaluate to either a digit 0-9, T or
// F. 
// 
// 
//
// 
//Example 1:
// 
//Input: "T?2:3"
//
//Output: "2"
//
//Explanation: If true, then result is 2; otherwise result is 3.
// 
// 
//
// 
//Example 2:
// 
//Input: "F?1:T?4:5"
//
//Output: "4"
//
//Explanation: The conditional expressions group right-to-left. Using parenthesi
//s, it is read/evaluated as:
//
//             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
//
//          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
//          -> "4"                                    -> "4"
// 
// 
//
// 
//Example 3:
// 
//Input: "T?T?F:5:3"
//
//Output: "F"
//
//Explanation: The conditional expressions group right-to-left. Using parenthesi
//s, it is read/evaluated as:
//
//             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
//
//          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
//          -> "F"                                    -> "F"
// 
// Related Topics Stack Depth-first Search 
// 👍 252 👎 30

package leetcode.editor.en;

import java.util.Stack;

// 2020-08-10 15:50:55
// Jesse Yang
public class Leetcode0439TernaryExpressionParser {

	// Java: ternary-expression-parser
	public static void main(String[] args) {
		Solution sol = new Leetcode0439TernaryExpressionParser().new Solution();
		// TO TEST
		String str = "T?T?F:5:3";
		String res = sol.parseTernary(str);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String parseTernary(String expression) {
        // corner case
        if (expression == null || expression.length() == 0) {
            return "";
        }
        // general case
        Stack<Character> stack = new Stack<>();
        int len = expression.length();
        boolean flagCon = false; // if previous sign is ?, true; else flase.
        for (int i = len - 1; i >= 0; i--) {
            char ch = expression.charAt(i);
            if (ch == ':') {
                flagCon = false;
            } else if (ch == '?') {
                flagCon = true;
            } else if (ch == ' ') {
                continue;
            } else if (ch == 'T' || ch == 'F') {
                if (flagCon) { // previous condition is  ?
                    Character tStr = stack.pop(); // String of true
                    Character fStr = stack.pop(); // String of false
                    if (ch == 'T') {
                        stack.push(tStr);
                    } else {
                        stack.push(fStr);
                    }
                } else { // previous condition is :
                    stack.push(ch);
                }
            } else if (Character.isLetterOrDigit(ch)) {
                stack.push(ch);
            } else {
                throw new RuntimeException("Unknown character");
            }
        }
        return String.valueOf(stack.pop());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}