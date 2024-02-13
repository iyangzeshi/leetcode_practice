//Given a list of non-negative numbers and a target integer k, write a function 
//to check if the array has a continuous subarray of size at least 2 that sums up 
//to a multiple of k, that is, sums up to n*k where n is also an integer. 
//
// 
//
// Example 1: 
//
// 
//Input: [23, 2, 4, 6, 7],  k=6
//Output: True
//Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 
//6.
// 
//
// Example 2: 
//
// 
//Input: [23, 2, 6, 4, 7],  k=6
//Output: True
//Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and 
//sums up to 42.
// 
//
// 
// Constraints: 
//
// 
// The length of the array won't exceed 10,000. 
// You may assume the sum of all the numbers is in the range of a signed 32-bit 
//integer. 
// 
// Related Topics Math Dynamic Programming 
// 👍 1481 👎 2084

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2020-09-14 12:18:21
// Zeshi Yang
public class Leetcode0523ContinuousSubarraySum{
    // Java: continuous-subarray-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0523ContinuousSubarraySum().new Solution();
        // TO TEST
        int[] nums = {0};
        int k = 0;
        boolean res = sol.checkSubarraySum(nums, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (k < 0) {
            return checkSubarraySum(nums, -k);
        }
        int cumSumRemainder = 0;
        Map<Integer, Integer> remainderToIndex = new HashMap<>();
        remainderToIndex.put(0, -1);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            cumSumRemainder += num;
            if (k != 0) {
                cumSumRemainder %= k;
            }
            if (remainderToIndex.containsKey(cumSumRemainder)) {
                if (i - remainderToIndex.get(cumSumRemainder) > 1) {
                    return true;
                }
            } else {
                remainderToIndex.put(cumSumRemainder, i);
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}