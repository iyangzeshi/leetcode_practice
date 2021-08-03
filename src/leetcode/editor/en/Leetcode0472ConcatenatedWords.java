//Given a list of words (without duplicates), please write a program that return
//s all concatenated words in the given list of words.
// A concatenated word is defined as a string that is comprised entirely of at l
//east two shorter words in the given array. 
//
// Example: 
// 
//Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","
//ratcatdogcat"]
//
//Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
//
//Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";  "
//dogcatsdog" can be concatenated by "dog", "cats" and "dog"; "ratcatdogcat" can b
//e concatenated by "rat", "cat", "dog" and "cat".
// 
// 
//
// Note: 
// 
// The number of elements of the given array will not exceed 10,000 
// The length sum of elements in the given array will not exceed 600,000. 
// All the input string will only include lower case letters. 
// The returned elements order does not matter. 
// 
// Related Topics Dynamic Programming Depth-first Search Trie 
// 👍 824 👎 116

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// 2020-09-06 17:33:52
// Zeshi Yang
public class Leetcode0472ConcatenatedWords{
    // Java: concatenated-words
    public static void main(String[] args) {
        Solution sol = new Leetcode0472ConcatenatedWords().new Solution();
        // TO TEST
        String[] words = {"rfkqyuqfjkx",""};
        List<String> res = sol.findAllConcatenatedWordsInADict(words);
        System.out.println(res.size());
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 1) {
            return res;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() > 0) {
                min = Math.min(min, word.length());
            }
        }
        // min = 1;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.remove(word);
            if (check(0, word, set, new Boolean[word.length() + 1])) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean check(int start, String word, Set<String> set, Boolean[] memo) {
        if (memo[start] != null) {
            return memo[start];
        }
        for (int i = start + min; i <= word.length() - min; i++) {
            if (set.contains(word.substring(start, i)) &&
                    (set.contains(word.substring(i)) || check(i, word, set, memo))) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/**面试的时候，用Solution 1_2 **/


// Solution 1_1: DFS + pruning, T(n, m) = O(nm)。n为数组长度,m为数组中字符串平均长度
// 656 ms,击败了15.25% 的Java用户, 145.4 MB,击败了5.04% 的Java用户
/*
先算出单词的最小长度min，dfs的时候，带着这个参数，会快很多，每次至少检测长度为min的单词
 */
class Solution1_1 {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 1) {
            return res;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() > 0) {
                min = Math.min(min, word.length());
            }
        }
        // min = 1;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.remove(word);
            if (check(0, word, set, new Boolean[word.length() + 1])) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean check(int start, String word, Set<String> set, Boolean[] memo) {
        if (memo[start] != null) {
            return memo[start];
        }
        for (int i = start + min; i <= word.length() - min; i++) {
            if (set.contains(word.substring(start, i)) &&
                    (set.contains(word.substring(i)) || check(i, word, set, memo))) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }
}

// Solution 1_2: DFS + pruning, T(n, m) = O(nm)。n为数组长度,m为数组中字符串平均长度
// 381 ms,击败了34.72% 的Java用户, 45.2 MB,击败了89.70% 的Java用户
class Solution1_2 {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 1) {
            return res;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() > 0) {
                min = Math.min(min, word.length());
            }
        }
        // min = 1;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.remove(word);
            if (check(0, word, set, new Boolean[word.length() + 1])) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean check(int start, String word, Set<String> set, Boolean[] memo) {
        if (memo[start] != null) {
            return memo[start];
        }
        // base case, success case, difference between this Solution and Solution1_1
        if (start == word.length() || set.contains(word.substring(start))) {
            memo[start] = true;
            return true;
        }
        
        for (int i = Math.max(start + min, 1); i <= word.length() - Math.max(min, 1); i++) {
            String str = word.substring(start, i); // [idx, i)
            if (set.contains(str)) {
                if (check(i, word, set, memo)) { // difference between this Solution and Solution1_1
                    memo[start] = true;
                    return true;
                }
            }
        }
        memo[start] = false;
        return false;
    }
}

// Solution 2: DP, T(n, m) = O(nm^2)。n为数组长度,m为数组中字符串最大长度
// 644 ms,击败了17.38% 的Java用户,47.5 MB,击败了36.22% 的Java用户
class Solution2 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        // corner case
        if (words == null || words.length <= 2) {
            return res;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word: words) {
            set.remove(word);
            if (wordBreak(word, set)) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }

    private boolean wordBreak(String word, Set<String> set) {
        // corner case
        if (word == null || word.length() == 0) {
            return false;
        }

        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

// Solution 3: Trie + DFS, 142ms, T(n, m) = O(nm)。n为数组长度,m为数组中字符串平均长度
// 740 ms,击败了13.80% 的Java用户, 48.5 MB,击败了29.60% 的Java用户
class Solution3 {
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        // corner case
        if (words == null || words.length <= 1) {
            return res;
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        for (String word : words) {
            if (dfs(trie, word.toCharArray(), 0, false, new Boolean[word.length() + 1])) {
                res.add(word);
            }
        }
        return res;
    }
    
    // search from word[start, end] including
    private boolean dfs(Trie trie, char[] word, int start, boolean isCut, Boolean[] dp) {
        int len = word.length;
        if (dp[start] != null) {
            return dp[start];
        }
        // base case
        if (start == len) {
            dp[start] = isCut; // 不能return true;因为如果这个字符是""（空字符）的时候会有问题
            return isCut;
        }
        
        for (int i = start; i < word.length; i++) {
            if (i == len - 1 && !isCut) {
                dp[start] = false;
                return false;
            }
            if (trie.search(word, start, i)) {
                // cut = true;
                if (dfs(trie, word, i + 1, true, dp)) {
                    dp[start] = true;
                    return true;
                }
            }
        }
        dp[start] = false;
        return false;
    }
    
    class Trie {
        
        class TrieNode {
            
            boolean isWord = false;
            TrieNode[] children;
            
            TrieNode() {
                children = new TrieNode[26];
            }
        }
        
        TrieNode root;
        
        Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.isWord = true;
        }
        
        public boolean search(char[] word, int start, int end) {
            TrieNode node = root;
            for (int i = start; i <= end; i++) {
                char ch = word[i];
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.isWord;
            
        }
    }
}

}