//Design a data structure that will be initialized with a string array, and then
// it should answer queries of the shortest distance between two different strings
// from the array. 
//
// Implement the WordDistance class: 
//
// 
// WordDistance(String[] wordsDict) initializes the object with the strings arra
//y wordsDict. 
// int shortest(String word1, String word2) returns the shortest distance betwee
//n word1 and word2 in the array wordsDict. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["WordDistance", "shortest", "shortest"]
//[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"]
//, ["makes", "coding"]]
//Output
//[null, 3, 1]
//
//Explanation
//WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", 
//"coding", "makes"]);
//wordDistance.shortest("coding", "practice"); // return 3
//wordDistance.shortest("makes", "coding");    // return 1
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
// At most 5000 calls will be made to shortest. 
// 
// Related Topics Hash Table Design 
// 👍 503 👎 152

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2021-04-21 16:46:57
// Zeshi Yang
public class Leetcode0244ShortestWordDistanceIi{
    // Java: shortest-word-distance-ii
    public static void main(String[] args) {
        // WordDistance wordDistance = new Leetcode0244ShortestWordDistanceIi().new WordDistance();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
先建立map，key是word，value是这个word对应的index（从小到大排序）
搜索的时候，把这两个word对应的index，双指针从左边到右边扫描。谁小移动谁。
 */
class WordDistance {

    Map<String, List<Integer>> map;
    
    public WordDistance(String[] wordsDict) {
        map = new HashMap<>();
        int len = wordsDict.length;
        for (int i = 0; i < len; i++) {
            String str = wordsDict[i];
            map.computeIfAbsent(str, k -> new ArrayList<>()).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> indexes1 = map.get(word1);
        List<Integer> indexes2 = map.get(word2);
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

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}