//Given two words word1 and word2, find the minimum number of operations require
//d to convert word1 to word2. 
//
// You have the following 3 operations permitted on a word: 
//
// 
// Insert a character 
// Delete a character 
// Replace a character 
// 
//
// Example 1: 
//
// 
//Input: word1 = "horse", word2 = "ros"
//Output: 3
//Explanation: 
//horse -> rorse (replace 'h' with 'r')
//rorse -> rose (remove 'r')
//rose -> ros (remove 'e')
// 
//
// Example 2: 
//
// 
//Input: word1 = "intention", word2 = "execution"
//Output: 5
//Explanation: 
//intention -> inention (remove 't')
//inention -> enention (replace 'i' with 'e')
//enention -> exention (replace 'n' with 'x')
//exention -> exection (replace 'n' with 'c')
//exection -> execution (insert 'u')
// 
// Related Topics String Dynamic Programming

package leetcode.editor.en;

public class Leetcode0072EditDistance {
    // Java: edit-distance
    public static void main(String[] args) {
        Solution sol = new Leetcode0072EditDistance().new Solution();
        // TO TEST
        String word1 = "horse";
        String word2 = "ros";
        int res = sol.minDistance(word1, word2);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP
    public int minDistance(String word1, String word2) {
        //corner case
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null || word2 == null) {
            return word1 == null ? word2.length() : word1.length();
        }
        
        //general case
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        //initialize the 2 edges
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        
        
        for(int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j],
                            dp[i][j - 1]));
                }
            }
        }
        return dp[len1][len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*下面所有的Solution的思路都是这个
思路：
设置dp[i][j]为word1[0 ~ i] 和 word2[0 ~ j]匹配的最小cost
如果word1[i] == word2[j]， 那dp[i][j] = dp[i - 1][j - 1]
否则dp[i][j] = min{dp[i- 1][j - 1], d[i - 1][j], dp[i][j - 1]} + 1;
 */
// Solution 1_1: DFS, T(m,n) = O(2^m * 2^n), S(m,n) = O(m * n)
class Solution1_1 {
    // DFS
    public int minDistance(String word1, String word2) {
        //corner case
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null || word2 == null) {
            return word1 == null ? word2.length() : word1.length();
        }
        
        //general case
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        return dfs(len1 - 1, len2 - 1, chars1, chars2);
    }
    
    private int dfs(int idx1, int idx2, char[] chars1, char[] chars2) {
        // base case
        if (idx1 == -1 && idx2 == -1) {
            return 0;
        }
        if (idx1 == -1) {
            return idx2 + 1;
        }
        if (idx2 == -1) {
            return idx1 + 1;
        }
        
        // general case
        int res;
        if (chars1[idx1] == chars2[idx2]) {
            res =  dfs(idx1 - 1, idx2 - 1, chars1, chars2);
        } else {
            int res1 = dfs(idx1 - 1, idx2 - 1, chars1, chars2);
            int res2 = dfs(idx1 - 1, idx2, chars1, chars2);
            int res3 = dfs(idx1, idx2 - 1,chars1, chars2);
            res = 1 + Math.min(res1, Math.min(res2, res3));
        }
        return res;
    }
}

// Solution 1_2: DFS with pruning, T(m, n) = O(m * n), S(m, n) = O(m * n)
// 4 ms,击败了91.82% 的Java用户, 39.1 MB,击败了65.14% 的Java用户
class Solution1_2 {
    // DFS with pruning
    public int minDistance(String word1, String word2) {
        //corner case
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null || word2 == null) {
            return word1 == null ? word2.length() : word1.length();
        }
        
        //general case
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        Integer[][] mem = new Integer[len1][len2];
        return dfs(len1 - 1, len2 - 1, chars1, chars2, mem);
    }
    
    private int dfs(int idx1, int idx2, char[] chars1, char[] chars2, Integer[][] mem) {
        // base case
        if (idx1 == -1 && idx2 == -1) {
            return 0;
        }
        if (idx1 == -1) {
            return idx2 + 1;
        }
        if (idx2 == -1) {
            return idx1 + 1;
        }
        
        //lookup the form
        if (mem[idx1][idx2] != null) {
            return mem[idx1][idx2];
        }
        
        // general case
        int res;
        if (chars1[idx1] == chars2[idx2]) {
            res =  dfs(idx1 - 1, idx2 - 1, chars1, chars2, mem);
        } else {
            int res1 = dfs(idx1 - 1, idx2 - 1, chars1, chars2, mem);
            int res2 = dfs(idx1 - 1, idx2, chars1, chars2, mem);
            int res3 = dfs(idx1, idx2 - 1,chars1, chars2, mem);
            res = 1 + Math.min(res1, Math.min(res2, res3));
        }
        mem[idx1][idx2] = res;
        return res;
    }
}

// Solution2: DP, T(m, n) = O(m * n), S(m,n) = O(m * n)
// 11 ms,击败了13.21% 的Java用户, 41.4 MB,击败了15.39% 的Java用户
class Solution2 {
    // DP
    public int minDistance(String word1, String word2) {
        //corner case
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null || word2 == null) {
            return word1 == null ? word2.length() : word1.length();
        }
        
        //general case
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        //initialize the 2 edges
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        
        
        for(int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
						dp[i - 1][j - 1],
	                    Math.min(
							dp[i - 1][j],
		                    dp[i][j - 1]
	                    )
                    );
                }
            }
        }
        return dp[len1][len2];
    }
}
}