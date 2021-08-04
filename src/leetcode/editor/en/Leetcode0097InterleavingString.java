//Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2. 
//
//
// Example 1: 
//
// 
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//Output: false
// 
// Related Topics String Dynamic Programming 
// 👍 1376 👎 84

package leetcode.editor.en;

// 2020-07-03 20:59:10
public class Leetcode0097InterleavingString {

	// Java: interleaving-string
	public static void main(String[] args) {

		Solution sol = new Leetcode0097InterleavingString().new Solution();

		// TO TEST
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		boolean res = sol.isInterleave(s1, s2, s3);
		System.out.println(res);

	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	public boolean isInterleave(String s1, String s2, String s3) {
		// cornner case
		if (s1 == null && s2 == null && s3 == null) {
			return true;
		}
		if (s1 == null || s2 == null || s3 == null) {
			return false;
		}
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		if (len1 + len2 != len3) {
			return false;
		}

		// general case
		boolean[][] dp = new boolean[len1 + 1][len2 + 1];
		// initialization
		dp[0][0] = true;
		for (int i = 1; i <= len1; i++) {
			dp[i][0] = (dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
		}
		for (int i = 1; i <= len2; i++) {
			dp[0][i] = (dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1));
		}

		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				int k = i + j - 1; // k在[0, len3)之间
				if (s1.charAt(i - 1) == s3.charAt(k) && s2.charAt(j - 1) == s3.charAt(k)) {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				} else if (s1.charAt(i - 1) == s3.charAt(k)) {
					dp[i][j] = dp[i - 1][j];
				} else if (s2.charAt(j - 1) == s3.charAt(k)) {
					dp[i][j] = dp[i][j - 1];
				}

			}
		}
		return dp[len1][len2];
	}
}
//leetcode submit region end(Prohibit modification and deletion)
}