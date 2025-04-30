/**
Given an integer array nums which is sorted in ascending order and all of its 
elements are unique and given also an integer k, return the kᵗʰ missing number 
starting from the leftmost number of the array. 

 
 Example 1: 

 
Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.
 

 Example 2: 

 
Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing 
number is 8.
 

 Example 3: 

 
Input: nums = [1,2,4], k = 3
Output: 6
Explanation: The missing numbers are [3,5,6,7,...], hence the third missing 
number is 6.
 

 
 Constraints: 

 
 1 <= nums.length <= 5 * 10⁴ 
 1 <= nums[i] <= 10⁷ 
 nums is sorted in ascending order, and all the elements are unique. 
 1 <= k <= 10⁸ 
 

 
Follow up: Can you find a logarithmic time complexity (i.e., 
O(log(n))) solution?

 Related Topics Array Binary Search 👍 1674 👎 62

*/
package leetcode.editor.en;

// 2024-11-22 15:38:20
// Jesse Yang
public class Leetcode1060MissingElementInSortedArray{
    // Java: missing-element-in-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode1060MissingElementInSortedArray().new Solution();
        // TO TEST
        int[] nums = {1,2,4};
        int k = 3;
        int res = sol.missingElement(nums, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int totalMissing = nums[len - 1] - nums[0] - (len - 1);
        /*if (k > totalMissing) {
            throw new IllegalArgumentException("not enough missing numbers");
        }*/
        if (k > totalMissing) {
            return nums[len - 1] + k - totalMissing;
        }
        int left = 0;
        int right = len - 1;
        while (left  + 1 < right) {
            int mid = left + (right - left) / 2;
            int diff = countMissing(nums, left, mid);
            if (diff < k) {
                left = mid;
                k -= diff;
            } else {
                right = mid;
            }
        }
        
        return nums[left] + k;
    }
    
    
    private int countMissing(int[] nums, int left, int right) {
        return nums[right] - nums[left] - (right - left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}