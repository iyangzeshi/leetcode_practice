//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, determine if s can be segmented into a space-separated sequence of 
//one or more dictionary words. 
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
//Input: s = "leetcode", wordDict = ["leet", "code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
// 
//
// Example 2: 
//
// 
//Input: s = "applepenapple", wordDict = ["apple", "pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pe
//n apple".
//             Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output: false
// 
// Related Topics Dynamic Programming

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Leetcode0139WordBreak {

	// Java: word-break
	public static void main(String[] args) {

		Solution sol = new Leetcode0139WordBreak().new Solution();
		// TO TEST
		String s = "catsandog";
		List<String> dict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));

		System.out.println(sol.wordBreak(s, dict));
	}

	//leetcode submit region begin(Prohibit modification and deletion)
// Solution4_2: BFS with pruning
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.offer(0);
        /*
         时间复杂度降低到O(n^2)的关键
         visited[i] == true，表示substring(i, length)被检测过但是不能wordBreak
         如果已经被就检查过，就不需要再被检查了，后面更新的结果应该已经在queue里面了
         */
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (visited[start]) {
                continue;
            }
            for (int i = start + 1; i <= s.length(); i++) {
                if (dict.contains(s.substring(start, i))) {
                    queue.add(i);
                    if (i == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

// Solution1_1: DFS，第1类搜索树, Time Limit Exceeded
class Solution1_1 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return dfs(s, 0, 0, dict);
    }

    private boolean dfs(String s, int start, int end, Set<String> dict) {
        // base case, success case
        if (end == s.length()) {
            return start == end;
        }
        // general case
        boolean res1 = false;
        if (dict.contains(s.substring(start, end + 1))) {
            res1 = dfs(s, end + 1, end + 1, dict);
        }
        boolean res2 = dfs(s, start, end + 1, dict);
        return res1 || res2;

    }
}

// Solution1_2: DFS，第1类搜索树，with pruning
class Solution1_2 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return dfs(s, 0, 0, dict, memo);
    }

    private boolean dfs(String s, int start, int end, Set<String> dict, Boolean[] memo) {
        // base case, success case
        if (end == s.length()) {
            return start == end;
        }
        if (memo[start] != null) { // 查表
            return memo[start];
        }
        // general case
        boolean res1 = false;
        if (dict.contains(s.substring(start, end + 1))) {
            res1 = dfs(s, end + 1, end + 1, dict, memo);
        }
        boolean res2 = dfs(s, start, end + 1, dict, memo);
        memo[start] = res1 || res2; // 填表
        return memo[start];
    }
}

// Solution2_1: DFS, 第2类搜索树，Time Limit Exceeded
class Solution2_1 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, 0, dict);
    }

    private boolean dfs(String s, int idx, Set<String> dict) {
        // base case, success case
        if (idx == s.length()) {
            return true;
        }

        // general case
        for (int i = idx + 1; i <= s.length(); i++) {
            String str = s.substring(idx, i); // [idx, i)
            if (dict.contains(str)) {
                if (dfs(s, i, dict)) {
                    return true;
                }
            }
        }
        return false;
    }
}

// Solution2_2: DFS 第2类搜索树，with pruning
class Solution2_2 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, 0, dict, new Boolean[s.length()]);
    }

    private boolean dfs(String s, int idx, Set<String> dict, Boolean[] memo) {
        // base case, success case
        if (idx == s.length()) {
            return true;
        }
        if (memo[idx] != null) { // 查表
            return memo[idx];
        }
        // general case
        for (int i = idx + 1; i <= s.length(); i++) {
            String str = s.substring(idx, i); // [idx, i)
            if (dict.contains(str)) {
                if (dfs(s, i, dict, memo)) {
                    memo[idx] = true; // 填表
                    return true;
                }
            }
        }
        memo[idx] = false; // 填表
        return false;
    }
}

// Solution3_1: BFS, Time Limit Exceeded
class Solution3_1 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int start = queue.poll();
            for (int i = start + 1; i <= s.length(); i++) {
                if (dict.contains(s.substring(start, i))) {
                    queue.add(i);
                    if (i == s.length()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

// Solution3_2: BFS with pruning
class Solution3_2 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.offer(0);
        /*
         时间复杂度降低到O(n^2)的关键
         visited[i] == true，表示substring(i, length)被检测过但是不能wordBreak
         如果已经被就检查过，就不需要再被检查了，后面更新的结果应该已经在queue里面了
         */
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (visited[start]) {
                continue;
            }
            for (int i = start + 1; i <= s.length(); i++) {
                if (dict.contains(s.substring(start, i))) {
                    queue.add(i);
                    if (i == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }

}

// Solution4: dynamic programming
class Solution4 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;
        // dp[i] 表示[i, len)的字符能不能被dict完美切分

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j <= len; j++) {
                if (dict.contains(s.substring(i, j)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

}


}