//Given an array nums which consists of non-negative integers and an integer m, 
//you can split the array into m non-empty continuous subarrays. 
//
// Write an algorithm to minimize the largest sum among these m subarrays. 
//
// 
// Example 1: 
//
// 
//Input: nums = [7,2,5,10,8], m = 2
//Output: 18
//Explanation:
//There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8],
//where the largest sum among the two subarrays is only 18.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,4,5], m = 2
//Output: 9
// 
//
// Example 3: 
//
// 
//Input: nums = [1,4,4], m = 3
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 106 
// 1 <= m <= min(50, nums.length) 
// 
// Related Topics Binary Search Dynamic Programming 
// 👍 2036 👎 78

package leetcode.editor.en;

import java.util.Arrays;
// 2020-09-24 15:32:23
// Jesse Yang
public class Leetcode0410SplitArrayLargestSum{
    // Java: split-array-largest-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0410SplitArrayLargestSum().new Solution();
        // TO TEST
        int[] nums = {1, 4, 4};
        int m = 3;
        int res = sol.splitArray(nums, m);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// binary search, left is the Max element, right is the sum of the array
class Solution {
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        for (int num: nums) {
            right += num;
            left = Math.max(left, num);
        }
        // corner case
        if (m == len) {
            return left;
        }
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sum = 0;
            int count = 1;
            for (int num : nums) {
                if (sum + num > mid) {
                    count++;
                    sum = num;
                } else {
                    sum += num;
                }
            }
            if (count <= m) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: Dynamic Programming, Time complexity : O(n^2 * m)
// dp[i][j] answer of sub problem, splitting nums[0] - nums[j] into i groups
// dp[1][j] = sum(0, j)
// dp[i][j] = min{max{dp[i - 1][k], sum(k + 1, j)}}
// where 0 <= k < j, sum(k + 1, j) including nums[k +1] and nums[j]
class Solution1 {
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        
        int[] cumSum = new int[len + 1]; // cumSum[i] denotes sum[0, i - 1] (included)
        for (int i = 1; i <= len; i++) {
            cumSum[i] = cumSum[i - 1] + nums[i - 1];
        }
        
        if (m == 1) {
            return cumSum[len];
        }
        int[][] dp = new int[m + 1][len];// dp[i][j] answer of sub problem,. splitting nums[0] - nums[j] into i groups
        for (int[] row: dp) {
            Arrays.fill(row, cumSum[len]);
        }
        dp[1] = Arrays.copyOfRange(cumSum, 1, len + 1);
        for (int i = 2; i <= m ; i++) {
            for (int j = 0; j < len ; j++) {
                int curMin = dp[i - 1][j];
                for (int k = 0; k < j; k++) {
                    int cur = Math.max(dp[i - 1][k], cumSum[j + 1] - cumSum[k + 1]);
                    curMin = Math.min(curMin, cur);
                }
                dp[i][j] = curMin;
            }
        }
        return dp[m][len - 1];
    }
    }
    
// Solution 2:
// binary search, left is the Max element, right is the sum of the array
class Solution2 {
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        for (int num: nums) {
            right += num;
            left = Math.max(left, num);
        }
        // corner case
        if (m == len) {
            return left;
        }
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sum = 0;
            int count = 1;
            for (int num : nums) {
                if (sum + num > mid) {
                    count++;
                    sum = num;
                } else {
                    sum += num;
                }
            }
            if (count <= m) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
}