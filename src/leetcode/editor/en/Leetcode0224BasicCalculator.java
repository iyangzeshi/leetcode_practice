//Implement a basic calculator to evaluate a simple expression string. 
//
// The expression string may contain open ( and closing parentheses ), the plus 
//+ or minus sign -, non-negative integers and empty spaces . 
//
// Example 1: 
//
// 
//Input: "1 + 1"
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: " 2-1 + 2 "
//Output: 3 
//
// Example 3: 
//
// 
//Input: "(1+(4+5+2)-3)+(6+8)"
//Output: 23 
//Note:
//
// 
// You may assume that the given expression is always valid. 
// Do not use the eval built-in library function. 
// 
// Related Topics Math Stack 
// 👍 1508 👎 137

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:57:15
// Zeshi Yang
public class Leetcode0224BasicCalculator{
    // Java: basic-calculator
    public static void main(String[] args) {
        Solution sol = new Leetcode0224BasicCalculator().new Solution();
        // TO TEST
        String str = "2-1+2 ";
        int res = sol.calculate(str);
        System.out.println(str);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2:
/*
1. 本题同上题III相比，因为只有+和-，所以不需要考虑运算符之间的优先级，可以用1和-1分别代表+和-。
2. 本题由于注明不存在负数，所以不用考虑一上来就是 - 的情况。
3. 为了保证最后加和出结果，我们需要在最后面手动加上一个+号，来促成这最后一次运算。
4. numStack和optrStack在初始以及出现左括号的情况下，初始化都是在numStack里加0，而在optrStack里加1，也就是一起初始化成 0 + 的状态，这样可以避免在call stack的pop时出现NullPointerException()，而同时也不会影响最后的结果。
 */
class Solution {
    public int calculate(String s) {
        // because there is only + - ( ), when we have not confront (, calculate the result, unless push the result
        if (s == null | s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num *10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                result += num * sign;
            }
            else if (c == '+') {
                sign = 1;
            }
            else if (c == '-') {
                sign = -1;
            }
            else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }
            else if (c == ')') {
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1:
/*
设置两个stack,分别是numStack, operStack,前者放数字,后者放符号
opeStack需要先push '(',最后的时候,需要人为加 )
当看到 +- 的时候,
    首先判断是不是第一个符号,或者是 ( 后的第一个符号,是的话,在numStack里面 push 0
    看前面的符号是否是+-,是的话,就像运行前面的符号,把运行结果push到numStack里面.
    否则前面的符号是 ( , 那push当前的符号就可以了
遇到 ( 符号时候, push (;
遇到 ) 的时候 一直运算,直到 ( 为止
遇到空格的时候,什么都不做
 */
class Solution1 {
    public int calculate(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        // general case
        s = s + ')';
        int len = s.length();
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operStack = new Stack<>();
        numStack.push(0);
        operStack.push('(');
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            // edge case, begin with '-' or ('-'
            if (ch == '-' && (i == 0 || s.charAt(i - 1) == '(')) {
                // 如果第一个符号或者括号后面第一个符号是-，就先再数字区域push一个 0
                numStack.push(0);
            } else if (Character.isDigit(ch)) { // 如果是数字，就一直算到数字结束位置
                int num = 0;
                while (i < len && Character.isDigit(ch)) {
                    num = num * 10 + (ch - '0');
                    ch = s.charAt(++i);
                }
                i--;
                numStack.push(num);
            } else if (ch == '(') { // 如果是'(',符号stack里面push一个'('
                operStack.push('(');
            } else if (ch =='+' || ch == '-') {
                // 如果遇到了 +/- 符号,看前面的是不是 +-,如果是的话,先运行前面符号,运行之后,再加上这个符号
                char oper = operStack.peek();
                if (oper == '+' || oper == '-') {
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    operStack.pop();
                    int num = operateNum(num1, oper, num2);
                    numStack.push(num);
                }
                operStack.push(ch);
            } else if (ch == ')') { // 如果是遇到')',就一直往前运算,知道'('为止
                if (operStack.peek() != '(') {
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    char oper = operStack.pop();
                    int num = operateNum(num1, oper, num2);
                    numStack.push(num);
                }
                operStack.pop(); // pop '('

            } else if (ch == ' ') { // 如果是空的话,什么都不做
                continue;
            } else { // 其他符号的画,说明有问题
                throw new RuntimeException("should never happen");
            }
        }
        return numStack.pop();
    }

    private int operateNum(int num1, char oper, int num2) {
        if (oper == '+') {
            return num1 + num2;
        } else if (oper == '-') {
            return num1 - num2;
        } else {
            throw new RuntimeException("should never happen");
        }
    }
}


// Solution 2:
/*
1. 本题同上题III相比，因为只有+和-，所以不需要考虑运算符之间的优先级，可以用1和-1分别代表+和-。
2. 本题由于注明不存在负数，所以不用考虑一上来就是 - 的情况。
3. 为了保证最后加和出结果，我们需要在最后面手动加上一个+号，来促成这最后一次运算。
4. numStack和optrStack在初始以及出现左括号的情况下，初始化都是在numStack里加0，而在optrStack里加1，也就是一起初始化成 0 + 的状态，这样可以避免在call stack的pop时出现NullPointerException()，而同时也不会影响最后的结果。
 */
class Solution2 {
    public int calculate(String s) {
        // because there is only + - ( ), when we have not confront (, calculate the result, unless push the result
        if (s == null | s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num *10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                result += num * sign;
            }
            else if (c == '+') {
                sign = 1;
            }
            else if (c == '-') {
                sign = -1;
            }
            else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }
            else if (c == ')') {
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }
}
}