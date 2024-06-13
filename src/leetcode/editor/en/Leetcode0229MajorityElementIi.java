//Given an integer array of size n, find all elements that appear more than ⌊ n/
//3 ⌋ times. 
//
// Note: The algorithm should run in linear time and in O(1) space. 
//
// Example 1: 
//
// 
//Input: [3,2,3]
//Output: [3] 
//
// Example 2: 
//
// 
//Input: [1,1,1,3,3,2,2,2]
//Output: [1,2] 
// Related Topics Array 
// 👍 1777 👎 173

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
// 2020-09-10 16:55:08
// Jesse Yang
public class Leetcode0229MajorityElementIi{
    // Java: majority-element-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0229MajorityElementIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Boyer-Moore Voting Algorithm, T(n) = O(n), S(n) = O(1)
class Solution {
    
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) {
            return res;
        }
        int[] candidate = findCandidate(nums);
        int candidate1 = candidate[0];
        int candidate2 = candidate[1];
        
        // another pass to check whether frequency of these 2 candidates satisfy the requirement
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        
        int threshold = nums.length / 3;
        if (count1 > threshold) {
            res.add(candidate1);
        }
        if (count2 > threshold) {
            res.add(candidate2);
        }
        return res;
    }
    
    // find the two most frequent numbers in nums
    private int[] findCandidate(int[] nums) {
        int candidate1 = 1;
        int candidate2 = 1;
        int vote1 = 0;
        int vote2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                vote1++;
            } else if (num == candidate2) {
                vote2++;
            } else if (vote1 == 0) {
                candidate1 = num;
                vote1++;
            } else if (vote2 == 0) {
                candidate2 = num;
                vote2++;
            } else {
                vote1--;
                vote2--;
            }
        }
        return new int[]{candidate1, candidate2};
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}