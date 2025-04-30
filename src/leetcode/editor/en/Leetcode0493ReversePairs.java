/**
Given an integer array nums, return the number of reverse pairs in the array. 

 A reverse pair is a pair (i, j) where: 

 
 0 <= i < j < nums.length and 
 nums[i] > 2 * nums[j]. 
 

 
 Example 1: 

 
Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 

 Example 2: 

 
Input: nums = [2,4,3,5,1]
Output: 3
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
(2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 

 
 Constraints: 

 
 1 <= nums.length <= 5 * 10⁴ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 

 Related Topics Array Binary Search Divide and Conquer Binary Indexed Tree 
Segment Tree Merge Sort Ordered Set 👍 6366 👎 276

*/
package leetcode.editor.en;

// 2025-01-04 21:46:54
// Jesse Yang
public class Leetcode0493ReversePairs{
    // Java: reverse-pairs
    public static void main(String[] args) {
        Solution sol = new Leetcode0493ReversePairs().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reversePairs(int[] nums) {
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}