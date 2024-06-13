//You are given a string s and an array of strings words of the same length. Ret
//urn all starting indices of substring(s) in s that is a concatenation of each wo
//rd in words exactly once, in any order, and without any intervening characters. 
//
//
// You can return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: s = "barfoothefoobarman", words = ["foo","bar"]
//Output: [0,9]
//Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" re
//spectively.
//The output order does not matter, returning [9,0] is fine too.
// 
//
// Example 2: 
//
// 
//Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//Output: []
// 
//
// Example 3: 
//
// 
//Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//Output: [6,9,12]
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 104 
// s consists of lower-case English letters. 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] consists of lower-case English letters. 
// 
// Related Topics Hash Table Two Pointers String 
// 👍 1058 👎 1361

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2020-11-28 16:48:43
// Jesse Yang
public class Leetcode0030SubstringWithConcatenationOfAllWords{
    // Java: substring-with-concatenation-of-all-words
    public static void main(String[] args) {
        Solution sol = new Leetcode0030SubstringWithConcatenationOfAllWords().new Solution();
        // TO TEST
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words = {"fooo","barr","wing","ding","wing"};
        List<Integer> res = sol.findSubstring(s, words);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2:Assuming length of s is n, number of words is m, T(n) = O(n), S(n) = O(m)
// 执行耗时:8 ms,击败了91.51% 的Java用户, 内存消耗:39.6 MB,击败了61.67% 的Java用户
/**
 * 分类讨论，moving window,一次分别走左边或者走右边
 * 把words里面的所有的单词存到HashMap里面， 因为可能有重复，所以key是String，value是int，表示出现的次数，
 * 把目前window里面出现的单词和次数存到另外一个HashMap里面，(key, value) 是 (word, times),然后一个个走
 * 如果新进来的单词是words里面有的，
 *      如果当前word出现的次数 < words里面的
 *          更新目前word的出现次数
 *          如果匹配到了concatenation
 *              加到结果里面
 *          否则，continue
 *      否则，左边走1格
 * 否则，重新开始
 * 分别以start = 1 ~ wordLen - 1为，重复上面的操作
 */
class Solution {
    
    // time = O(n), space = O(k)   n: string的长度； k: words的个数
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> wordToTimes = countWord(words);
        int wordLen = words[0].length();
        for (int start = 0; start < wordLen; start++) {
            traverseWordFromStart(start, s, words, wordToTimes, res);
        }
        return res;
    }
    
    private Map<String, Integer> countWord(String[] words) {
        Map<String, Integer> wordToTimes = new HashMap<>();
        for (String str : words) {
            wordToTimes.put(str, wordToTimes.getOrDefault(str, 0) + 1);
        }
        return wordToTimes;
    }
    
    private void traverseWordFromStart(int start, String s, String[] words,
            Map<String, Integer> wordToTimes, List<Integer> res) {
        int len = s.length();
        int wordLen = words[0].length();
        int[] left = {start};
        int right = left[0];
        int[] windowSize = {0};
        Map<String, Integer> wordOccured = new HashMap<>();
        while (right + wordLen <= len) {
            String rightStr = s.substring(right, right + wordLen);
            if (wordToTimes.containsKey(rightStr)) {
                wordOccured.putIfAbsent(rightStr, 0);
                /*
                 * 如果出现次数小于要求的话，加到window里面
                 */
                if (wordOccured.get(rightStr) < wordToTimes.get(rightStr)) {
                    wordOccured.put(rightStr, wordOccured.get(rightStr) + 1);
                    windowSize[0]++;
                    right += wordLen;
                    if (windowSize[0] == words.length) {
                        res.add(left[0]);
                        // leftPointGoRight
                        LeftPointGoRightBy1Word(left, s, wordOccured, wordLen, windowSize);
                    }
                } else {
                    // leftPointGoRight
                    LeftPointGoRightBy1Word(left, s, wordOccured, wordLen, windowSize);
                }
            } else {
                // 遇到words里面没有的单词，reset，从right这个地方开始
                wordOccured.clear();
                right += wordLen;
                left[0] = right;
                windowSize[0] = 0;
            }
        }
    }
    
    private void LeftPointGoRightBy1Word(int[] left, String s, Map<String, Integer> wordOccured,
            int wordLen, int[] windowSize) {
        String leftStr = s.substring(left[0], left[0] + wordLen);
        wordOccured.put(leftStr, wordOccured.get(leftStr) - 1);
        left[0] += wordLen;
        windowSize[0]--;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// 面试的时候，用写法1_2

// Solution 1_1: Assuming length of s is n, number of words is m, T(n) = O(n * m), S(n) = O(m)
// 执行耗时:225 ms,击败了20.16% 的Java用户, 内存消耗:39.6 MB,击败了61.67% 的Java用户
/**
 * 假设words的所有单词长度合为lenSum，
 * 在s String里，按顺序一个个遍历所有长度为lenSum的字符串，看是否符合条件
 */
class Solution1_1 {
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        int wordNum = words.length;
        int wordLen = words[0].length();
        int subStringLen = wordLen * wordNum;
        int sLen = s.length();
        Map<String, Integer> wordCount = countWord(words);
        for (int left = 0; left <= sLen - subStringLen; left++) {
            int right = left + subStringLen;
            Map<String, Integer> wordsDeleted = new HashMap<>(wordCount);
            for (int i = left; i < right; i += wordLen) {
                String str = s.substring(i, i + wordLen);
                if (wordsDeleted.containsKey(str)) {
                    int times = wordsDeleted.get(str);
                    if (times == 1) {
                        wordsDeleted.remove(str);
                    } else {
                        wordsDeleted.put(str, wordsDeleted.get(str) - 1);
                    }
                }
            }
            if (wordsDeleted.isEmpty()) {
                res.add(left);
            }
        }
        return res;
    }
    
    private Map<String, Integer> countWord(String[] words) {
        Map<String, Integer> wordToTimes = new HashMap<>();
        for (String str : words) {
            wordToTimes.put(str, wordToTimes.getOrDefault(str, 0) + 1);
        }
        return wordToTimes;
    }
    
}

// Solution 1_2:Assuming length of s is n, number of words is m, T(n) = O(n), S(n) = O(m)
// 执行耗时:15 ms,击败了85.74% 的Java用户, 内存消耗:39.5 MB,击败了74.80% 的Java用户
/**
 *  假设words的所有单词长度合为lenSum，在s String里面遍历所有长度为lenSum的字符串，看是否符合条件
 *  把window的初始left遍历从0 ~ wordLen - 1
 *      建立一个和WordToTimes一样的HashMap wordRemained，每次遇到一个新单词就减小他的频率
 *      初始化windows到它应该有的长度，然后检测wordRemained是否为空，空的话，加入left的index
 *      每次往右走一个单词
 *
 */
class Solution1_2 {
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        int wordNum = words.length;
        int wordLen = words[0].length();
        int subStringLen = wordLen * wordNum;
        int sLen = s.length();
        Map<String, Integer> wordCount = countWord(words);
        for (int start = 0; start < wordLen && start + subStringLen <= sLen; start++) {
            // processing, compute first subStringLen char
            Map<String, Integer> wordRemained = new HashMap<>(wordCount);
            int left = start;
            int right;
            for (right = left; right < subStringLen; right += wordLen) {
                rightPointerGoRightBy1Word(s, wordLen, wordRemained, right);
            }
            
            // moving window
            for (; right + wordLen <= sLen; right+=wordLen, left += wordLen) {
                if (wordRemained.isEmpty()) {
                    res.add(left);
                }
                // deal with right++
                rightPointerGoRightBy1Word(s, wordLen, wordRemained, right);
                
                // deal with left++
                String leftStr = s.substring(left, left + wordLen);
                wordRemained.put(leftStr, wordRemained.getOrDefault(leftStr, 0) + 1);
                if (wordRemained.get(leftStr) == 0) {
                    wordRemained.remove(leftStr);
                }
            }
            if (wordRemained.isEmpty()) {
                res.add(left);
            }
        }
        return res;
    }
    
    private Map<String, Integer> countWord(String[] words) {
        Map<String, Integer> wordToTimes = new HashMap<>();
        for (String str : words) {
            wordToTimes.put(str, wordToTimes.getOrDefault(str, 0) + 1);
        }
        return wordToTimes;
    }
    
    private void rightPointerGoRightBy1Word(String s, int wordLen,
            Map<String, Integer> wordRemained, int right) {
        String str = s.substring(right, right + wordLen);
        Integer times = wordRemained.get(str);
        if (times == null) {
            wordRemained.put(str, -1);
        } else if (times == 1) {
            wordRemained.remove(str);
        } else {
            wordRemained.put(str, wordRemained.get(str) - 1);
        }
    }
    
}

// Solution 2:Assuming length of s is n, number of words is m, T(n) = O(n), S(n) = O(m)
// 执行耗时:8 ms,击败了91.51% 的Java用户, 内存消耗:39.6 MB,击败了61.67% 的Java用户
/**
 * 分类讨论，sliding window,每次left pointer, 或者right pointer走一边，一次分别走左边或者走右边
 * 把words里面的所有的单词存到HashMap里面， 因为可能有重复，所以key是String，value是int，表示出现的次数，
 * 把目前window里面出现的单词和次数存到另外一个HashMap里面，(key, value) 是 (word, times),然后一个个走
 * 如果新进来的单词是words里面有的，
 *      如果当前word出现的次数 < words里面的
 *          更新目前word的出现次数
 *          如果匹配到了concatenation
 *              加到结果里面
 *          否则，continue
 *      否则，左边走1格
 * 否则，重新开始
 * 分别以start = 1 ~ wordLen - 1为，重复上面的操作
 */
class Solution2 {
    
    // time = O(n), space = O(k)   n: string的长度； k: words的个数
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> wordCount = countWord(words);
        int wordLen = words[0].length();
        for (int start = 0; start < wordLen; start++) {
            traverseWordFromStart(start, s, words, wordCount, res);
        }
        return res;
    }
    
    private Map<String, Integer> countWord(String[] words) {
        Map<String, Integer> wordToTimes = new HashMap<>();
        for (String str : words) {
            wordToTimes.put(str, wordToTimes.getOrDefault(str, 0) + 1);
        }
        return wordToTimes;
    }
    
    private void traverseWordFromStart(int start, String s, String[] words,
            Map<String, Integer> wordCount, List<Integer> res) {
        int len = s.length();
        int wordLen = words[0].length();
        int[] left = {start};
        int right = left[0];
        int[] windowSize = {0};
        Map<String, Integer> wordOccured = new HashMap<>();
        while (right + wordLen <= len) {
            String rightStr = s.substring(right, right + wordLen);
            if (wordCount.containsKey(rightStr)) {
                wordOccured.putIfAbsent(rightStr, 0);
                /*
                * 如果出现次数小于要求的话，加到window里面
                */
                // deal with right++
                if (wordOccured.get(rightStr) < wordCount.get(rightStr)) {
                    wordOccured.put(rightStr, wordOccured.get(rightStr) + 1);
                    windowSize[0]++;
                    right += wordLen;
                    if (windowSize[0] == words.length) {
                        res.add(left[0]);
                        // deal with left++
                        LeftPointGoRightBy1Word(left, s, wordOccured, wordLen, windowSize);
                    }
                } else {
                    // deal with left++
                    LeftPointGoRightBy1Word(left, s, wordOccured, wordLen, windowSize);
                }
            } else {
                // 遇到words里面没有的单词，reset，从right这个地方开始
                wordOccured.clear();
                right += wordLen;
                left[0] = right;
                windowSize[0] = 0;
            }
        }
    }
    
    private void LeftPointGoRightBy1Word(int[] left, String s, Map<String, Integer> wordOccured,
            int wordLen, int[] windowSize) {
        String leftStr = s.substring(left[0], left[0] + wordLen);
        wordOccured.put(leftStr, wordOccured.get(leftStr) - 1);
        left[0] += wordLen;
        windowSize[0]--;
    }
    
}
}