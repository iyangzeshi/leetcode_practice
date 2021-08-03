//Given an array of n integers nums and a target, find the number of index tripl
//ets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j]
// + nums[k] < target. 
//
// Example: 
//
// 
//Input: nums = [-2,0,1,3], and target = 2
//Output: 2 
//Explanation: Because there are two triplets which sums are less than 2:
//             [-2,0,1]
//             [-2,0,3]
// 
//
// Follow up: Could you solve it in O(n2) runtime? 
// Related Topics Array Two Pointers 
// 👍 583 👎 59

package leetcode.editor.en;

import java.util.*;
// 2020-07-24 15:19:10
// Zeshi Yang
public class Leetcode0259ThreeSumSmaller{
    // Java: 3sum-smaller
    public static void main(String[] args) {
        Solution sol = new Leetcode0259ThreeSumSmaller().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        // corner case
        if (nums == null) {
            return 0;
        }

        // general case
        Arrays.sort(nums);
        int count = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tar = target - nums[i];
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (nums[left] + nums[right] < tar) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}