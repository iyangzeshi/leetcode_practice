//Given an array of strings arr. String s is a concatenation of a sub-sequence o
//f arr which have unique characters. 
//
// Return the maximum possible length of s. 
//
// 
// Example 1: 
//
// 
//Input: arr = ["un","iq","ue"]
//Output: 4
//Explanation: All possible concatenations are "","un","iq","ue","uniq" and "iqu
//e".
//Maximum length is 4.
// 
//
// Example 2: 
//
// 
//Input: arr = ["cha","r","act","ers"]
//Output: 6
//Explanation: Possible solutions are "chaers" and "acters".
// 
//
// Example 3: 
//
// 
//Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
//Output: 26
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 16 
// 1 <= arr[i].length <= 26 
// arr[i] contains only lower case English letters. 
// 
// Related Topics Backtracking Bit Manipulation 
// 👍 647 👎 72

package leetcode.editor.en;

import java.util.Arrays;
import java.util.List;
// 2020-12-23 20:13:58
// Jesse Yang
public class Leetcode1239MaximumLengthOfAConcatenatedStringWithUniqueCharacters{
    // Java: maximum-length-of-a-concatenated-string-with-unique-characters
    public static void main(String[] args) {
        Solution sol = new Leetcode1239MaximumLengthOfAConcatenatedStringWithUniqueCharacters().new Solution();
        // TO TEST
        List<String> list = Arrays.asList("a", "abc", "d", "de", "def");
        int res = sol.maxLength(list);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int maxLength(List<String> arr) {
        // corner case
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        
        int[] max = {0};
        StringBuilder sb = new StringBuilder();
        dfs(0, arr, sb, max);
        return max[0];
    }
    
    // top down dfs, having StringBuilder sb,
    // search the longest length from (sb + string combination from start index to the end)
    private void dfs(int index, List<String> list, StringBuilder sb, int[] max) {
        // base case
        if (index >= list.size()) {
            return;
        }
        int len = sb.length();
        for (int i = index; i < list.size(); i++) {
            String str = list.get(i);
            sb.append(str);
            if (uniqueChar(sb)) {
                max[0] = Math.max(max[0], sb.length());
                dfs(i + 1, list, sb, max);
            }
            sb.setLength(len);
        }
    }
    
    private boolean uniqueChar(StringBuilder sb) {
        // corner case
        if (sb == null || sb.length() == 0) {
            return true;
        }
        
        /*Set<Character> set = new HashSet<>();
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }*/
        // 可以用HashSet来去重，但是实际上也可以用bit操作来去重，因为只包含有小写英文单词
        int mask = 0;
        for (int i = 0; i < sb.length(); i++) {
            if ((mask & 1 << (sb.charAt(i) - 'a')) != 0) {
                return false;
            }
            mask |= (1 << sb.charAt(i) - 'a');
        }
        return true;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}