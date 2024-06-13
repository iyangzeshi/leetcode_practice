
//Given a binary array, find the maximum number of consecutive 1s in this array.
// 
//
// Example 1: 
// 
//Input: [1,1,0,1,1,1]
//Output: 3
//Explanation: The first two digits or the last three digits are consecutive 1s.
//
//    The maximum number of consecutive 1s is 3.
// 
// 
//
// Note:
// 
// The input array will only contain 0 and 1. 
// The length of input array is a positive integer and will not exceed 10,000 
// 
// Related Topics Array 
// 👍 1059 👎 379

package leetcode.editor.en;

// 2021-02-02 12:19:45
// Jesse Yang
public class Leetcode0485MaxConsecutiveOnes{
    // Java: max-consecutive-ones
    public static void main(String[] args) {
        Solution sol = new Leetcode0485MaxConsecutiveOnes().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                // Increment the count of 1's by one.
                count += 1;
            } else {
                // Find the maximum till now.
                maxCount = Math.max(maxCount, count);
                // Reset count of 1.
                count = 0;
            }
        }
        return Math.max(maxCount, count);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}