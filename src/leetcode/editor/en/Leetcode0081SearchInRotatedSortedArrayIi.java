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
// Jesse Yang
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
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        if (nums[left] > nums[right]) { // nums is rotated sorted array
            if (target < nums[left] && nums[right] < target) {
                return false;
            }
        }
        
        int maxIndex = findMaxIndex(nums);
        return find(nums, left, maxIndex, target) || find(nums, maxIndex + 1, right, target);
    }
    
    // find the max value's index in the turning point
    private int findMaxIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return right;
        }
        
        /*
        at least one of [left, mid) and [mid, right] is sorted
        if [a, b] and nums[a] < nums[b], then [a, b] is sorted in this case
         */
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {// [left, mid] is sorted
                left = mid;
            } else if (nums[left] == nums[mid]) {
                if (nums[left] > nums[left + 1]) {
                    return left;
                }
                left++;
            } else { // nums[left] > nums[mid], max is located in [left, mid];
                right = mid;
            }
        }
        return nums[left] >= nums[right] ? left : right;
    }
    
    // in sorted part nums[left, right], try to find target
    private boolean find(int[] nums, int left, int right, int target) {
        // corner case
        if (left > right || target < nums[left] || nums[right] < target) {
            return false;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: 找到转折点，在转折点前面和后面2段区间分别做binary search
/*
先找转折点，
    如果找最大转折点，每次nums[mid]和nums[left]比较
    如果找最小转折点，每次nums[mid]和nums[right]比较
 */

// Solution 1_1: 先找到turning point的最大值，再在两边用binary search
// T(n) = O(n), S(n) = O(1)，每次可能都直走1步，所以T(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 39.1 MB,击败了27.75% 的Java用户
/*
binary search先找转折点
    如果找最大转折点，每次nums[mid]和nums[left]比较
    如果找最小转折点，每次nums[mid]和nums[right]比较
    
[left, mid) 和[mid, right]中间至少有一段是sorted的
找最大值，nums[left]和nums[mid]比较，
    case1: nums[left] == nums[mid]
        [left, mid] can be all equal, or there is turning point in [left, mid],
        but maxIndex is > left, so left++
    case2: nums[left] < nums[mid]
        [left, mid] is sorted, left = mid
    case3: nums[left] > nums[mid]
        there is turning point in the [left, mid], so right = mid;
        
binary search找转折点最大值和left比较的原因，如果和right比较的话，有些情况分析不出来
找最大值，nums[mid]和nums[right]比较，
    case1: nums[mid] == nums[right]
        [mid, right] can be all equal, or there is turning point in [mid, right]
        we can not know anything about max value's index
            if all equal, the max value's index can < mid
            if not all equal, max value can > mid or
            but we can not check whether all number in nums[mid, right] are all equal
    case2: no need to analysis
    case3: no need to analysis

so try to find the max value's index, we need compare nums[left] and nums[mid]
 */
class Solution1_1 {
    
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        if (nums[left] > nums[right]) { // nums is rotated sorted array
            if (target < nums[left] && nums[right] < target) {
                return false;
            }
        }
        
        int maxIndex = findMaxIndex(nums);
        return find(nums, left, maxIndex, target) || find(nums, maxIndex + 1, right, target);
    }
    
    // find the max value's index in the turning point
    private int findMaxIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return right;
        }
        
        /*
        at least one of [left, mid) and [mid, right] is sorted
        if [a, b] and nums[a] < nums[b], then [a, b] is sorted in this case
         */
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {// [left, mid] is sorted
                left = mid;
            } else if (nums[left] == nums[mid]) {
                if (nums[left] > nums[left + 1]) {
                    return left;
                }
                left++;
            } else { // nums[left] > nums[mid], max is located in [left, mid];
                right = mid;
            }
        }
        return nums[left] >= nums[right] ? left : right;
    }
    
    // in sorted part nums[left, right], try to find target
    private boolean find(int[] nums, int left, int right, int target) {
        // corner case
        if (left > right || target < nums[left] || nums[right] < target) {
            return false;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
    
}

// Solution 1_2: 先找到turning point的最小值，再在两边binary search
// T(n) = O(n), S(n) = O(1)，每次可能都直走1步，所以T(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 39.1 MB,击败了27.75% 的Java用户
/*
binary search先找转折点
    如果找最大转折点，每次nums[mid]和nums[left]比较
    如果找最小转折点，每次nums[mid]和nums[right]比较
    
[left, mid) 和[mid, right]中间至少有一段是sorted的
找最小值，nums[mid]和nums[right]比较，
    case1: nums[mid] == nums[right]
        [mid, right] can be all equal, or there is turning point,
        but maxIndex is < right, so right--
    case2: nums[mid] < nums[right]
        [mid, right] is sorted, right = mid
    case3: nums[mid] > nums[right]
        there is turning point in the [mid, right], so left = mid + 1;
        
binary search找转折点最小值和right比较的原因，如果和left比较的话， 有些情况分析不出来
分析同Solution 1_1
 */
class Solution1_2 {
    
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        if (nums[left] > nums[right]) { // nums is rotated sorted array
            if (target < nums[left] && nums[right] < target) {
                return false;
            }
        }
        int minIndex = findMinIndex(nums);
        return find(nums, left, minIndex - 1, target) || find(nums, minIndex, right, target);
        
    }
    
    // find the min value's index in the turning point
    private int findMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return left;
        }
        /*
        at least one of [left, mid) and [mid, right] is sorted
        if [a, b] and nums[a] < nums[b], then [a, b] is sorted in this case
         */
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                if (nums[right - 1] >  nums[right]) {
                    return right;
                }
                right--;
            } else if (nums[mid] < nums[right]){ // [mid, right] is sorted
                right = mid;
            } else { // nums[mid] > nums[right]
                left = mid + 1;
            }
        }
        return nums[left] <= nums[right] ? left : right;
    }
    
    // in sorted part nums[left, right], try to find target
    private boolean find(int[] nums, int left, int right, int target) {
        // corner case
        if (left > right || target < nums[left] || nums[right] < target) {
            return false;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}


// Solution 2: 直接分析mid在哪一段
// T(n) = O(n), S(n) = O(1)，每次可能都直走1步，所以T(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 39.1 MB,击败了27.75% 的Java用户
/*
binary search
assuming target is always in nums[left, right]
edge case: nums[mid] == target, return true;

    case 1: nums[left] < nums[mid]
        [left, mid] is sorted, do general binary search
        if nums[left] <= target < nums[mid]
            target is in [left, mid)
        else
            target is in [mid, right]
    case 2: nums[left] > nums[mid]
        [left, mid] has a turning point,
        if nums[left] <= target or target < nums[mid]
            target is in [left, mid)
        else
            target is in (mid, right]
    case 3: nums[left] == nums[mid]
        we do not get any more information here except for target is located in nums(left, right]
        ∵ nums[mid] != target and nums[left] == target, so nums[left] != target
        
 */
class Solution2 {
    
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        if (nums[left] > nums[right]) { // nums is rotated sorted array
            if (target < nums[left] && nums[right] < target) {
                return false;
            }
        }
        
        // target is always in the nums[left, right] part
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target/* || nums[left] == target || nums[right] == target*/) {
                return true;
            }
            if (nums[left] < nums[mid]) { // nums[left, mid] is sorted
                if (nums[left] <= target && target < nums[mid]) { // target != nums[mid]
                    right = mid - 1;
                } else { // target < nums[left] || target > nums[mid]
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) {
                left++;
            } else { // nums[left] > nums[mid], there's turning point in [left, mid]
                if (nums[left] <= target || target < nums[mid]) { // target != nums[mid]
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }
}
}