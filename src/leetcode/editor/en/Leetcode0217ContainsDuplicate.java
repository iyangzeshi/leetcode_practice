//Given an array of integers, find if the array contains any duplicates. 
//
// Your function should return true if any value appears at least twice in the a
//rray, and it should return false if every element is distinct. 
//
// Example 1: 
//
// 
//Input: [1,2,3,1]
//Output: true 
//
// Example 2: 
//
// 
//Input: [1,2,3,4]
//Output: false 
//
// Example 3: 
//
// 
//Input: [1,1,1,3,3,4,3,2,4,2]
//Output: true 
// Related Topics Array Hash Table 
// 👍 921 👎 744

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:56:19
// Zeshi Yang
public class Leetcode0217ContainsDuplicate{
    // Java: contains-duplicate
    public static void main(String[] args) {
        Solution sol = new Leetcode0217ContainsDuplicate().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();

        for (int n: nums) {
            if (!set.add(n)) {
                return true;
            }
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}