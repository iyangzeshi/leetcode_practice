//Given a string S of '(' and ')' parentheses, we add the minimum number of pare
//ntheses ( '(' or ')', and in any positions ) so that the resulting parentheses s
//tring is valid. 
//
// Formally, a parentheses string is valid if and only if: 
//
// 
// It is the empty string, or 
// It can be written as AB (A concatenated with B), where A and B are valid stri
//ngs, or 
// It can be written as (A), where A is a valid string. 
// 
//
// Given a parentheses string, return the minimum number of parentheses we must 
//add to make the resulting string valid. 
//
// 
//
// Example 1: 
//
// 
//Input: "())"
//Output: 1
// 
//
// 
// Example 2: 
//
// 
//Input: "((("
//Output: 3
// 
//
// 
// Example 3: 
//
// 
//Input: "()"
//Output: 0
// 
//
// 
// Example 4: 
//
// 
//Input: "()))(("
//Output: 4 
//
// 
// 
// 
// 
//
// Note: 
//
// 
// S.length <= 1000 
// S only consists of '(' and ')' characters. 
// 
//
// 
// 
// 
// 
// 
// 
// Related Topics Stack Greedy 
// 👍 1092 👎 79

package leetcode.editor.en;

import java.util.Stack;

// 2021-03-09 14:23:05
// Zeshi Yang
public class Leetcode0921MinimumAddToMakeParenthesesValid{
    // Java: minimum-add-to-make-parentheses-valid
    public static void main(String[] args) {
        Solution sol = new Leetcode0921MinimumAddToMakeParenthesesValid().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution { // 这个答案比较简单，你也方便说
    
    public int minAddToMakeValid(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (char ch: S.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    res++; // add '('
                } else { // stack.peek() == '('
                    stack.pop();
                }
            }
        }
        // post processing
        res += stack.size(); // add ')'
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

/** 面试的时候用Solution 1 */

// Solution 1: compute the difference left and right parentheses, T(n) = O(n), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 36.9 MB,击败了92.52% 的Java用户
/*
to meet the requirement of the valid parentheses
let the left be the number of the open parentheses, right the closing parentheses
we need to satisfy
    1. at any point, the left >= right
    2. at the end, the left = right
so the delta be the difference between left and right, delta = left - right
    1. at any point, the delta >= 0
    2. at the end, the delta = 0
*/
class Solution1 {
    
    public int minAddToMakeValid(String S) {
        // corner case
        if (S == null || S.length() == 0) {
            return 0;
        }
        int delta = 0;
        int res = 0;
        for (char ch : S.toCharArray()) {
            if (ch == '(') {
                delta++;
            } else { // means ')'
                delta--;
                if (delta < 0) {
                    res++;
                    delta++;
                }
            }
        }
        res += delta;
        return res;
    }
    
}

// Solution 2: Stack T(n) = O(n), S(n) = O(1)
// 1 ms,击败了53.47% 的Java用户, 37.3 MB,击败了45.54% 的Java用户
/*
idea : stack only store the '(', match the '(' in the peek of stack and current char ')'
case 1: '(', just push it into stack
case 2: ')',
    if stack.peek() is empty
        res++;
    if stack.peek() is '('
        stack.pop();
 */
class Solution2 { // 这个答案比较简单，你也方便说
    
    public int minAddToMakeValid(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (char ch: S.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    res++; // add '('
                } else { // stack.peek() == '('
                    stack.pop();
                }
            }
        }
        // post processing
        res += stack.size(); // add ')'
        return res;
    }
    
}
}