//Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
// determine if the input string is valid. 
//
// An input string is valid if: 
//
// 
// Open brackets must be closed by the same type of brackets. 
// Open brackets must be closed in the correct order. 
// 
//
// Note that an empty string is also considered valid. 
//
// Example 1: 
//
// 
//Input: "()"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: "()[]{}"
//Output: true
// 
//
// Example 3: 
//
// 
//Input: "(]"
//Output: false
// 
//
// Example 4: 
//
// 
//Input: "([)]"
//Output: false
// 
//
// Example 5: 
//
// 
//Input: "{[]}"
//Output: true
// 
// Related Topics String Stack 
// 👍 5175 👎 225

package leetcode.editor.en;

import java.util.Stack;
// 2020-07-26 12:14:56
// Zeshi Yang
public class Leetcode0020ValidParentheses{
    // Java: valid-parentheses
    public static void main(String[] args) {
        Solution sol = new Leetcode0020ValidParentheses().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean isValid(String s) {
        // S2: record the right part of the parentheses by stack
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
                
            }
            /*
             notice that must be stack.pop() instead of stack.peek(), because
             if it matches, the right part of parentheses shall be deleted
            */
            else if (stack.isEmpty() || stack.pop() != c) {
                
                return false;
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 把所有的括号都记在stack里面，遇到右括号的时候和stack的头比较是否相等
class Solution1 {
    
    public boolean isValid(String s) {
        // S1: compeletely record the parentheses by stack
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (c == ')') {
                        if (stack.isEmpty() || stack.pop() != '(') {
                            return false;
                        }
                    }
                    if (c == ']') {
                        if (stack.isEmpty() || stack.pop() != '[') {
                            return false;
                        }
                    }
                    if (c == '}') {
                        if (stack.isEmpty() || stack.pop() != '{') {
                            return false;
                        }
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}

// Solution 2: 把所有的括号都记在stack里面
class Solution2 {
    
    public boolean isValid(String s) {
        // S2: record the right part of the parentheses by stack
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
        
            }
            /*
             notice that must be stack.pop() instead of stack.peek(), because
             if it matches, the right part of parentheses shall be deleted
            */
            else if (stack.isEmpty() || stack.pop() != c) {
        
                return false;
            }
        }
        return stack.isEmpty();
    }
}
}