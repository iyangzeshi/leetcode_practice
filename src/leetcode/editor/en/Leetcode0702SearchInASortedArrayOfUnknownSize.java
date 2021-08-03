//Given an integer array sorted in ascending order, write a function to search t
//arget in nums. If target exists, then return its index, otherwise return -1. How
//ever, the array size is unknown to you. You may only access the array using an A
//rrayReader interface, where ArrayReader.get(k) returns the element of the array 
//at index k (0-indexed). 
//
// You may assume all integers in the array are less than 10000, and if you acce
//ss the array out of bounds, ArrayReader.get will return 2147483647. 
//
// 
//
// Example 1: 
//
// 
//Input: array = [-1,0,3,5,9,12], target = 9
//Output: 4
//Explanation: 9 exists in nums and its index is 4
// 
//
// Example 2: 
//
// 
//Input: array = [-1,0,3,5,9,12], target = 2
//Output: -1
//Explanation: 2 does not exist in nums so return -1
// 
//
// 
// Constraints: 
//
// 
// You may assume that all elements in the array are unique. 
// The value of each element in the array will be in the range [-9999, 9999]. 
// The length of the array will be in the range [1, 10^4]. 
// 
// Related Topics Binary Search 
// 👍 357 👎 26

package leetcode.editor.en;

// 2020-09-24 15:32:36
// Zeshi Yang
public class Leetcode0702SearchInASortedArrayOfUnknownSize {
	
	// Java: search-in-a-sorted-array-of-unknown-size
	public static void main(String[] args) {
		Solution sol = new Leetcode0702SearchInASortedArrayOfUnknownSize().new Solution();
		// TO TEST
		ArrayReader arrayReader = new ArrayReader(new int[]{-1, 0, 3, 5, 9, 12});
		int target = 9;
		int res = sol.search(arrayReader, target);
		System.out.println(res);
	}
	
	static class ArrayReader {
		
		int[] nums;
		
		public ArrayReader(int[] nums) {
			this.nums = nums;
		}
		
		public int get(int k) {
			if (k < nums.length) {
				return nums[k];
			} else {
				return 2147483647;
			}
		}
	}

//leetcode submit region begin(Prohibit modification and deletion)
	
/**
 * // This is ArrayReader's API interface. // You should not implement it, or speculate about
 * its implementation interface ArrayReader { public int get(int index) {} }
 */

class Solution {
	
	public int search(ArrayReader reader, int target) {
		int left = 0;
		int right = 1;
		while (reader.get(right) != 2147483647 && reader.get(right) < target) {
			right *= 2;
		}
		if (reader.get(right) == target) {
			return right;
		}
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (reader.get(mid) == target) {
				return mid;
			} else if (reader.get(mid) > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return reader.get(left) == target ? left : -1;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}