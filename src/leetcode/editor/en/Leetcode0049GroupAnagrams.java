//Given an array of strings, group anagrams together. 
//
// Example: 
//
// 
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// Note: 
//
// 
// All inputs will be in lowercase. 
// The order of your output does not matter. 
// 
// Related Topics Hash Table String 
// 👍 3715 👎 190

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 2020-08-04 11:22:56
// Jesse Yang
public class Leetcode0049GroupAnagrams{
    // Java: group-anagrams
    public static void main(String[] args) {
        Solution sol = new Leetcode0049GroupAnagrams().new Solution();
        // TO TEST
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = sol.groupAnagrams(strs);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/* T(n, k) = O(n*k), S(n,k) = O(n,k),
assuming n is number of words, k is  length of longest word
build a hashmap to traverse all the words
the key is the char count of that anagram, value is the list of anagram of the key
 */
 
class Solution {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int['z' - 'a' + 1];
        for (String str : strs) {
            Arrays.fill(count, 0);
            for (char ch : str.toCharArray()) {
                count[ch - 'a']++;
            }
            
            String key = Arrays.toString(count);
            ans.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(ans.values());
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

/*
Solution 1: T(n, k) = O(n*k*log(n)), S(n,k) = O(n,k),
assuming n is number of words, k is  length of longest word
build a hashmap to traverse all the words and sort that word
the key is the anagram, value is the list of anagram of the key
 */
 
class Solution1 {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        // 此排序是为了让每个List<String>的结果按照字典序输出
        // 但是并不需要，此题没要求
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            // 将char数组按照字典顺序排序
            Arrays.sort(chs);
            String sortStr = String.valueOf(chs);
            // 排序后的String作为key，
            map.computeIfAbsent(sortStr, k -> new ArrayList<>()).add(str);
        }
        // 注意ArrayList的构造方法的应用
        return new ArrayList<>(map.values());
    }
    
}

/*
Solution 2: T(n, k) = O(n*k), S(n,k) = O(n,k),
assuming n is number of words, k is  length of longest word
build a hashmap to traverse all the words
the key is the anagram, value is the list of anagram of the key
 */
class Solution2 {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int['z' - 'a' + 1];
        for (String str : strs) {
            Arrays.fill(count, 0);
            for (char ch : str.toCharArray()) {
                count[ch - 'a']++;
            }
            
            String key = Arrays.toString(count);
            ans.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(ans.values());
    }
    
}
}