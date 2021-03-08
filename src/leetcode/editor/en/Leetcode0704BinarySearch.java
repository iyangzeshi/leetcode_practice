//Given a sorted (in ascending order) integer array nums of n elements and a tar
//get value, write a function to search target in nums. If target exists, then ret
//urn its index, otherwise return -1. 
//
// 
//Example 1: 
//
// 
//Input: nums = [-1,0,3,5,9,12], target = 9
//Output: 4
//Explanation: 9 exists in nums and its index is 4
//
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,0,3,5,9,12], target = 2
//Output: -1
//Explanation: 2 does not exist in nums so return -1
// 
//
// 
//
// Note: 
//
// 
// You may assume that all elements in nums are unique. 
// n will be in the range [1, 10000]. 
// The value of each element in nums will be in the range [-9999, 9999]. 
// 
// Related Topics Binary Search 
// 👍 1010 👎 53

package leetcode.editor.en;

// 2020-12-12 20:07:26
// Zeshi Yang
public class Leetcode0704BinarySearch{
    // Java: binary-search
    public static void main(String[] args) {
        Solution sol = new Leetcode0704BinarySearch().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int search(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}