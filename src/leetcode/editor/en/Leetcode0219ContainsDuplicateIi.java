//Given an array of integers and an integer k, find out whether there are two di
//stinct indices i and j in the array such that nums[i] = nums[j] and the absolute
// difference between i and j is at most k. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1], k = 3
//Output: true
// 
//
// 
// Example 2: 
//
// 
//Input: nums = [1,0,1,1], k = 1
//Output: true
// 
//
// 
// Example 3: 
//
// 
//Input: nums = [1,2,3,1,2,3], k = 2
//Output: false
// 
// 
// 
// 
// Related Topics Array Hash Table 
// 👍 881 👎 1047

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:56:20
// Jesse Yang
public class Leetcode0219ContainsDuplicateIi{
    // Java: contains-duplicate-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0219ContainsDuplicateIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}