//Given a binary array nums, return the maximum length of a contiguous subarray 
//with an equal number of 0 and 1. 
//
// 
// Example 1: 
//
// 
//Input: nums = [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with an equal number 
//of 0 and 1.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal 
//number of 0 and 1.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// nums[i] is either 0 or 1. 
// 
//
// Related Topics Array Hash Table Prefix Sum 👍 6959 👎 287

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2024-02-27 20:32:19
// Jesse Yang
public class Leetcode0525ContiguousArray{
    // Java: contiguous-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0525ContiguousArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)
/*
assuming countDiff[i] means: in nums[0 ~ i], number of 1 minus number 0
then must exist i and j, countDiff[j] - countDiff[i] = 0
create a HashMap to record the countDiff and its index,
then traverse the every number, update countDiff and
find whether exist i such that countDiff[j] - countDiff[i] = 0
 */
class Solution {
    public int findMaxLength(int[] nums) {
        // corner case
        if (nums == null) {
            return 0;
        }
        
        // general case
        int len = nums.length;
        int countDiff = 0; // count of 1 minus count of 0
        
        // key: count diff from num[0 ~ i] including i, value: index i
        Map<Integer, Integer> diffIndex = new HashMap<>();
        
        diffIndex.put(0, -1);
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            countDiff += num == 1? 1 : -1;
            if (!diffIndex.containsKey(countDiff)) {
                diffIndex.put(countDiff, i);
            } else {
                maxLen = Math.max(maxLen, i - diffIndex.get(countDiff));
            }
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}