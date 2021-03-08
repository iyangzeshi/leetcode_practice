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
// Zeshi Yang
public class Leetcode0033SearchInRotatedSortedArray{
    // Java: search-in-rotated-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0033SearchInRotatedSortedArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int search(int[] nums, int target) {// Jesse version 1
    /*先取mid，按照[left,mid]是否单调分类讨论，
        如果[left,mid]是单调，
            target在[left, mid]之间，right = mid - 1
            target不在[left, mid]之间，left = mid + 1
        如果[left,mid]不是单调，[mid, right]单调
            target在[left, mid]之间，right = mid - 1
            target不在[left, mid]之间(在)，left = mid + 1*/
    
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
    
            if (nums[mid] == target) {
                return mid;
            }else if (nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            }
            
            if (nums[left] < nums[mid]) {
                if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // which means nums[left] >= nums[mid]  or nums[mid] < nums[right]
                if (target > nums[left] || target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}