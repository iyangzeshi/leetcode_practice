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
// Jesse Yang
public class Leetcode0140WordBreakIi {
    // Java: word-break-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0140WordBreakIi().new Solution();
        // TO TEST
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        List<String> res = sol.wordBreak(s, wordDict);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return res;
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
        dfsBuildPaths(0, s, new StringBuilder(), graph, res);
        return res;
    }
    
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
            Map<Integer, List<Integer>> graph, List<String> res) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            res.add(sb.toString());
            return;
        }
        
        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s, index, i).append(" ");
                dfsBuildPaths(i, s, sb, graph, res);
                sb.setLength(len);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 1_2 */
/*
因为题目要求返回所有的结果，
所以pruning虽然可以减少很多重复操作，但是并不会影响时间复杂度
所以面试的时候，写Solution 1_1就可以了，
如果要优化的话，就写Solution 2_1，记得要解释，实际上并不会影响时间复杂度

下面的写法是DFS的第2类搜索树，
其实还有其他的写法，
BFS的第1和2类搜索树可以
DFS的第1类搜索树也可以，同理，就不写了
 */
 
 
// Solution 1: DFS 第2类搜索树
// Solution 1_1: DFS 第2类搜索树without pruning
// T(n, k) = O(n^3 + 2^n + k), S(n,k) = O(2^n * n + k)
// n is length of the String s, k is length of word dictionary
// 0 ms,击败了100.00% 的Java用户, 37.3 MB,击败了89.52% 的Java用户
class Solution1_1 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return res;
        }
        
        int index = 0;
        Set<String> set = new HashSet<>(wordDict);
        StringBuilder sb = new StringBuilder();
        wordBreak(index, s, set, sb, res);
        return res;
    }
    
    private void wordBreak(int index, String s, Set<String> set, StringBuilder sb,
	    List<String> res) {
    	int len = s.length();
        // base case
        if (index == len) {
            sb.setLength(sb.length() - 1); // delete the " " at the end of the String
            res.add(sb.toString());
        }
        
        for (int i = index + 1; i <= len; i++) {
            String word = s.substring(index, i);
            if (set.contains(word)) {
                int sbLen = sb.length();
                sb.append(word).append(" ");
                wordBreak(i, s, set, sb, res);
                sb.setLength(sbLen);
            }
        }
    }
}

// Solution 1_2: DFS with pruning by Boolean[] memo
// T(n, k) = O(n^3 + 2^n + k), S(n,k) = O(2^n * n + k)
// n is length of the String s, k is length of word dictionary
// 0 ms,击败了100.00% 的Java用户, 36.7 MB,击败了99.98% 的Java用户
/** 设置一个Boolean memo[]数组，初始值都是Null，
 memo[i] 表示[i, length)的String是不是可以被wordBreak
 
 用DFS思想，遇到memo[i]是false的时候，就return false; 否则继续做下去
 某个位置开始的word不能被break的时候，memo[i]就标为false
 */
class Solution1_2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return res;
        }
        
        int len = s.length(); // 比Solution 1_1多的部分
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[len + 1];// 比Solution 1_1多的部分
        StringBuilder sb = new StringBuilder();
        wordBreak(0, s, set, sb, memo, res);
        return res;
    }
	
    /*
    return String s从index开始到末尾能不能被break，用Boolean[] memo来pruning
    并且用StringBuilder来记录从头开始的路径
     */
	private boolean wordBreak(int index, String s, Set<String> set, StringBuilder sb,
		Boolean[] memo, List<String> res) {
    	int len = s.length();
		// base case
		if (index == len) {
			sb.setLength(sb.length() - 1); // delete the " " at the end of the String
			res.add(sb.toString());
			memo[len] = true; // 比Solution 1_1多的部分
			return true; // 比Solution 1_1多的部分
		}
		if (memo[index] != null && !memo[index]) { // Null和false不能比较, 比Solution 1_1多的部分
			return false;
		}
		
		for (int i = index + 1; i <= len; i++) {
			String word = s.substring(index, i);
			if (set.contains(word)) {
				int sbLen = sb.length();
				sb.append(word).append(" ");
				if (wordBreak(i, s, set, sb, memo, res)) { // 和Solution 1_1不同的部分
					memo[index] = true; // 注意这里索引是index，不是i
				}
				sb.setLength(sbLen); // 比Solution 1_1多的部分
			}
		}
		if (memo[index] == null) {// 比Solution 1_1多的部分
			memo[index] = false;
		}
		return memo[index]; // 比Solution 1_1多的部分
	}
}

// Solution 1_3: DFS with pruning by Boolean[] memo + HashMap存路径
// T(n, k) = O(n^3 + 2^n + k), S(n,k) = O(2^n * n + k)
// n is length of the String s, k is length of word dictionary
// 2 ms,击败了81.29% 的Java用户,37.5 MB,击败了65.78% 的Java用户
/*
第1个DFS函数用来建立分叉搜索图，建立一个HashMap, 从后往前搜索分叉，
当index之后可以分叉到index i的时候（i < index)，我们在HashMap里面存<i, index>，
第2个private函数用来建立路径
 */
class Solution1_3 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return res;
        }
        
        int len = s.length();
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[len + 1]; // 表示能不能从起点走到s[i]
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        wordBreak(len, s, set, graph, memo);
        dfsBuildPaths(0, s, new StringBuilder(), graph, res);
        return res;
    }
    
    /*
    post order dfs, 从后往前搜索
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
	                graph.computeIfAbsent(i, k-> new HashSet<>()).add(index);
                }
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
	    Map<Integer, Set<Integer>> graph, List<String> res) {
        int sbLen = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(sbLen - 1);
            res.add(sb.toString());
            return;
        }

        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s, index, i).append(" ");
                dfsBuildPaths(i, s, sb, graph, res);
                sb.setLength(sbLen);
            }
        }
    }
}

// Solution 2: dynamic Programming
// T(n, k) = O(n^3 + 2^n + k), S(n,k) = O(2^n * n + k)
// n is length of the String s, k is length of word dictionary
// 1 ms,击败了95.84% 的Java用户, 37.4 MB,击败了82.21% 的Java用户
/*
第1个DP用来建立分叉搜索图，建立一个HashMap, 从后往前搜索分叉，
当index之后可以分叉到index i的时候（i < index)，我们在HashMap里面存<i, index>，
private函数用来建立路径
 */
class Solution2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return res;
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
        dfsBuildPaths(0, s, new StringBuilder(), graph, res);
        return res;
    }
    
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
            Map<Integer, List<Integer>> graph, List<String> res) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            res.add(sb.toString());
            return;
        }
        
        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s, index, i).append(" ");
                dfsBuildPaths(i, s, sb, graph, res);
                sb.setLength(len);
            }
        }
    }
}
}