//Given a string s, partition s such that every substring of the partition is a 
//palindrome. 
//
// Return the minimum cuts needed for a palindrome partitioning of s. 
//
// Example: 
//
// 
//Input: "aab"
//Output: 1
//Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 
//cut.
// 
// Related Topics Dynamic Programming

package leetcode.editor.en;

/**
 * @ClassName: Leetcode132PalindromePartitioningIi
 * @Description:
 * @Author: Jesse Yang
 * @Date: 2020/07/02 周四 13:32
 */
public class Leetcode0132PalindromePartitioningIi {
	
	// Java: palindrome-partitioning-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0132PalindromePartitioningIi().new Solution();
		// TO TEST
		String str = "efe";
		int res = sol.minCut(str);
		System.out.println(res);
	}
	
	//leetcode submit region begin(Prohibit modification and deletion)
// DP with DP to judge whether s[i-j] is palindrome and combine the 2 DP together
class Solution {
    
    public int minCut(String s) {
        int len = s.length();
        boolean[][] isPal = new boolean[len][len]; // isPal[i][j]表示s[i-j]是否是Pal
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= len; i++) { // i is dp_idx
            dp[i] = i;
            isPal[i - 1][i - 1] = true;
            for (int j = 0; j < i; j++) { // j is str_idx
                isPal[j][i - 1] = (j == i - 1 ||
                        (s.charAt(j) == s.charAt(i - 1) && (i == j + 2 || isPal[j + 1][i
                                - 2])));
                if (isPal[j][i - 1]) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[len] - 1;
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: DFS, Time Limit Exceeded
class Solution1_1 {
    
    public int minCut(String s) {
        int len = s.length();
        int res = dfs(len, s);
        return res - 1;
    }
    
    private int dfs(int idx, String s) {
        int res = idx;
        for (int i = 0; i < idx; i++) {
            String str = s.substring(i, idx);
            if (isPalindrome(str)) {
                res = Math.min(res, 1 + dfs(i, s));
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
}

// Solution 1_2: DFS with pruning
class Solution1_2 {
    
    // DFS with pruning
    public int minCut(String s) {
        int len = s.length();
        // mem[i] means how many least palindromes between s[0, i)
        Integer[] mem = new Integer[len + 1];
        boolean[][] isPal = new boolean[len][len]; // means whether s[i, j] is a palindrome
        
        int res = dfs(len, s, mem, isPal);
        return res - 1;
    }
    
    private int dfs(int idx, String s, Integer[] mem, boolean[][] isPal) {
        // lookup the form
        if (mem[idx] != null) {
            return mem[idx];
        }
        int res = idx;
        for (int i = idx - 1; i >= 0; i--) {
            if (i == idx - 1) {
                isPal[i][idx - 1] = true;
            } else if (i + 1 == idx - 1) {
                isPal[i][idx - 1] = (s.charAt(i) == s.charAt(idx - 1));
            } else {
                isPal[i][idx - 1] = (s.charAt(i) == s.charAt(idx - 1) && isPal[i + 1][idx - 2]);
            }
            if (isPal[i][idx - 1]) {
                res = Math.min(res, 1 + dfs(i, s, mem, isPal));
            }
        }
        // fill in the form
        mem[idx] = res;
        return res;
    }
    
}

// Solution 2_1: DP with a new method to judge whether s[i-j] is palindrome
class Solution2_1 {
    
    // DP with a new method to judge whether s[i-j] is palindrome
    public int minCut(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= len; i++) { // i is dp_idx
            dp[i] = i;
            for (int j = 0; j < i; j++) { // j is str_idx
                String str = s.substring(j, i); // [j, i - 1]
                if (isPalindrome(str)) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[len] - 1;
    }
    
    private boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

// Solution 2_2: DP with DP to to judge whether s[i-j] is palindrome
class Solution2_2 {
    
    public int minCut(String s) {
        int len = s.length();
        boolean[][] isPal = new boolean[len][len]; // isPal[i][j]表示s[i-j]是否是Pal
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (j == i) {
                    isPal[i][j] = true;
                } else if (i + 1 == j) {
                    isPal[i][j] = (s.charAt(j) == s.charAt(i));
                } else {
                    isPal[i][j] = (s.charAt(j) == s.charAt(i) && isPal[i + 1][j - 1]);
                }
            }
        }
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= len; i++) { // i is dp_idx
            dp[i] = i;
            for (int j = 0; j < i; j++) { // j is str_idx
                isPal[j][i - 1] = (j == i - 1
                        || (s.charAt(j) == s.charAt(i - 1) && (i == j + 2 || isPal[j + 1][i - 2]))
                );
                if (isPal[j][i - 1]) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[len] - 1;
    }
    
}

// DP with DP to judge whether s[i-j] is palindrome and combine the 2 DP together
class Solution2_3 {
    
    public int minCut(String s) {
        int len = s.length();
        boolean[][] isPal = new boolean[len][len]; // isPal[i][j]表示s[i-j]是否是Pal
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= len; i++) { // i is dp_idx
            dp[i] = i;
            isPal[i - 1][i - 1] = true;
            for (int j = 0; j < i; j++) { // j is str_idx
                isPal[j][i - 1] = (j == i - 1 ||
                        (s.charAt(j) == s.charAt(i - 1) && (i == j + 2 || isPal[j + 1][i
                                - 2])));
                if (isPal[j][i - 1]) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[len] - 1;
    }
    
}

}