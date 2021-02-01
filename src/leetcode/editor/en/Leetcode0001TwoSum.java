//Given an array of integers, return indices of the two numbers such that they a
//dd up to a specific target. 
//
// You may assume that each input would have exactly one solution, and you may n
//ot use the same element twice. 
//
// Example: 
//
// 
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].
// 
// Related Topics Array Hash Table 
// 👍 15861 👎 576

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 2020-07-26 13:50:13
// Zeshi Yang
public class Leetcode0001TwoSum{
    // Java: two-sum
    public static void main(String[] args) {
        Solution sol = new Leetcode0001TwoSum().new Solution();
        // TO TEST
        int[] nums = {2,7,11,15,16,7,8};
        int target = 9;
        int[] res = sol.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // corner case
        if (nums== null || nums.length < 2) {
            return null;
        }
        
        // general case
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target- nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}