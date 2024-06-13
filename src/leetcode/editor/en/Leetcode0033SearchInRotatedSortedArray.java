//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// You are given a target value to search. If found in the array return its inde
//x, otherwise return -1. 
//
// You may assume no duplicate exists in the array. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// Example 1: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1 
// Related Topics Array Binary Search 
// 👍 5343 👎 464

package leetcode.editor.en;

// 2020-08-04 11:19:22
// Jesse Yang
public class Leetcode0033SearchInRotatedSortedArray{
    // Java: search-in-rotated-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0033SearchInRotatedSortedArray().new Solution();
        // TO TEST
        int[] nums = {5, 1, 3};
        int target = 5;
        int res = sol.search(nums, target);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }/* else if (nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            }*/
            
            // the target is always in [left, right] if target exists
            if (nums[left] <= nums[mid]) { // [left, mid] is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else { // nums[mid] < target
                    left = mid + 1;
                }
            } else { // which means nums[left] > nums[mid], turning point in [left, mid]
                if (target >= nums[left] || target < nums[mid]) {
                    right = mid - 1;
                } else { // target > nums[mid] && target < nums[left]
                    left = mid + 1; // target can not < nums[left], so target > nums[mid]
                }
            }
        }
        return -1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

/**面试的时候，用Solution 1_2，比较好讲清楚 */

// Solution 1：找到turning point最小值t, [left, t] 和[t, right]分别binary search,

// Solution 1_1: 通过mid和right比较，找到turning point 最小值t, 再在[left, t] 和[t, right]分别binary search,
// T(n) = O(lg(n)), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 38.2 MB,击败了75.97% 的Java用户
/*
找最小值的方法，binary search和right比较(和left比较也行，但是会比较麻烦）
    假设min index is always in the [left, right]
    
    case1: nums[left] < nums[mid]:
        说明nums[left, right]是sorted,
        case 1.1: nums[mid] < nums[right], then [left, right] is sorted
            return left;
        case1.2 // nums[left] < nums[mid] && nums[mid] > nums[right]
            t在(mid, right]之间, left = mid + 1
    case2: nums[left] > nums[mid]:
        说明t在[left, mid]之间, t在[left, mid]之间, right = mid;
        
然后在[left, tuning) 和 [turning, right]之间都搜索一下target有没有
*/
class Solution1_1 {
    
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int len = nums.length;
        int minIndex = findLowestIndex(nums);
        int left = find(nums, 0, minIndex - 1, target);
        int right = find(nums, minIndex, len - 1, target);
        return Math.max(left, right);
    }
    
    private int findLowestIndex(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left + 1 < right) { // [left, right) length >= 2
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) { // nums[left, mid] is sorted
                if (nums[mid] < nums[right]) { // nums[left, right] is sorted
                    return left;
                } else { // nums[mid] > nums[right], there is turning point in (mid, right]
                    left = mid + 1;
                }
            } else { // nums[left] > nums[mid], tuning point is in [left, mid]
                right = mid;
            }
        }
        return nums[left] < nums[right] ? left : right;
    }
    
    private int find(int[] nums, int left, int right, int target) {
        // corner case
        if (left > right || target < nums[left] || nums[right] < target) {
            return -1;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    
}

// Solution 1_2: 通过mid和right比较，找到turning point 最小值t, 再在[left, t] 和[t, right]分别binary search
// T(n) = O(lg(n)), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 38.2 MB,击败了75.97% 的Java用户
/*
确定转折点的最大值，每次nums[mid]和nums[left]比较，
确定转折点的最小值，每次nums[mid]和nums[right]比较
先找到最小值所在位置t，然后分别在[left, t)和[t, right]之间binary search找target

    假设min index is always in the [left, right]
    
    case 1: nums[mid] < nums[right]
        nums[mid, right] is sorted, right = mid
    case 2: nums[mid] > nums[right]
        there is turning point in nums[mid, right], so the left = mid + 1;
 */
class Solution1_2 {
    
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int len = nums.length;
        int minIndex = findLowestIndex(nums);
        int left = find(nums, 0, minIndex - 1, target);
        int right = find(nums, minIndex, len - 1, target);
        return Math.max(left, right);
    }
    
    private int findLowestIndex(int[] nums) {
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
            if (nums[mid] < nums[right]){ // [mid, right] is sorted
                right = mid;
            } else { // nums[mid] > nums[right]
                left = mid + 1;
            }
        }
        return nums[left] <= nums[right] ? left : right;
    }
    
    private int find(int[] nums, int left, int right, int target) {
        // corner case
        if (left > right || target < nums[left] || nums[right] < target) {
            return -1;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    
}


// Solution 2: 直接binary search, T(n) = O(lg(n)), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 38.4 MB,击败了63.36% 的Java用户
/*
先取mid，按照[left,mid]是否单调分类讨论，
case 0: 取mid = (left + right) / 2;如果nums[mid] == target, return mid;
case 1: [left, mid]是单调，
    target∈num[left, mid)之间，right = mid - 1
    target !∈ nums[left, mid], ∴ left = mid + 1
case 2: [left, mid]不是单调， then → [mid, right]单调
    target在[left, mid]之间(target >= nums[left] 或者target < nums[mid]), 那么right = mid - 1
    target不在[left, mid]之间(target < nums[left] 或者target > nums[mid])，left = mid + 1
*/
class Solution2 {
    
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }/* else if (nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            }*/
            
            // the target is always in [left, right] if target exists
            if (nums[left] <= nums[mid]) { // [left, mid] is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else { // nums[mid] < target
                    left = mid + 1;
                }
            } else { // which means nums[left] > nums[mid], turning point in [left, mid]
                if (target >= nums[left] || target < nums[mid]) {
                    right = mid - 1;
                } else { // target > nums[mid] && target < nums[left]
                    left = mid + 1; // target can not < nums[left], so target > nums[mid]
                }
            }
        }
        return -1;
    }
    
}
}