//Given a string containing just the characters '(' and ')', find the length of 
//the longest valid (well-formed) parentheses substring. 
//
// Example 1: 
//
// 
//Input: "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()"
// 
//
// Example 2: 
//
// 
//Input: ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()"
// 
// Related Topics String Dynamic Programming 
// 👍 3772 👎 147

package leetcode.editor.en;

import java.util.Stack;

// 2020-09-06 16:08:29
// Zeshi Yang
public class Leetcode0032LongestValidParentheses {

    // Java: longest-valid-parentheses
    public static void main(String[] args) {
        Solution sol = new Leetcode0032LongestValidParentheses().new Solution();
        // TO TEST
        String s = ")()())";
        int res = sol.longestValidParentheses(s);
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int longestValidParentheses(String s) {
        // corner case
        if (s == null || s.length() < 2) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len + 1];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else  { // s.charAt(i) == ')'
                int j = i - dp[i - 1] - 1;
                if (j >= 0 && s.charAt(j) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (j - 1 >= 0 && s.charAt(j - 1) == ')') {
                        dp[i] += dp[j - 1];
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
// Solution 1: Stack
class Solution1 {

    public int longestValidParentheses(String s) {
        // corner case
        if (s == null || s.length() < 2) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        int res = 0;
        int start = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start);
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }
}

// Solution 2: Dynamic Programming
class Solution2 {

    public int longestValidParentheses(String s) {
        // corner case
        if (s == null || s.length() < 2) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len + 1];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else  { // s.charAt(i) == ')'
                int j = i - dp[i - 1] - 1;
                if (j >= 0 && s.charAt(j) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (j - 1 >= 0 && s.charAt(j - 1) == ')') {
                        dp[i] += dp[j - 1];
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}