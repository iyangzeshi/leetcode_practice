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
/*
greedy, T(n) = O(n), S(n) = O(1)
设置一个curSum和maxSum
    curSum表示，走到当前这个点的连续的subarray的最大sum
    maxSum表示最大的值

每次遇到一个值num，如果本来curSum >= 0 ,则curSum += num, 否则curSum = num;
    也就是curSum = num + Math.max(0, curSum);
 */
class Solution {
    
    public int maxSubArray(int[] nums) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (currSum < 0) {
                currSum = 0;
            }
            currSum += num;
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2 */

// Solution 1: dynamic programming, T(n) = O(n), S(n) = O(n)
// 1 ms,击败了53.34% 的Java用户, 38.9 MB,击败了71.63% 的Java用户
/*
设置一个curSum和maxSum
    curSum表示，走到当前这个点的连续的subarray的最大sum的数组
    maxSum表示最大的值

每次遇到一个值num，如果当前这个点加上cur >= 0就更新cur += num，否则就把cur重置成num
 */
class Solution1 {
    
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] curSum = new int[len];
        
        curSum[0] = nums[0];
        int maxSum = nums[0];
        
        for (int i = 1; i < len; i++) {
            curSum[i] = nums[i] + (Math.max(curSum[i - 1], 0));
            maxSum = Math.max(maxSum, curSum[i]);
        }
        return maxSum;
    }
    
}

// Solution 2: greedy, T(n) = O(n), S(n) = O(1)
/*
设置一个curSum和maxSum
    curSum表示，走到当前这个点的连续的subarray的最大sum
    maxSum表示最大的值

每次遇到一个值num，如果本来curSum >= 0 ,则curSum += num, 否则curSum = num;
    也就是curSum = num + Math.max(0, curSum);
 */
class Solution2 {
    
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int curSum = nums[0];
        int maxSum = nums[0];
        
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            curSum = num + Math.max(0, curSum);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
    
}

}