//A peak element is an element that is greater than its neighbors. 
//
// Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and
// return its index. 
//
// The array may contain multiple peaks, in that case return the index to any on
//e of the peaks is fine. 
//
// You may imagine that nums[-1] = nums[n] = -∞. 
//
// Example 1: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 2
//Explanation: 3 is a peak element and your function should return the index num
//ber 2. 
//
// Example 2: 
//
// 
//Input: nums = [1,2,1,3,5,6,4]
//Output: 1 or 5 
//Explanation: Your function can return either index number 1 where the peak ele
//ment is 2, 
//             or index number 5 where the peak element is 6.
// 
//
// Follow up: Your solution should be in logarithmic complexity. 
// Related Topics Array Binary Search 
// 👍 1800 👎 2115

package leetcode.editor.en;

// 2020-08-04 11:43:46
// Zeshi Yang
public class Leetcode0162FindPeakElement {

	// Java: find-peak-element
	public static void main(String[] args) {
		Solution sol = new Leetcode0162FindPeakElement().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}