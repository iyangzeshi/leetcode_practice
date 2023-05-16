//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number o
//f rows like this: (you may want to display this pattern in a fixed font for bett
//er legibility) 
//
// 
//P   A   H   size
//A P L S I I G
//Y   I   R
// 
//
// And then read line by line: "PAHNAPLSIIGYIR" 
//
// Write the code that will take a string and make this conversion given a numbe
//r of rows: 
//
// 
//string convert(string s, int numRows);
// 
//
// 
// Example 1: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
// 
//
// Example 2: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//P     I    size
//A   L S  I G
//Y A   H R
//P     I
// 
//
// Example 3: 
//
// 
//Input: s = "A", numRows = 1
//Output: "A"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists of English letters (lower-case and upper-case), ',' and '.'. 
// 1 <= numRows <= 1000 
// 
// Related Topics String 
// 👍 1995 👎 5172

package leetcode.editor.en;

import java.util.*;
// 2020-11-24 21:17:04
// Zeshi Yang
public class Leetcode0006ZigzagConversion{
    // Java: zigzag-conversion
    public static void main(String[] args) {
        Solution sol = new Leetcode0006ZigzagConversion().new Solution();
        // TO TEST
        String str = "PAYPALISHIRING";
        int numRows = 3;
        String res = sol.convert(str, numRows);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        // corner case
        if (s == null || s.length() == 0 || numRows <= 0) {
            return "";
        }
        
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        int len = s.length();
        
        Map<Integer, List<Character>> charToList = new HashMap<>();
        boolean ascending = false;
        int row = 1;
        for (int i = 0; i < len; i++) {
            if (row == 1 || row == numRows) {
                ascending = !ascending;
            }
            char ch = s.charAt(i);
            charToList.computeIfAbsent(row, k -> new ArrayList<>()).add(ch);
            if (ascending) {
                row++;
            } else {
                row--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numRows; i++) {
            List<Character> list = charToList.get(i);
            for (Character ch: list) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}