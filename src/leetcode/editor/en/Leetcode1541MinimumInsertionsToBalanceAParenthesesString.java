//Given a parentheses string s containing only the characters '(' and ')'. A par
//entheses string is balanced if: 
//
// 
// Any left parenthesis '(' must have a corresponding two consecutive right pare
//nthesis '))'. 
// Left parenthesis '(' must go before the corresponding two consecutive right p
//arenthesis '))'. 
// 
//
// In other words, we treat '(' as openning parenthesis and '))' as closing pare
//nthesis. 
//
// For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" a
//nd "(()))" are not balanced. 
//
// You can insert the characters '(' and ')' at any position of the string to ba
//lance it if needed. 
//
// Return the minimum number of insertions needed to make s balanced. 
//
// 
// Example 1: 
//
// 
//Input: s = "(()))"
//Output: 1
//Explanation: The second '(' has two matching '))', but the first '(' has only 
//')' matching. We need to to add one more ')' at the end of the string to be "(()
//)))" which is balanced.
// 
//
// Example 2: 
//
// 
//Input: s = "())"
//Output: 0
//Explanation: The string is already balanced.
// 
//
// Example 3: 
//
// 
//Input: s = "))())("
//Output: 3
//Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
// 
//
// Example 4: 
//
// 
//Input: s = "(((((("
//Output: 12
//Explanation: Add 12 ')' to balance the string.
// 
//
// Example 5: 
//
// 
//Input: s = ")))))))"
//Output: 5
//Explanation: Add 4 '(' at the beginning of the string and one ')' at the end. 
//The string becomes "(((())))))))".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10^5 
// s consists of '(' and ')' only. 
// 
// Related Topics String Stack 
// 👍 273 👎 66

package leetcode.editor.en;

// 2021-03-09 14:06:15
// Jesse Yang
public class Leetcode1541MinimumInsertionsToBalanceAParenthesesString{
    // Java: minimum-insertions-to-balance-a-parentheses-string
    public static void main(String[] args) {
        Solution sol = new Leetcode1541MinimumInsertionsToBalanceAParenthesesString().new Solution();
        // TO TEST
        String s = ")))))))"; // ")))(),))))))((()))())"
        int res = sol.minInsertions(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
to meet the requirement of the valid parentheses
let the left be the number of the open parentheses, right the closing parentheses
we need to satisfy
    1. at any point, the left * 2 >= right - 1
    2. at the end, the left * 2 = right
    
case 1: '('
    if previous char is ')'
        if can not have its pair —— next ')',
            then we add ')' first
    now right is even number, compare left * 2 and right, add left if need
case 2: ')'
    right++
    flag = !flag
    if it is second closing parenthesis
        compare left * 2 and right, then add parenthesis
     
*/
class Solution {
    public int minInsertions(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int res = 0;
        boolean flag = true; // is second right parenthesis in two consecutive right parentheses
        char[] charArray = s.toCharArray();
        for (char ch : charArray) {
            if (ch == '(') {
                if (!flag) {
                    right++;
                    flag = !flag;
                    res++;
                }
                if (left * 2 < right) {
                    left++;
                    res++;
                }
                left++;
            } else { // means ')'
                right++;
                flag = !flag;
                if (flag) {
                    if (left * 2 < right) {
                        left++;
                        res++;
                    }
                }
            }
        }
        if (!flag) {
            right++;
            res++;
        }
        res += left * 2 < right ? right / 2 - left : (left * 2 - right);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}