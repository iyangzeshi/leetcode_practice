//Given an array of integers nums and a positive integer k, find whether it's po
//ssible to divide this array into k non-empty subsets whose sums are all equal. 
//
// 
//
// Example 1: 
//
// 
//Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//Output: True
//Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,
//3) with equal sums.
// 
//
// 
//
// Note: 
//
// 
// 1 <= k <= len(nums) <= 16. 
// 0 < nums[i] < 10000. 
// 
// Related Topics Dynamic Programming Recursion 
// 👍 2701 👎 172

package leetcode.editor.en;

import java.util.Arrays;

// 2021-03-11 16:13:13
// Zeshi Yang
public class Leetcode0698PartitionToKEqualSumSubsets{
    // Java: partition-to-k-equal-sum-subsets
    public static void main(String[] args) {
        Solution sol = new Leetcode0698PartitionToKEqualSumSubsets().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // corner case
        if (k == 1) {
            return true;
        }
        if (nums == null || nums.length == 0 || k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k > 0) {
            return false;
        }
        
        int target = sum / k;
        
        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) {
            return false;
        }
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }
    
    public boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) {
            return true;
        }
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) {
                    return true;
                }
                groups[i] -= v;
            }
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}