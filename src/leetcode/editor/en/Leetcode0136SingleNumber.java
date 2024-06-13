//Given a non-empty array of integers, every element appears twice except for on
//e. Find that single one. 
//
// Note: 
//
// Your algorithm should have a linear runtime complexity. Could you implement i
//t without using extra memory? 
//
// Example 1: 
//
// 
//Input: [2,2,1]
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: [4,1,2,1,2]
//Output: 4
// 
// Related Topics Hash Table Bit Manipulation 
// 👍 4606 👎 165

package leetcode.editor.en;

// 2020-07-29 20:43:30
// Jesse Yang
public class Leetcode0136SingleNumber{
    // Java: single-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0136SingleNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("should never happen");
        }
        int res = 0;
        for (int num: nums) {
            res = res ^ num; // ^ xor 是异或的意思
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}