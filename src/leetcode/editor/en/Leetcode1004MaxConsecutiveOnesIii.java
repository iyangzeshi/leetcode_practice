/**
Given a binary array nums and an integer k, return the maximum number of 
consecutive 1's in the array if you can flip at most k 0's. 

 
 Example 1: 

 
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined. 

 Example 2: 

 
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 nums[i] is either 0 or 1. 
 0 <= k <= nums.length 
 

 Related Topics Array Binary Search Sliding Window Prefix Sum 👍 10071 👎 177

*/
package leetcode.editor.en;

// 2025-12-06 21:12:11
// Jesse Yang
public class Leetcode1004MaxConsecutiveOnesIii{
    // Java: max-consecutive-ones-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode1004MaxConsecutiveOnesIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int cur = 0; // number of 0s in the current window
        int res = 0;
        int len = nums.length;
        for (int right = 0; right < len; right++) {
            if (nums[right] == 0) {
                cur++;
            }
            if (cur > k) {
                if (nums[left] == 0) {
                    cur--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
