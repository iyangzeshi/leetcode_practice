//Given an array of n positive integers and a positive integer s, find the minim
//al length of a contiguous subarray of which the sum ≥ s. If there isn't one, ret
//urn 0 instead. 
//
// Example: 
//
// 
//Input: s = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: the subarray [4,3] has the minimal length under the problem const
//raint. 
//
// Follow up: 
//
// If you have figured out the O(n) solution, try coding another solution of whi
//ch the time complexity is O(n log n). 
// Related Topics Array Two Pointers Binary Search 
// 👍 3261 👎 131

package leetcode.editor.en;

// 2021-01-19 16:50:36
// Jesse Yang
public class Leetcode0209MinimumSizeSubarraySum{
    // Java: minimum-size-subarray-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0209MinimumSizeSubarraySum().new Solution();
        // TO TEST
        int s = 7;
        int[] nums = {2,3,1,2,4,3};
        int res = sol.minSubArrayLen(s, nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // sum of nums[start, end]
        int start = 0;
        int end = 0;
        int len = nums.length;
        int sum = nums[0];
        int minLen = len + 1;
        while (end < len) {
            if (sum >= s) {
                minLen = Math.min(minLen, end - start + 1);
                sum -= nums[start];
                start++;
            } else {
                end++;
                if (end == len) {
                    break;
                }
                sum += nums[end];
            }
        }
        return minLen == len + 1 ? 0 : minLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}