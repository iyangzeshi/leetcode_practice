//Given an integer array nums, find the contiguous subarray (containing at least
// one number) which has the largest sum and return its sum. 
//
// Example: 
//
// 
//Input: [-2,1,-3,4,-1,2,1,-5,4],
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.
// 
//
// Follow up: 
//
// If you have figured out the O(n) solution, try coding another solution using 
//the divide and conquer approach, which is more subtle. 
// Related Topics Array Divide and Conquer Dynamic Programming 
// 👍 8134 👎 386

package leetcode.editor.en;

// 2020-07-26 12:45:03
// Zeshi Yang
public class Leetcode0053MaximumSubarray{
    // Java: maximum-subarray
    public static void main(String[] args) {
        Solution sol = new Leetcode0053MaximumSubarray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 1: dynamic programming
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] result = new int[length];

        result[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < length; i++) {
            result[i] = nums[i] + (Math.max(result[i - 1], 0));
            max = Math.max(max, result[i]);
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}