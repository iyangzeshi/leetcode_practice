/*
Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators
and empty spaces . The integer division should truncate toward zero.

 Example 1:

 
Input: "3+2*2"
Output: 7
 

 Example 2:

 
Input: " 3/2 "
Output: 1

 Example 3:

 
Input: " 3+5 / 2 "
Output: 5
 

 Note:

 
 You may assume that the given expression is always valid.
 Do not use the eval built-in library function.
 
 Related Topics String
 👍 1450 👎 250
*/

package leetcode.editor.en;

import java.util.Stack;
// 2020-08-04 11:57:21
// Jesse Yang
public class Leetcode0227BasicCalculatorIi{
    // Java: basic-calculator-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0227BasicCalculatorIi().new Solution();
        // TO TEST
        /*String s = "3+2*2";
        int res = sol.calculate(s);
        System.out.println(res);*/
        // int num = Integer.parseInt(" 12");
        char ch = ' ';
        System.out.println(ch - 0);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s + "+ 0";
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (isOperand(ch)) {
                while (!ops.isEmpty() && calculatePreviousOper(ch, ops.peek())) {
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(ch);
            } else {
                throw new RuntimeException("Shall never happen");
            }
        }
        // 到这里为止, ops stack里面就只有加减符号了
        if (!ops.isEmpty()) {
            nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }
    
    private boolean isOperand(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }
    
    /**
     * show whether we should calculate previous operator,
     * return false, only if c1 is * or /, and c2 is + or -;
     * else return true
     */
    private boolean calculatePreviousOper(char c1, char c2) {
        // return 0, only if c1 is * or /, and c2 is + or -; else return 1
        return !((c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-'));
    }
    
    private int operate(char c, int i1, int i2) {
        switch (c) {
            case '+': return i2 + i1;
            case '-': return i2 - i1;
            case '*': return i2 * i1;
            case '/': return i2 / i1;
        }
        return 0;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
//面试的时候，用Solution 2_1

// Solution 1: 用2个stack,按照计算优先级来写
// Solution 1_1: 当计算到最后一个数字,符号栈只可能是有一个 + or -,后面跟着一堆的 * or /,用while循环计算
/**
 * 遇到操作符的时候，计算数值
 * 遇到一个操作符的时候，不能马上计算，因为不知道这个操作符后面的操作符是不是 * ÷，如果是* ÷
 * 要先计算后面的 ×÷，才可以计算当前这个操作符。
 * when meet with the digit, record the whole number
 * when meet with the operator, if it is the first operator, push it;
 * else if it is the * /, if the previous operator is * /, calculate previous one;
 *                        if the previous operator is + -, push operator,
 * else if it is the + -, if the previous operator is * /, calculate previous one,
 *                        if the previous operator is + -, calculate previous one,
 */
class Solution1_1 {
    
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (isOperand(ch)) {
                while (!ops.isEmpty() && calculatePreviousOper(ch, ops.peek())) {
                    nums.push(operate(nums.pop(), ops.pop(), nums.pop()));
                }
                ops.push(ch);
            } else {
                throw new RuntimeException("Shall never happen");
            }
        }
        // 到这里为止, ops stack里面就只有加减符号了
        while (!ops.isEmpty()) {
            nums.push(operate(nums.pop(), ops.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private boolean isOperand(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    
    /**
    * show whether we should calculate previous operator,
    * return false, only if c1 is * or /, and c2 is + or -;
    * else return true
    */
    private boolean calculatePreviousOper(char c1, char c2) {
        return !((c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-'));
    }

    private int operate(int i1, char c, int i2) {
        switch (c) {
            case '+': return i2 + i1;
            case '-': return i2 - i1;
            case '*': return i2 * i1;
            case '/': return i2 / i1;
        }
        return 0;
    }
}

// Solution 1_2: 用2个stack,按照计算优先级来写, 给String s后面添加 + 0,使得最后的操作一定是一个整数
/*
 只有当前运算符的优先级,比前面运算符优先级高的时候,需要push到stack里面,否则都直接计算
 */
class Solution1_2 {
    
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s + "+ 0";
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (isOperand(c)) {
                while (!ops.isEmpty() && calculatePreviousOper(c, ops.peek())) {
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            } else {
                throw new RuntimeException("Shall never happen");
            }
        }
        // 到这里为止, ops stack里面就只有加减符号了
        if (!ops.isEmpty()) {
            nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private boolean isOperand(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    
    /**
    * show whether we should calculate previous operator,
    * return false, only if cur is * or /, and prev is + or -;
    * else return true
    */
    private boolean calculatePreviousOper(char cur, char prev) {
        // return 0, only if cur is * or /, and prev is + or -; else return 1
        return !((cur == '*' || cur == '/') && (prev == '+' || prev == '-'));
    }

    private int operate(char c, int thisNum, int prevNum) {
        switch (c) {
            case '+': return prevNum + thisNum;
            case '-': return prevNum - thisNum;
            case '*': return prevNum * thisNum;
            case '/': return prevNum / thisNum;
        }
        return 0;
    }
}

// Solution 2:
    
/**
 * 设置1个stack<Integer>来存数字就行了,每遇到一个符号，处理前面的符号
 * 如果当前遇到的是+, -:
 *      +:把前面遇到的数字num push进去
 *      -:把前面遇到的数字取相反数变成-num push到stack进去
 * 如果当前遇到的是 * /:
 *      把stack里面的数字stack.pop()/num在push到stack里面
 *
 * 到最后一个数字结束的时候，把stack里面的所有数字求和
 */
class Solution2_1 {
	
	public int calculate(String s) {
		int res = 0;
		int num = 0;
		char opt = '+'; //一开始赋值为+ 因为+的那叉和刚开始处理的逻辑一样 - 合并了
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				num = num * 10 + ch - '0';
			}
			//这里最后一次一定要进一次 不然处理不完
			if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) { // 是操作符，或者是最后一个数字的时候
				if (opt == '+') {
					stack.push(num);
				} else if (opt == '-') {
					stack.push(-num);
				} else if (opt == '/') {
					stack.push(stack.pop() / num);
				} else if (opt == '*') {
					stack.push(stack.pop() * num);
				}
				num = 0;
				opt = ch;//这里必须是先赋值 后一轮处理这个符号
			}
		}
		
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;
	}

}

class Solution2_2 {
    
    public int calculate(String s) {
        int res = 0;
        int num = 0;
        char opt = '+'; //上一个符号，一开始赋值为+ 因为+的那叉和刚开始处理的逻辑一样 - 合并了
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }
            //这里最后一次一定要进一次 不然处理不完
            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
                if (opt == '+' || opt == '-') {
                    stack.push(operate(0, opt, num));
                } else { // opt == '*' || opt == '/'
                    stack.push(operate(stack.pop(), opt, num));
                }
                num = 0;
                opt = ch;//这里必须是先赋值 后一轮处理这个符号
            }
        }
        
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
    
    private int operate(int prevNum, char c, int thisNum) {
        switch (c) {
            case '+': return prevNum + thisNum;
            case '-': return prevNum - thisNum;
            case '*': return prevNum * thisNum;
            case '/': return prevNum / thisNum;
        }
        return 0;
    }
    
}


// Solution 3:
/**
 * 用cur表示到目前为止的计算值, 再keep 一个int prev表示前面一个值, 一个char oper表示当前符号
 * 当遇到 +-: cur = cal(cur, oper, num)
 * 当遇到 * / : 用cur = cur  - prev + cal(prev, ch, num)
 */
class Solution3 {
    
    public int calculate(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        // general case
        int cur = 0;
        int prev = 0;
        Character oper = null;
        char[] charArray= s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                i++;
                while (i < charArray.length && Character.isDigit(charArray[i])) {
                    num = num * 10 + charArray[i] - '0';
                    i++;
                }
                i--;
                if (oper != null) {
                    if (oper == '+' || oper == '-') {
                        cur = operate(cur, oper, num);
                        prev = operate(0, oper, num);
                    } else { // is * or /
                        cur = cur - prev + operate(prev, oper, num);
                        prev = operate(prev, oper, num);
                    }
                } else { // oper is null means it is first number
                    cur = num;
                    prev = num;
                }
            } else if (isOperand(ch)) {
                oper = ch;
            } else {
                throw new RuntimeException("Shall never happen");
            }
        }
        return cur;

    }

    private boolean isOperand(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private int operate(int i1, char c, int i2) {
        switch (c) {
            case '+': return i1 + i2;
            case '-': return i1 - i2;
            case '*': return i1 * i2;
            case '/': return i1 / i2;
        }
        return 0;
    }
}

}