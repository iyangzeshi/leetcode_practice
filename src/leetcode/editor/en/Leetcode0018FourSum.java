//Given an array nums of n integers and an integer target, are there elements a,
// b, c, and d in nums such that a + b + c + d = target? Find all unique quadruple
//ts in the array which gives the sum of target. 
//
// Note: 
//
// The solution set must not contain duplicate quadruplets. 
//
// Example: 
//
// 
//Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
//A solution set is:
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics Array Hash Table Two Pointers 
// 👍 2013 👎 331

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 13:58:46
// Zeshi Yang
public class Leetcode0018FourSum{
    // Java: 4sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0018FourSum().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // corner case
        if (nums == null || nums.length < 4) {
            return result;
        }

        // general case
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3 && nums[i] <= target / 4; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                threeSum(nums, target, i, result);
            }
        }
        return result;
    }

    public void threeSum(int[] nums, int target, int first,
            List<List<Integer>> result) {
        for (int j = first + 1; j < nums.length - 2 && nums[j] <= (target - nums[first]) / 3; j++) {
            // twoSum(nums, i, result);
            // 要求答案里面不能有重复的，所以下面的这个语句必须要有
            if (j == first + 1 || nums[j - 1] != nums[j]) {
                twoSum(nums, target, first, j, result);
            }
        }
    }

    private void twoSum(int[] nums, int target, int first, int second, List<List<Integer>> result) {
        int low = second + 1, high = nums.length - 1;
        while (low < high) {
            int sum = nums[first] + + nums[second] + nums[low] + nums[high];
            if (sum < target || (low > second + 1 && nums[low] == nums[low - 1])) {
                low++;
            }
            else if (sum > target || (high < nums.length - 1 && nums[high] == nums[high + 1])) {
                --high;
            }
            else {
                result.add(Arrays.asList(nums[first], nums[second], nums[low++], nums[high--]));
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}