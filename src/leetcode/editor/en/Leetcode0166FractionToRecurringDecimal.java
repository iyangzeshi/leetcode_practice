//Given two integers representing the numerator and denominator of a fraction, r
//eturn the fraction in string format. 
//
// If the fractional part is repeating, enclose the repeating part in parenthese
//s. 
//
// If multiple answers are possible, just return any of them. 
//
// Example 1: 
//
// 
//Input: numerator = 1, denominator = 2
//Output: "0.5"
// 
//
// Example 2: 
//
// 
//Input: numerator = 2, denominator = 1
//Output: "2" 
//
// Example 3: 
//
// 
//Input: numerator = 2, denominator = 3
//Output: "0.(6)"
// 
// Related Topics Hash Table Math 
// 👍 887 👎 1873

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:44:04
// Jesse Yang
public class Leetcode0166FractionToRecurringDecimal{
    // Java: fraction-to-recurring-decimal
    public static void main(String[] args) {
        Solution sol = new Leetcode0166FractionToRecurringDecimal().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) -2^31 overflows
        long dividend = Math.abs((long)(numerator));
        long divisor = Math.abs((long)(denominator));
        fraction.append(dividend / divisor);
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(remainder / divisor);
            remainder %= divisor;
        }
        return fraction.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}