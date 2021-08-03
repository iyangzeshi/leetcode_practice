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
// Solution2_2: DFS 第2类搜索树，with pruning
// T(n) = O(n^3), S(n) = O(n)
/*
每个点往后面搜索，最多有n个分支，最长的分支的深度是n，所以tree的size是n^2
然后每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
所以T(n) = O(n^2 * n) = O(n^3)
 */
class Solution {
    
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
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2_2 */
/*
Solution 1是DFS第1类搜索树
Solution 2是DFS第2类搜索树
Solution 3是BFS第1类搜索树
Solution 4是BFS第2类搜索树
Solution 5是DP
 */

// Solution1_1: DFS，第1类搜索树, Time Limit Exceeded
// T(n, k) = O(n * 2^n + k), S(n, k) = O(n + k)
// n is length of the String s, k is length of word dictionary
/*
每个点有2种可能，切或者不切
有n个地方可以选择且或者不切，所以是O(2^n)
每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(n * 2^n + k)
 */
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
// T(n) = O(n^3 + k), S(n) = O(n + k)
// n is length of the String s, k is length of word dictionary
// 5 ms,击败了75.27% 的Java用户, 39.4 MB,击败了28.27% 的Java用户
/*
每个点有2种可能，切或者不切
有n个地方可以选择且或者不切，但是memo被pruning了，所以最长branch是n，
相当于每个string[i, j]都检测了一下能不能被word break, 所以dfs的size是n^2
每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(n^2 * n + k) = O(n^3 + k)
 */
class Solution1_2 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return dfs(s, 0, 0, dict, memo);
    }
    
    private boolean dfs(String s, int start, int cur, Set<String> dict, Boolean[] memo) {
        // base case, success case
        if (cur == s.length()) {
            return start == cur;
        }
        if (memo[start] != null) { // 查表
            return memo[start];
        }
        // general case
        boolean res1 = false;
        if (dict.contains(s.substring(start, cur + 1))) { // 从start 到当前这个点不切
            res1 = dfs(s, cur + 1, cur + 1, dict, memo);
            if (res1) { // 这条分支走得通，就走这条，不用走其他分支了
                memo[start] = true;
                return true;
            }
        }
        boolean res2 = dfs(s, start, cur + 1, dict, memo); // 从start 到当前这个点要切
        memo[start] = res2; // 填表
        return memo[start];
    }
}

// Solution2_1: DFS, 第2类搜索树，Time Limit Exceeded
// T(n, k) = O(n * 2^n + k), S(n, k) = O(n + k)
// n is length of the String s, k is length of word dictionary
/*
每个点往后面搜索，从n个地方split，所有可以插入1个挡板，2个挡板，3个挡板......n个挡板
所以时间复杂度是C_n_1 + C_n_2 + C_n_3 + C_n_4 + ... C_n_(n-1) + C_n_n = 2^n
然后每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(2^n * n + k) = O(n * 2^n + k)
 */
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
// T(n) = O(n^3 + k), S(n) = O(n + k)
// n is length of the String s, k is length of word dictionary
// 5 ms,击败了75.27% 的Java用户, 39.5 MB,击败了28.27% 的Java用户
/*
每个点往后面搜索，最多有n个分支，最长的分支的深度是n，所以tree的size是n^2
然后每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(n^2 * n + k) = O(n^3 + k)
 */
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

// Solution 3_1: BFS 第1类搜索树, Time Limit Exceeded
// T(n, k) = O(n * 2^n + k), S(n, k) = O(n + k)
// n is length of the String s, k is length of word dictionary
/*
每个点有2种可能，切或者不切
有n个地方可以选择且或者不切，所以是O(2^n)
每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(n * 2^n + k)
 */
class Solution3_1 {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int index = 0;
        while (index <= len) {
            int size = queue.size();
            while (size-- > 0) {
                int cut = queue.poll();
                String str = s.substring(cut, index);
                if (dict.contains(str)) { // case1: cut in this place, ∵ str is contained in dict
                    if (index == len) {
                        return true;
                    }
                    queue.offer(index);
                }
                queue.offer(cut); // case2: do not cut in this place
            }
            index++;
        }
        return false;
    }
    
}

// Solution 3_2: BFS 第1类搜索树 with pruning
// T(n) = O(n^3 + k), S(n) = O(n + k)
// n is length of the String s, k is length of word dictionary
// 12 ms,击败了7.10% 的Java用户, 38.9 MB,击败了78.51% 的Java用户
/*
每个点有2种可能，切或者不切
有n个地方可以选择且或者不切，但是memo被pruning了，所以最长branch是n，
相当于每个string[i, j]都检测了一下能不能被word break, 所以dfs的size是n^2
每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(n^2 * n + k) = O(n^3 + k)
 */
class Solution3_2 {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        
        Set<Integer> thisLevel = new HashSet<>(); // 用HashSet 去pruning
        thisLevel.add(0);
        int index = 0;
        while (index <= len) {
            Set<Integer> nextLevel = new HashSet<>();
            for (int prev : thisLevel) {
                String str = s.substring(prev, index);
                if (dict.contains(str)) {
                    if (index == len) {
                        return true;
                    }
                    nextLevel.add(index);
                }
                nextLevel.add(prev);
            }
            thisLevel = nextLevel;
            index++;
        }
        return false;
    }
    
}

// Solution 4_1: BFS第2类搜索树, Time Limit Exceeded
// T(n, k) = O(n * 2^n + k), S(n, k) = O(n + k)
// n is length of the String s, k is length of word dictionary
/*
BFS第2类搜索树
每个点往后面搜索，从n个地方split，所有可以插入1个挡板，2个挡板，3个挡板......n个挡板
所以时间复杂度是C_n_1 + C_n_2 + C_n_3 + C_n_4 + ... C_n_(n-1) + C_n_n = 2^n
然后每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(2^n * n + k) = O(n * 2^n + k)
 */
class Solution4_1 {

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

// Solution4_2: BFS第2类搜索树 with pruning
// T(n) = O(n^3 + k), S(n) = O(n + k)
// n is length of the String s, k is length of word dictionary
// 7 ms,击败了37.12% 的Java用户, 39.1 MB,击败了61.92% 的Java用户
/*
BFS第2类搜索树
每个点往后面搜索，从n个地方split，所有可以插入1个挡板，2个挡板，3个挡板......n个挡板
但是pruning之后
每个点往后面搜索，最多有n个分支，最长的分支的深度是n，所以tree的size是n^2
然后每个地方切的时候，本身的复杂度substring()的复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以T(n) = O(n^2 * n + k) = O(n^3 + k)
 */
class Solution4_2 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.offer(0);
        /*
         时间复杂度降低到O(n^3)的关键
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

// Solution5: dynamic programming
// T(n) = O(n^3 + k), S(n) = O(n + k)
// n is length of the String s, k is length of word dictionary
// 7 ms,击败了37.12% 的Java用户, 39.1 MB,击败了71.87% 的Java用户
/*
2层for循环，所以计算了n^2个点, O(n^2)
每个点都用到了substring, substring的时间复杂度是O(n)
把k个word放到HashSet里面，时间复杂度是O(k)
所以时间复杂度是两者相乘，所以T(n) = O(n^2 * n + k) = O(n^3 + k)
 */
class Solution5 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true; // dp[i] 表示[i, len)的字符能不能被dict完美切分

        for (int i = len - 1; i >= 0; i--) { // O(n)
            for (int j = i; j <= len; j++) { // O(n)
                if (dict.contains(s.substring(i, j)) && dp[j]) { // substring O(n)
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

}


}