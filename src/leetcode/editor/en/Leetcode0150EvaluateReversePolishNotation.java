//Evaluate the value of an arithmetic expression in Reverse Polish Notation. 
//
// Valid operators are +, -, *, /. Each operand may be an integer or another exp
//ression. 
//
// Note: 
//
// 
// Division between two integers should truncate toward zero. 
// The given RPN expression is always valid. That means the expression would alw
//ays evaluate to a result and there won't be any divide by zero operation. 
// 
//
// Example 1: 
//
// 
//Input: ["2", "1", "+", "3", "*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
// 
//
// Example 2: 
//
// 
//Input: ["4", "13", "5", "/", "+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
// 
//
// Example 3: 
//
// 
//Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
//Output: 22
//Explanation: 
//  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22
// 
// Related Topics Stack 
// 👍 1074 👎 474

package leetcode.editor.en;

import java.util.Stack;
// 2020-08-04 11:36:08
// Zeshi Yang
public class Leetcode0150EvaluateReversePolishNotation{
    // Java: evaluate-reverse-polish-notation
    public static void main(String[] args) {
        Solution sol = new Leetcode0150EvaluateReversePolishNotation().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (isNumber(str)) {
                stack.push(Integer.parseInt(str));
            } else if (isOperand(str)) {
                stack.push(operate(str, stack.pop(), stack.pop()));
            }
        }
        return stack.pop();
    }
    
    // 检测str是不是数字，"+12"， "-5" 可以转化成数字，"++12"就不能转化成数字
    private boolean isNumber(String str) {
        // corner case
        if (str == null || str.length() == 0) {
            return false;
        }
        char ch = str.charAt(0);
        if (!Character.isDigit(ch)) { // when the first note does not equals to digit
            if (!(ch == '+' || ch == '-') || str.length() == 1) {
                return false;
            }
        }
        // general case
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isOperand(String str) {
        if (str == null || str.length() != 1) {
            return false;
        }
        
        char ch = str.charAt(0);
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }
    
    private int operate(String str, int i1, int i2) {
        char ch = str.charAt(0);
        switch (ch) {
            case '+':
                return i2 + i1;
            case '-':
                return i2 - i1;
            case '*':
                return i2 * i1;
            case '/':
                return i2 / i1;
        }
        return 0;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}