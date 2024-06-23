/**
Given two integer arrays nums1 and nums2, return the maximum length of a 
subarray that appears in both arrays. 

 
 Example 1: 

 
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
 

 Example 2: 

 
Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 

 
 Constraints: 

 
 1 <= nums1.length, nums2.length <= 1000 
 0 <= nums1[i], nums2[i] <= 100 
 

 Related Topics Array Binary Search Dynamic Programming Sliding Window Rolling 
Hash Hash Function 👍 6763 👎 167

*/
package leetcode.editor.en;

// 2024-06-14 12:44:02
// Jesse Yang
public class Leetcode0718MaximumLengthOfRepeatedSubarray{
    // Java: maximum-length-of-repeated-subarray
    public static void main(String[] args) {
        Solution sol = new Leetcode0718MaximumLengthOfRepeatedSubarray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(m * n), S(m, n) = O(m * n)
/*
DP
创建一个二维的DP数组，dp[i][j](exclude i and j)表示字符串s1的前i-1个字符和字符串s2的前j-2个字符的最长公共子串的长度。
如果s1[i-1]等于s2[j-1]，那么dp[i][j] = dp[i-1][j-1] + 1。
如果s1[i-1]不等于s2[j-1]，那么dp[i][j] = 0。
最终答案是dp数组中的最大值。
 */
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        // dp[i][j]表示A的前i个元素和B的前j个元素的最长公共子数组的长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        int maxLength = 0;
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++){
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}