//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro. 
//
// Note: 
//
// The solution set must not contain duplicate triplets. 
//
// Example: 
//
// 
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics Array Two Pointers 
// 👍 7325 👎 838

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2020-07-25 19:10:23
// Zeshi Yang
public class Leetcode0015ThreeSum {

	// Java: 3sum
	public static void main(String[] args) {
		Solution sol = new Leetcode0015ThreeSum().new Solution();
		// TO TEST
		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<List<Integer>> threeSum(int[] nums) {
        // corner case
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, result);
            }
        }
        return result;
    }
    
    private void twoSum(int[] nums, int i, List<List<Integer>> result) {
        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if (sum < 0 || (left > i + 1 && nums[left] == nums[left - 1])) {
                left++;
            } else if (sum > 0 || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                right--;
            } else {
                result.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}