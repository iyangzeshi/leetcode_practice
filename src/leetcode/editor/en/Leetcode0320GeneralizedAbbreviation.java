//Write a function to generate the generalized abbreviations of a word. 
//
// Note: The order of the output does not matter. 
//
// Example: 
//
// 
//Input: "word"
//Output:
//["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", 
//"w1r1", "1o2", "2r1", "3d", "w3", "4"]
// 
//
// 
// Related Topics Backtracking Bit Manipulation

package leetcode.editor.en;

import java.util.*;

public class Leetcode0320GeneralizedAbbreviation {
    // Java: generalized-abbreviation
    public static void main(String[] args) {
        Solution sol = new Leetcode0320GeneralizedAbbreviation().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        if (word == null) return res;
    
        dfs(0, 0, new StringBuilder(), word, res);
        return res;
    }
    
    /**
     * @param index: location of current char
     * @param count: the number of consecutive 0 until current char
     * @param path: path
     * @param word: target word
     * @param res: the result of dfs
     */
    private void dfs(int index, int count, StringBuilder path, String word, List<String> res) {
        // base case
        // success
        int len = path.length();
        if (index == word.length()) {
            if (count != 0) {
                path.append(count);
            }
            res.add(path.toString());
            path.setLength(len);
            return;
        }
        
        // regard this char as a number, and count 1
        dfs(index + 1, count + 1, path, word, res);
        
        // keep original char
        if (count != 0) {
            path.append(count);
        }
        path.append(word.charAt(index));
        dfs(index + 1, 0, path, word, res);
        path.setLength(len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}