//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]). 
//
// You are given a target value to search. If found in the array return true, ot
//herwise return false. 
//
// Example 1: 
//
// 
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true
// 
//
// Example 2: 
//
// 
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false 
//
// Follow up: 
//
// 
// This is a follow up problem to Search in Rotated Sorted Array, where nums may
// contain duplicates. 
// Would this affect the run-time complexity? How and why? 
// 
// Related Topics Array Binary Search 
// 👍 1492 👎 490

package leetcode.editor.en;

// 2020-09-24 14:36:52
// Zeshi Yang
public class Leetcode0081SearchInRotatedSortedArrayIi {
	
	// Java: search-in-rotated-sorted-array-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0081SearchInRotatedSortedArrayIi().new Solution();
		// TO TEST
		int[] nums = {3, 1};
		int target = 1;
		boolean res = sol.search(nums, target);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        int maxIndex = findTurningPoint(nums);
        if (target == nums[left]) {
            return true;
        } else if (target > nums[left]) {
            right = maxIndex;
        } else {
            left = maxIndex + 1;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    
    private int findTurningPoint(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return right;
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[left]) {
                left++;
            } else if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 先找到最大值，再用binary searc
class Solution1 {
    
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        int maxIndex = findTurningPoint(nums);
        if (target == nums[left]) {
            return true;
        } else if (target > nums[left]) {
            right = maxIndex;
        } else {
            left = maxIndex + 1;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    
    private int findTurningPoint(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return right;
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[left]) {
                left++;
            } else if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }
}

// Solution 2: 直接分析mid在哪一段
class Solution2 {
    
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[left]) {
                if (nums[mid] > nums[left]) {
                    if (nums[mid] > target && target >= nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else if (nums[mid] < nums[left]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        return false;
    }
}
}