//Given a string s and a non-empty string p, find all the start indices of p's a
//nagrams in s. 
//
// Strings consists of lowercase English letters only and the length of both str
//ings s and p will not be larger than 20,100. 
//
// The order of output does not matter. 
//
// Example 1:
// 
//Input:
//s: "cbaebabacd" p: "abc"
//
//Output:
//[0, 6]
//
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
// 
// 
//
// Example 2:
// 
//Input:
//s: "abab" p: "ab"
//
//Output:
//[0, 1, 2]
//
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".
// 
// Related Topics Hash Table 
// 👍 3391 👎 180

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2020-09-10 20:11:34
// Jesse Yang
public class Leetcode0438FindAllAnagramsInAString{
    // Java: find-all-anagrams-in-a-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0438FindAllAnagramsInAString().new Solution();
        // TO TEST
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res = sol.findAnagrams(s, p);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 2 pointers, T(s, p) = O(s + p), S(s, p) = O(1) 因为只有26个英文字母
class Solution {
    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() < p.length()) {
            return res;
        }
        Map<Character, Integer> charToTimes = new HashMap<>(p.length());
        for (char ch : p.toCharArray()) {
            charToTimes.put(ch, charToTimes.getOrDefault(ch, 0) + 1);
        }
        matchAnagram(s, charToTimes, res);
        return res;
    }
    
    private void matchAnagram(String s, Map<Character, Integer> charToTimes, List<Integer> res) {
        int len = s.length();
        char[] sArr = s.toCharArray();
        int start = 0;
        int end = 0;
        while (end < len) { // [start, end)
            char chEnd = sArr[end];
            if (charToTimes.containsKey(chEnd)) {
                if (charToTimes.get(chEnd) == 1) {
                    charToTimes.remove(chEnd);
                    if (charToTimes.isEmpty()) {
                        res.add(start);
                    }
                } else {
                    charToTimes.put(chEnd, charToTimes.get(chEnd) - 1);
                }
                end++;
            } else { // !map.containsKey(ch)
                if (start == end) { // 这个chEnd字符在p里面没有的时候，就以下一个点为起点看
                    end++;
                    start++;
                    continue;
                }
                char chStart = sArr[start];
                charToTimes.put(chStart, charToTimes.getOrDefault(chStart, 0) + 1);
                start++;
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}