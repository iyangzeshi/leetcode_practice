//Roman numerals are represented by seven different symbols: I, V, X, L, C, D an
//d M. 
//
// 
//Symbol       Value
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// For example, two is written as II in Roman numeral, just two one's added toge
//ther. Twelve is written as, XII, which is simply X + II. The number twenty seven
// is written as XXVII, which is XX + V + II. 
//
// Roman numerals are usually written largest to smallest from left to right. Ho
//wever, the numeral for four is not IIII. Instead, the number four is written as 
//IV. Because the one is before the five we subtract it making four. The same prin
//ciple applies to the number nine, which is written as IX. There are six instance
//s where subtraction is used: 
//
// 
// I can be placed before V (5) and X (10) to make 4 and 9. 
// X can be placed before L (50) and C (100) to make 40 and 90. 
// C can be placed before D (500) and M (1000) to make 400 and 900. 
// 
//
// Given an integer, convert it to a roman numeral. Input is guaranteed to be wi
//thin the range from 1 to 3999. 
//
// Example 1: 
//
// 
//Input: 3
//Output: "III" 
//
// Example 2: 
//
// 
//Input: 4
//Output: "IV" 
//
// Example 3: 
//
// 
//Input: 9
//Output: "IX" 
//
// Example 4: 
//
// 
//Input: 58
//Output: "LVIII"
//Explanation: L = 50, V = 5, III = 3.
// 
//
// Example 5: 
//
// 
//Input: 1994
//Output: "MCMXCIV"
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4. 
// Related Topics Math String 
// 👍 1117 👎 2676

package leetcode.editor.en;

// 2020-08-04 11:09:05
// Jesse Yang
public class Leetcode0012IntegerToRoman{
    // Java: integer-to-roman
    public static void main(String[] args) {
        Solution sol = new Leetcode0012IntegerToRoman().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String intToRoman(int num) {
        int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        
        int i = 0;
        while (num > 0) {
            int k = num / vals[i];
            for (int j = 0; j < k; j++) {
                sb.append(romans[i]);
                num -= vals[i];
            }
            i++;
        }
        return sb.toString();
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}