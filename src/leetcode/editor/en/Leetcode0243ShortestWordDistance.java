//Given an array of strings wordsDict and two different strings that already exi
//st in the array word1 and word2, return the shortest distance between these two 
//words in the list. 
//
// 
// Example 1: 
//
// 
//Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 
//= "coding", word2 = "practice"
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 
//= "makes", word2 = "coding"
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= wordsDict.length <= 3 * 104 
// 1 <= wordsDict[i].length <= 10 
// wordsDict[i] consists of lowercase English letters. 
// word1 and word2 are in wordsDict. 
// word1 != word2 
// 
// Related Topics Array 
// 👍 703 👎 53

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2021-04-21 17:08:27
// Jesse Yang
public class Leetcode0243ShortestWordDistance{
    // Java: shortest-word-distance
    public static void main(String[] args) {
        Solution sol = new Leetcode0243ShortestWordDistance().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        List<Integer> indexes1 = new ArrayList<>();
        List<Integer> indexes2 = new ArrayList<>();
        int len = wordsDict.length;
        for (int i = 0; i < len; i++) {
            String word = wordsDict[i];
            if (word.equals(word1)) {
                indexes1.add(i);
            } else if (word.equals(word2)) {
                indexes2.add(i);
            }
        }
        
        int p1 = 0;
        int p2 = 0;
        int size1 = indexes1.size();
        int size2 = indexes2.size();
        int minDiff = Integer.MAX_VALUE;
        while (p1 < size1 && p2 < size2) {
            int index1 = indexes1.get(p1);
            int index2 = indexes2.get(p2);
            minDiff = Math.min(minDiff, Math.abs(index2 - index1));
            if (index1 < index2) {
                p1++;
            } else {
                p2++;
            }
        }
        return minDiff;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}