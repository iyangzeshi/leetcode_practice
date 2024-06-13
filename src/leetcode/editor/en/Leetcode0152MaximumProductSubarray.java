//Given an integer array nums, find the contiguous subarray within an array (con
//taining at least one number) which has the largest product. 
//
// Example 1: 
//
// 
//Input: [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.
// 
//
// Example 2: 
//
// 
//Input: [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray. 
// Related Topics Array Dynamic Programming 
// 👍 4257 👎 157

package leetcode.editor.en;

// 2020-07-26 12:44:23
// Jesse Yang
public class Leetcode0152MaximumProductSubarray{
    // Java: maximum-product-subarray
    public static void main(String[] args) {
        Solution sol = new Leetcode0152MaximumProductSubarray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DP, T(n) = O(n), S(n) = O(1)
// 1 ms,击败了90.92% 的Java用户, 38.9 MB,击败了23.17% 的Java用户
/*
因为涉及到±号的问题
keep两个数字
preMax 表示以前一个数字为结尾的乘积最大值（正数）
preMin 表示以钱一个数字为结尾的乘积最小值（负数）

 */
class Solution {
    
    public int maxProduct(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preMax = nums[0];
        int preMin = nums[0];
        int max = nums[0];
        int curMax;
        int curMin;
        
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            curMax = Math.max(Math.max(preMax * temp, preMin * temp), temp);
            curMin = Math.min(Math.min(preMax * temp, preMin * temp), temp);
            max = Math.max(curMax, max);
            preMax = curMax;
            preMin = curMin;
        }
        return max;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}