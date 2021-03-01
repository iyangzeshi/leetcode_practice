//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, add spaces in s to construct a sentence where each word is a valid 
//dictionary word. Return all such possible sentences. 
//
// Note: 
//
// 
// The same word in the dictionary may be reused multiple times in the segmentat
//ion. 
// You may assume the dictionary does not contain duplicate words. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//Output:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// Example 2: 
//
// 
//Input:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//Output:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//Explanation: Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output:
//[] 
// Related Topics Dynamic Programming Backtracking

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 2020-07-01 00:02:13
// Zeshi Yang
public class Leetcode0140WordBreakIi {
    // Java: word-break-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0140WordBreakIi().new Solution();
        // TO TEST
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        List<String> result = sol.wordBreak(s, wordDict);
        System.out.println(result);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: dynamic Programming
class Solution {
    // dynamic programming
    /*
    建立一个HashMap, 从后往前搜索分叉，当index i 之后可以分叉到index j的时候（j < i)，
    我们在HashMap里面存<j, i>，第一个DFS函数用来建立图，第二个private函数用来建立路径
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int length = s.length();
        boolean[] mem = new boolean[length + 1];
        mem[length] = true;
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // dynamic programming
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= length; j++) {
                String str = s.substring(i, j);
                if (set.contains(str) && mem[j]) {
                    if (!graph.containsKey(i)) {
                        mem[i] = true;
                        graph.put(i, new ArrayList<>());
                    }
                    graph.get(i).add(j);
                }
            }
        }
        
        dfsBuildPaths(0, s, new StringBuilder(), graph, result);
        
        return result;
    }
    
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
            Map<Integer, List<Integer>> graph, List<String> result) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            result.add(sb.toString());
            return;
        }
        
        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s.substring(index, i)).append(" ");
                dfsBuildPaths(i, s, sb, graph, result);
                sb.setLength(len);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS
// Solution 1_1: DFS without pruning, time limit exceed
class Solution1_1 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        
        int index = 0;
        Set<String> set = new HashSet<>(wordDict);
        StringBuilder sb = new StringBuilder();
        wordBreak(index, s, set, sb, result);
        return result;
    }
    
    private void wordBreak(int index, String s, Set<String> set, StringBuilder sb,
	    List<String> result) {
    	int len = s.length();
        // base case
        if (index == len) {
            sb.setLength(sb.length() - 1); // delete the " " at the end of the String
            result.add(sb.toString());
        }
        
        for (int i = index + 1; i <= len; i++) {
            String word = s.substring(index, i);
            if (set.contains(word)) {
                int sbLen = sb.length();
                sb.append(word).append(" ");
                wordBreak(i, s, set, sb, result);
                sb.setLength(sbLen);
            }
        }
    }
}

// Solution 1_2: DFS with pruning by memo[], 4ms
// DFS with pruning
/** 设置一个Boolean memo[]数组，初始值都是Null，
 memo[i] 表示[i, length)的String是不是可以被wordBreak
 
 用DFS思想，遇到memo[i]是false的时候，就return false; 否则继续做下去
 某个位置开始的word不能被break的时候，memo[i]就标为false
 */
class Solution1_2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        
        int length = s.length(); // 比Solution 1_1多的部分
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[length + 1];// 比Solution 1_1多的部分
        StringBuilder sb = new StringBuilder();
        wordBreak(0, s, set, sb, memo, result);
        return result;
    }
	
    /*
    return String s从index开始到末尾能不能被break，用Boolean[] memo来pruning
    并且用StringBuilder来记录从头开始的路径
     */
	private boolean wordBreak(int index, String s, Set<String> set, StringBuilder sb,
		Boolean[] memo, List<String> result) {
    	int len = s.length();
		// base case
		if (index == len) {
			sb.setLength(sb.length() - 1); // delete the " " at the end of the String
			result.add(sb.toString());
			memo[len] = true; // 比Solution 1_1多的部分
			return true; // 比Solution 1_1多的部分
		}
		if (memo[index] != null && !memo[index]) { // Null和false不能比较, 比Solution 1_1多的部分
			return false;
		}
		
		int size = result.size();// 比Solution 1_1多的部分
		
		for (int i = index + 1; i <= len; i++) {
			String word = s.substring(index, i);
			if (set.contains(word)) {
				int sbLen = sb.length();
				sb.append(word).append(" ");
				if (wordBreak(i, s, set, sb, memo, result)) { // 和Solution 1_1不同的部分
					memo[index] = true; // 注意这里索引是index，不是i
				}
				sb.setLength(sbLen); // 比Solution 1_1多的部分
			}
		}
		if (result.size() == size) {// 比Solution 1_1多的部分
			memo[index] = false;
		}
		return memo[index]; // 比Solution 1_1多的部分
	}
}

// Solution 1_3: DFS with pruning by HashMap, 3ms
/*
第1个DFS函数用来建立分叉搜索图，建立一个HashMap, 从后往前搜索分叉，
当index之后可以分叉到index i的时候（i < index)，我们在HashMap里面存<i, index>，
第2个private函数用来建立路径
 */
class Solution1_3 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        
        int length = s.length();
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[length + 1]; // 表示能不能从起点走到s[i]
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        wordBreak(length, s, set, graph, memo);
        dfsBuildPaths(0, s, new StringBuilder(), graph, result);
        return result;
    }
    
    /*
    post order dfs
    return String s从index开始到开头能不能被break，用Boolean memo[]来pruning
    如果可以的话，还有在graph里面存下这个分支 i,表示从index往前可以搜索到i，而且这条路可以走到开头
    graph.get(i).add(index), i < index, 建立graph图形
     */
    private boolean wordBreak(int index, String s, Set<String> set,
	    Map<Integer, Set<Integer>> graph, Boolean[] memo) {
        // base case
        if (index == 0) {
            return true;
        }
        if (memo[index] != null && !memo[index]) { // Null和false不能比较
            return false;
        }
        
        for (int i = index; i >= 0; i--) {
            if (memo[index] != null && !memo[index]) {
                break;
            }
            String word = s.substring(i, index);
            if (set.contains(word)) {
                if (wordBreak(i, s, set, graph, memo)) {
                    memo[index] = true;
                }
	            graph.computeIfAbsent(i, k-> new HashSet<>()).add(index);
            }
        }
        if (memo[index] == null) {
            memo[index] = false;
        }
        return memo[index];
    }

    /*
    从前往后dfs build paths
     */
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
	    Map<Integer, Set<Integer>> graph, List<String> result) {
        int sbLen = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(sbLen - 1);
            result.add(sb.toString());
            return;
        }

        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s, index, i).append(" ");
                dfsBuildPaths(i, s, sb, graph, result);
                sb.setLength(sbLen);
            }
        }
    }
}

// Solution 2: dynamic Programming, 8ms
/*
第1个DP用来建立分叉搜索图，建立一个HashMap, 从后往前搜索分叉，
当index之后可以分叉到index i的时候（i < index)，我们在HashMap里面存<i, index>，
private函数用来建立路径
 */
class Solution2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int length = s.length();
        boolean[] memo = new boolean[length + 1];
        memo[length] = true;
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // dynamic programming
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= length; j++) {
                String str = s.substring(i, j);
                if (set.contains(str) && memo[j]) {
                    if (!graph.containsKey(i)) {
                        memo[i] = true;
                        graph.put(i, new ArrayList<>());
                    }
                    graph.get(i).add(j);
                }
            }
        }
        dfsBuildPaths(0, s, new StringBuilder(), graph, result);
        return result;
    }
    
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
            Map<Integer, List<Integer>> graph, List<String> result) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            result.add(sb.toString());
            return;
        }
        
        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s, index, i).append(" ");
                dfsBuildPaths(i, s, sb, graph, result);
                sb.setLength(len);
            }
        }
    }
}
}