//Given an array of integers nums sorted in ascending order, find the starting a
//nd ending position of a given target value. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// If the target is not found in the array, return [-1, -1]. 
//
// Example 1: 
//
// 
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4] 
//
// Example 2: 
//
// 
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1] 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 10^5 
// -10^9 <= nums[i] <= 10^9 
// nums is a non decreasing array. 
// -10^9 <= target <= 10^9 
// 
// Related Topics Array Binary Search 
// 👍 3646 👎 154

package leetcode.editor.en;

// 2020-08-04 11:19:24
// Zeshi Yang
public class Leetcode0034FindFirstAndLastPositionOfElementInSortedArray{
    // Java: find-first-and-last-position-of-element-in-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0034FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0) {
            return new int[]{-1,-1};
        }
        int[] result = new int[2];
        result[0] = findLeftIndex(nums, target);
        result[1] = findRightIndex(nums, target);
        return result;
    }

    // To find the left index, we need to move left even we find the target
    private int findLeftIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        } else {
            return -1;
        }
    }

    // To find the right index, we need to move right even we find the target
    private int findRightIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] == target) {
            return right;
        } else if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}