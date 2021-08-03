//Given an array of integers, find out whether there are two distinct indices i 
//and j in the array such that the absolute difference between nums[i] and nums[j]
// is at most t and the absolute difference between i and j is at most k. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1], k = 3, t = 0
//Output: true
// 
//
// 
// Example 2: 
//
// 
//Input: nums = [1,0,1,1], k = 1, t = 2
//Output: true
// 
//
// 
// Example 3: 
//
// 
//Input: nums = [1,5,9,1,5,9], k = 2, t = 3
//Output: false
// 
// 
// 
// Related Topics Sort Ordered Map 
// 👍 1048 👎 1109

package leetcode.editor.en;

import java.util.*;
// 2020-07-13 00:39:24
public class Leetcode0220ContainsDuplicateIii{
    // Java: contains-duplicate-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0220ContainsDuplicateIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 用TreeSet，这种方法不能handle duplicates的情况
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove((long) nums[i - k - 1]);
            }
            long upper = (long) nums[i] + t;
            long lower = (long) nums[i] - t;

            Long temp = set.floor(upper);

            if (temp != null && temp >= lower) {
                return true;
            }
            set.add((long) nums[i]);
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}