//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// Find the minimum element. 
//
// You may assume no duplicate exists in the array. 
//
// Example 1: 
//
// 
//Input: [3,4,5,1,2] 
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: [4,5,6,7,0,1,2]
//Output: 0
// 
// Related Topics Array Binary Search 
// 👍 2341 👎 245

package leetcode.editor.en;

// 2020-08-04 11:40:02
// Zeshi Yang
public class Leetcode0153FindMinimumInRotatedSortedArray {
	
	// Java: find-minimum-in-rotated-sorted-array
	public static void main(String[] args) {
		Solution sol = new Leetcode0153FindMinimumInRotatedSortedArray().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int findMin(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        //edge case
        int left = 0;
        int right = nums.length - 1;
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        
        //normal case
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[left]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return Math.min(nums[right], nums[left]);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}