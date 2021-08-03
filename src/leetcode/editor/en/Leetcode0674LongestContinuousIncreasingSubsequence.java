//
//Given an unsorted array of integers, find the length of longest continuous inc
//reasing subsequence (subarray).
// 
//
// Example 1: 
// 
//Input: [1,3,5,4,7]
//Output: 3
//Explanation: The longest continuous increasing subsequence is [1,3,5], its len
//gth is 3. 
//Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous
// one where 5 and 7 are separated by 4. 
// 
// 
//
// Example 2: 
// 
//Input: [2,2,2,2,2]
//Output: 1
//Explanation: The longest continuous increasing subsequence is [2], its length 
//is 1. 
// 
// 
//
// Note:
//Length of the array will not exceed 10,000.
// Related Topics Array 
// 👍 803 👎 118

package leetcode.editor.en;

// 2020-07-26 12:43:58
// Zeshi Yang
public class Leetcode0674LongestContinuousIncreasingSubsequence{
    // Java: longest-continuous-increasing-subsequence
    public static void main(String[] args) {
        Solution sol = new Leetcode0674LongestContinuousIncreasingSubsequence().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int findLengthOfLIS(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int cur = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cur++;
            } else {
                cur = 1;
            }
            maxLen = Math.max(cur, maxLen);
        }
        return maxLen;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}