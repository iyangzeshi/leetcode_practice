//We can shift a string by shifting each of its letters to its successive 
//letter. 
//
// 
// For example, "abc" can be shifted to be "bcd". 
// 
//
// We can keep shifting the string to form a sequence. 
//
// 
// For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" 
//-> ... -> "xyz". 
// 
//
// Given an array of strings strings, group all strings[i] that belong to the 
//same shifting sequence. You may return the answer in any order. 
//
// 
// Example 1: 
// Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
//Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
// 
// Example 2: 
// Input: strings = ["a"]
//Output: [["a"]]
// 
// 
// Constraints: 
//
// 
// 1 <= strings.length <= 200 
// 1 <= strings[i].length <= 50 
// strings[i] consists of lowercase English letters. 
// 
//
// Related Topics Array Hash Table String 👍 1617 👎 346

package leetcode.editor.en;

import java.util.List;

// 2024-01-16 17:32:00
// Jesse Yang
public class Leetcode0249GroupShiftedStrings{
    // Java: group-shifted-strings
    public static void main(String[] args) {
        Solution sol = new Leetcode0249GroupShiftedStrings().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}